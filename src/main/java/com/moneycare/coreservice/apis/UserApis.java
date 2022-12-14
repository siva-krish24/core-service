package com.moneycare.coreservice.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneycare.coreservice.datamanagement.*;
import com.moneycare.coreservice.datamanagement.wrapper.*;
import com.moneycare.coreservice.model.*;
import com.moneycare.coreservice.transactions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import static com.moneycare.coreservice.transactions.Ratings.ratings;


@RestController
public class UserApis {


    public static int TERMAMOUNT = 500;
    @Autowired
    DefaultLoginUserRepositry defaultLoginUserRepositry;
    @Autowired
    PendingRequestRepo pendingRequestRepo;

    @Autowired
    PendingWithdrawlRequestRepo pendingWithdrawlRequestRepo;
    @Autowired
    RatingRepo ratingRepo;
    @Autowired
    UserCredentialsRepo userCredentialsRepo;
    @Autowired
    UsersRepo usersRepo;

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    DefaultLoginUsers defaultLoginUsers = DefaultLoginUsers.getDefaultLoginUsersInstance();
    @Autowired
    UserCredentials userCredentials = UserCredentials.getUserCredentialsInstance();
    @Autowired
    PendingRequests pendingRequests = PendingRequests.getPendingRequestsInstance();
    @Autowired
    Users users = Users.getUsersInstance();

    @Autowired
    PendingWithdrawRequests pendingWithdrawRequests = PendingWithdrawRequests.getPendingWithdrawRequestsInstance();

    @PostMapping("/makelogin")
    public @ResponseBody ResponseEntity<LoginType> makeLogin(@RequestBody String userCustomer) throws JsonProcessingException {
        System.out.println("/makelogin: " + userCustomer);

        UserAuthEntity userEntity = mapper.readValue(userCustomer, UserAuthEntity.class);
        System.out.println("readValue = " + userEntity.toString());
        if(userEntity==null){
            System.out.println("/makelogin: " + "User Authentication failed");
            return new ResponseEntity<>(LoginType.FAILED, HttpStatus.FORBIDDEN);
        }
        else{
            if(loginCheck(userEntity,  defaultLoginUsers))

                return new ResponseEntity<>(LoginType.FIRSTIME, HttpStatus.OK);
            else if(userEntity.getUserName().equalsIgnoreCase("admin") && loginCheck(userEntity, userCredentials)){
                return new ResponseEntity<>(LoginType.ADMINLOGIN, HttpStatus.OK);
            }

            else if(loginCheck(userEntity,  userCredentials))
                return new ResponseEntity<>(LoginType.SUCCESS, HttpStatus.OK);
            }
        return new ResponseEntity<>(LoginType.FAILED, HttpStatus.FORBIDDEN);
    }

    private boolean ValidateLogin(String userId, CommonStore credSrc){
        return credSrc.containsKey(userId);
    }
    private boolean loginCheck(UserAuthEntity userEntity, CommonStore credSrc) {
        String userId = userEntity.getUserName();
        String pwd = userEntity.getPassword();
       return credSrc.containsKey(userId) && credSrc.get(userId).equals(pwd);
    }

    @PostMapping("/adduser")
      public @ResponseBody ResponseEntity<String> addUser(@RequestBody String addUserRequest) throws JsonProcessingException{
        AddUserRequest adRequest = mapper.readValue(addUserRequest, AddUserRequest.class);
        UserAuthEntity sUsr = adRequest.srcUser;
        String sUserId = sUsr.getUserName();
        BasicUserEntity tUser = adRequest.targetUser;
        UserId userId =  prepareUserId(tUser);
        String requestId = prepareRequestId(tUser);
        if(userCredentials.containsKey(userId.id1) || userCredentials.containsKey(userId.id2) ||
                defaultLoginUsers.containsKey(userId.id1) ||  defaultLoginUsers.containsKey(userId.id1)){
        return new ResponseEntity<>("USER is Already in MoneyCare", HttpStatus.FORBIDDEN);
        }
        ApprovalRequest approvalRequest = new ApprovalRequest(sUsr, tUser);
        if(sUsr.getUserName().equalsIgnoreCase("admin")){
            if(loginCheck(sUsr,  userCredentials) || ValidateLogin(sUsr.getUserName(),userCredentials)){  //previously login check....changed to Validate Login
                if(userId.id1!=null ) {
                    defaultLoginUsers.put(userId.id1, "mny123");
                    defaultLoginUserRepositry.save(new DefaulLoginPair(userId.id1,"mny123"));
                   users.put(userId.id1, new User(tUser));
                   usersRepo.save(new UsersPair(userId.id1,new User(tUser)));
                }
                if(userId.id2!=null) {
                    defaultLoginUsers.put(userId.id1, "mny123");
                    defaultLoginUserRepositry.save(new DefaulLoginPair(userId.id1,"mny123"));
                    users.put(userId.id1, new User(tUser));
                    usersRepo.save(new UsersPair(userId.id1,new User(tUser)));
                }
                                return new ResponseEntity<>("Default Login Created", HttpStatus.OK);
            }
        }
        else {
            if(loginCheck(sUsr, userCredentials) || ValidateLogin(sUserId, userCredentials)) {

                pendingRequests.put(requestId, approvalRequest);
                pendingRequestRepo.save(new PendingRequestPair(requestId,approvalRequest));
                return new ResponseEntity<>("User added Pending for approval", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("AUTH ERROR", HttpStatus.FORBIDDEN);

    }

    private String prepareRequestId(BasicUserEntity tUser) {
        return String.valueOf((tUser.getFirstName()+tUser.getLastName()+tUser.getEmail()+tUser.getMobileNo()).hashCode());
    }

    private UserId prepareUserId(BasicUserEntity tUser) {
        if(tUser.getEmail().contains("@moneycare.com")){
            return new UserId(tUser.getMobileNo());
        }

           return new UserId(tUser.getMobileNo(), tUser.getEmail());
    }

    @PostMapping("/approveuser")
    public @ResponseBody ResponseEntity<String> approveUser(@RequestBody String approvalRequestStr) throws JsonProcessingException{
        ApprovalRequest approvalRequest = mapper.readValue(approvalRequestStr, ApprovalRequest.class);
        int amount = approvalRequest.amount;
        BasicUserEntity tUser = approvalRequest.targetUser;
        UserId uId  = prepareUserId(tUser);
        String requestId = prepareRequestId(tUser);
        System.out.println("Approval request" + approvalRequestStr);
        Earning curEarning = new Earning(amount, LocalDateTime.now(), tUser);

        if(uId.id1!=null) {
            defaultLoginUsers.put(uId.id1, "mny123");
            defaultLoginUserRepositry.save(new DefaulLoginPair( uId.id1, "mny123"));
           if(users.containsKey(approvalRequest.srcUser.getUserName())){
               String srcUserKey = approvalRequest.srcUser.getUserName();
               users.get(srcUserKey).getTeam().add(tUser);
               users.get(srcUserKey).getEarnings().add(curEarning);
               users.get(srcUserKey).setTotalEarning(users.get(srcUserKey).getTotalEarning()+curEarning.getAmount());
               users.get(srcUserKey).setAvailBalance(users.get(srcUserKey).getAvailBalance()+curEarning.getAmount());
               users.get(srcUserKey).setTermAmount(TERMAMOUNT);

               users.put(uId.id1,new User(tUser));
               usersRepo.save(new UsersPair(uId.id1, new User(tUser)));
               System.out.println("Approved user" + uId.id1);
               pendingRequests.remove(requestId);
               pendingRequestRepo.delete(pendingRequestRepo.findAll().stream().filter(val -> val.getKey().equalsIgnoreCase(requestId)).findFirst().get());

           }
        }
        else if(uId.id2!=null){
            defaultLoginUsers.put(uId.id2, "mny123");
            defaultLoginUserRepositry.save(new DefaulLoginPair( uId.id2, "mny123"));
            if(users.containsKey(approvalRequest.srcUser.getUserName())){
                String srcUserKey = approvalRequest.srcUser.getUserName();
                users.get(srcUserKey).getTeam().add(tUser);
                users.put(uId.id2,new User(tUser));
                usersRepo.save(new UsersPair(uId.id2,new User(tUser)));
                System.out.println("Approved user" + uId.id1);
                pendingRequests.remove(requestId);
                pendingRequestRepo.delete(pendingRequestRepo.findAll().stream().filter(val -> val.getKey().equalsIgnoreCase(requestId)).findFirst().get());

            }
        }
        System.out.println("Approved user" + uId.id1 + " "+ uId.id2);
        return new ResponseEntity<>("User Approved", HttpStatus.OK);
    }


    @PostMapping("/addbankaccount")
    public @ResponseBody ResponseEntity<String> addBankAccount(@RequestBody String bankAccountRequest) throws JsonProcessingException{
        BankAccountRequest bankRequest = mapper.readValue(bankAccountRequest, BankAccountRequest.class);
        BankAccount bank = bankRequest.bankAccount;
        String userId = bankRequest.srcUser.getUserName();
         users.get(userId).getBankAccounts().put(bank.getAccountNo(),bank);
        return new ResponseEntity<>("added successfully", HttpStatus.ACCEPTED);
    }
    @PostMapping("/removebankaccount")
    public @ResponseBody ResponseEntity<String> removeBankAccount(@RequestBody String bankAccountRequest) throws JsonProcessingException{
        BankAccountRequest bankRequest = mapper.readValue(bankAccountRequest, BankAccountRequest.class);
        BankAccount bank = bankRequest.bankAccount;
        String userId = bankRequest.srcUser.getUserName();
        users.get(userId).getBankAccounts().remove(bank.getAccountNo());
        return new ResponseEntity<>("removed successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getteam/{userId}")
    public @ResponseBody ResponseEntity<List<BasicUserEntity>> geTeam(@PathVariable String userId) throws JsonProcessingException {
        if(!users.containsKey(userId))
            return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        return new ResponseEntity<>(users.get(userId).getTeam(), HttpStatus.OK);
    }

    @GetMapping("/getpendingapprovals/{userId}")
    public @ResponseBody ResponseEntity<List<ApprovalRequest>> getPendingApprovals(@PathVariable String userId) throws JsonProcessingException {
        if(!userId.equalsIgnoreCase("admin"))  return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        List<ApprovalRequest> pendingRequestsResult =  new ArrayList<>();
        pendingRequests.entrySet().forEach(pr -> {pendingRequestsResult.add(pr.getValue());});
        return new ResponseEntity<>(pendingRequestsResult, HttpStatus.OK);
    }
    @GetMapping("/getbankaccounts/{userId}")
    public @ResponseBody ResponseEntity<List<BankAccount>> getBankAccounts(@PathVariable String userId) throws JsonProcessingException{
        if(!users.containsKey(userId))
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        List<BankAccount> accounts = new ArrayList<>();
        users.get(userId).getBankAccounts().entrySet().forEach(entry -> accounts.add(entry.getValue()));
         return new ResponseEntity<>(accounts,HttpStatus.OK);
    }
    @GetMapping("/getmytotalamount/{userId}")
    public @ResponseBody ResponseEntity<List<Integer>> getTotalAmount(@PathVariable String userId) throws JsonProcessingException{
        if(!users.containsKey(userId))
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        int res = 0;
        for(Earning earning : users.get(userId).getEarnings()){
            res += earning.getAmount();
        }
        //users.get(userId).setTotalEarning(res);
        //users.get(userId).setAvailBalance(res);
        List<Integer> earnings = new ArrayList<>();
        earnings.add(users.get(userId).getTotalEarning());
        earnings.add(users.get(userId).getAvailBalance());
        return new ResponseEntity<>(earnings, HttpStatus.OK);
    }

    @GetMapping("/getuserdetails/{userId}")
    public @ResponseBody ResponseEntity<BasicUserEntity> getUserDetails(@PathVariable String userId) throws JsonProcessingException{
        if (!users.containsKey(userId))
            return new ResponseEntity<>(new BasicUserEntity(),HttpStatus.OK);
        BasicUserEntity profile = new BasicUserEntity(users.get(userId).getFirstName(),users.get(userId).getLastName(),users.get(userId).getEmail(),users.get(userId).getMobileNo());
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }

    @PostMapping("/edituserdetails")
    public @ResponseBody ResponseEntity<String> updateUserDetails(@RequestBody String reqBody) throws JsonProcessingException{

        AddUserRequest editProfile = mapper.readValue(reqBody,AddUserRequest.class);
        UserAuthEntity sUser = editProfile.srcUser;
        String userId = sUser.getUserName();
        BasicUserEntity profileDetails = editProfile.targetUser;
        if (!users.containsKey(userId))
            return new ResponseEntity<>("user not found",HttpStatus.OK);
        users.get(userId).setFirstName(profileDetails.getFirstName());
        users.get(userId).setLastName(profileDetails.getLastName());
        users.get(userId).setEmail(profileDetails.getEmail());
        users.get(userId).setMobileNo(profileDetails.getMobileNo());
        return new ResponseEntity<>("edited success",HttpStatus.OK);
    }

    @PostMapping("/resetpassword")
    public @ResponseBody ResponseEntity<String> resetPassword(@RequestBody String userCustmer) throws JsonProcessingException{

        UserAuthEntity userAuthEntity = mapper.readValue(userCustmer,UserAuthEntity.class);
        System.out.println("user: "+userAuthEntity.getUserName()+" pass: "+userAuthEntity.getPassword());
        if (!users.containsKey(userAuthEntity.getUserName()))
            return new ResponseEntity<>("ERROR",HttpStatus.OK);
        if (defaultLoginUsers.containsKey(userAuthEntity.getUserName())) {

//            User curUser = new User().setUserName(userAuthEntity.getUserName()).setPassword(userAuthEntity.getPassword()).se;
            users.get(userAuthEntity.getUserName()).setPassword(userAuthEntity.getPassword());
            users.get(userAuthEntity.getUserName()).setTeam(new ArrayList<>());
            users.get(userAuthEntity.getUserName()).setBankAccounts(new HashMap<>());
            users.get(userAuthEntity.getUserName()).setEarnings(new ArrayList<>());
            users.get(userAuthEntity.getUserName()).setTransactions(new ArrayList<>());
            UsersPair curPair = new UsersPair(userAuthEntity.getUserName(),users.get(userAuthEntity.getUserName()));
            usersRepo.save(curPair);
            defaultLoginUsers.remove(userAuthEntity.getUserName());
            DefaulLoginPair removedObj = defaultLoginUserRepositry.findAll().stream().filter(val -> val.getKey().equalsIgnoreCase(userAuthEntity.getUserName())).findFirst().get();
            defaultLoginUserRepositry.delete(removedObj);
//            defaultLoginUserRepositry.delete(removedObj);
            userCredentials.put(userAuthEntity.getUserName(), userAuthEntity.getPassword());
            userCredentialsRepo.save(new UserCredentialsPair(userAuthEntity.getUserName(), userAuthEntity.getPassword()));

        }
        else {
            users.get(userAuthEntity.getUserName()).setPassword(userAuthEntity.getPassword());
            UsersPair curUserPair = new UsersPair(userAuthEntity.getUserName(),users.get(userAuthEntity.getUserName()));
            usersRepo.save(curUserPair);
            userCredentials.put(userAuthEntity.getUserName(), userAuthEntity.getPassword());
            userCredentialsRepo.save(new UserCredentialsPair( userAuthEntity.getUserName(), userAuthEntity.getPassword()));

        }

        return new ResponseEntity<>("success " + users.get(userAuthEntity.getUserName()).getPassword(),HttpStatus.OK);
    }

    @GetMapping("/getuserrating/{userId}")
    public @ResponseBody ResponseEntity<Rating> getRating(@PathVariable String userId) throws JsonProcessingException{
        if (!users.containsKey(userId)) return new ResponseEntity<>(new Rating(),HttpStatus.OK);
        if (!ratings.containsKey(userId)) return new ResponseEntity<>(new Rating(),HttpStatus.OK);
        else return new ResponseEntity<>(ratings.get(userId),HttpStatus.OK);
    }
    @PostMapping("/updateterm")
    public @ResponseBody ResponseEntity<String> updateTerm(@RequestBody String term) throws JsonProcessingException{
        TERMAMOUNT = Integer.parseInt(term);
        return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
    }

    @GetMapping("/getterm/{userId}")
    public @ResponseBody ResponseEntity<Integer> getTerm(@PathVariable String userId) throws JsonProcessingException{
        return new ResponseEntity<>(users.get(userId).getTermAmount(),HttpStatus.OK);
    }

    @PostMapping("/addrating")
    public @ResponseBody ResponseEntity<String> addRating(@RequestBody String userRating) throws JsonProcessingException{
        Rating rating = mapper.readValue(userRating,Rating.class);
        String userId = rating.getUserId();
        if (!users.containsKey(userId)) return new ResponseEntity<>("Invalid User",HttpStatus.FORBIDDEN);
        ratings.put(userId,rating);
        ratingRepo.save(new RatingPair(userId,rating));
        return new ResponseEntity<>("Ratings Added",HttpStatus.OK);
    }


    //addWithdraw Request

    @PostMapping("/addwithdrawrequest")
    public @ResponseBody ResponseEntity<String> addWithdrawRequest(@RequestBody String addWithdrawRequest) throws JsonProcessingException{
        AddWithdrawRequest adWithdrawRequest = mapper.readValue(addWithdrawRequest, AddWithdrawRequest.class);
        UserAuthEntity sUsr = adWithdrawRequest.srcUser;
        String sUserId = sUsr.getUserName();
        Transaction userTransaction = adWithdrawRequest.userTransaction;

        if (!users.containsKey(sUserId)){
            return new ResponseEntity<>("Invalid User",HttpStatus.OK);
        }
        else if (pendingWithdrawRequests.containsKey(sUserId)) return new ResponseEntity<>("request limit exceeded",HttpStatus.OK);
        else{
            ApprovalWithdrawRequest approvalWithdrawRequest = new ApprovalWithdrawRequest(sUsr,userTransaction);
            pendingWithdrawRequests.put(sUserId,approvalWithdrawRequest);
            pendingWithdrawlRequestRepo.save(new PendingWithdrawlRequestPair(sUserId,approvalWithdrawRequest));
            return new ResponseEntity<>("request added",HttpStatus.OK);
        }
    }

    //pending withdraw requests
    @GetMapping("/getwithdrawrequests/{userId}")
    public @ResponseBody ResponseEntity<List<ApprovalWithdrawRequest>> getWithdrawRequests(@PathVariable String userId) throws JsonProcessingException {
        if(!userId.equalsIgnoreCase("admin"))  return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        List<ApprovalWithdrawRequest> pendingRequestsResult =  new ArrayList<>();
        pendingWithdrawRequests.entrySet().forEach(pr -> {pendingRequestsResult.add(pr.getValue());});
        return new ResponseEntity<>(pendingRequestsResult, HttpStatus.OK);
    }

    //approve withdraw request
    @PostMapping("/approvalwithdrawrequest")
    public @ResponseBody ResponseEntity<String> approveWithdrawRequest(@RequestBody String approveWithdrawReqStr) throws JsonProcessingException{
        ApprovalWithdrawRequest approvalWithdrawRequest = mapper.readValue(approveWithdrawReqStr,ApprovalWithdrawRequest.class);
        UserAuthEntity sUser = approvalWithdrawRequest.srcUser;
        Transaction userTransaction = approvalWithdrawRequest.userTransaction;
        String sUserId = sUser.getUserName();
        if (!users.containsKey(sUserId))
            return new ResponseEntity<>("Invalid user",HttpStatus.FORBIDDEN);
        if (users.get(sUserId).getAvailBalance() < Integer.parseInt(userTransaction.getTrnAmount())) {
            users.get(sUserId).getTransactions().add(userTransaction);
            pendingWithdrawRequests.remove(sUserId);
            pendingWithdrawlRequestRepo.delete(pendingWithdrawlRequestRepo.findAll().stream().filter(val -> val.getKey().equalsIgnoreCase(sUserId)).findFirst().get());
            return new ResponseEntity<>("Insuficient", HttpStatus.OK);
        }
        else{
            users.get(sUserId).setAvailBalance(users.get(sUserId).getAvailBalance() - Integer.parseInt(userTransaction.getTrnAmount()));
            users.get(sUserId).getTransactions().add(userTransaction);
            pendingWithdrawRequests.remove(sUserId);
            pendingWithdrawlRequestRepo.delete(pendingWithdrawlRequestRepo.findAll().stream().filter(val -> val.getKey().equalsIgnoreCase(sUserId)).findFirst().get());
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
    }

    @GetMapping("/usertransactions/{userId}")
    public @ResponseBody ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable String userId) throws JsonProcessingException{
        if (!users.containsKey(userId)) return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        else return new ResponseEntity<>(users.get(userId).getTransactions(),HttpStatus.OK);
    }

    @GetMapping("/displayallusers/{userId}")
    public @ResponseBody ResponseEntity<List<UserItem>> getAllUserDetails(@PathVariable String userId)throws JsonProcessingException{
        if (!userId.equalsIgnoreCase("admin")) return new ResponseEntity<>(new ArrayList<>(),HttpStatus.FORBIDDEN);
        List<UserItem> userItemList = new ArrayList<>();
        users.entrySet().forEach(user -> {
            userItemList.add(new UserItem(user.getValue().getMobileNo(),
                    user.getValue().getEmail(),
                    user.getValue().getAvailBalance(),
                    user.getValue().getTotalEarning(),
                    user.getValue().getTeam(),
                    user.getValue().getBankAccounts()));
        });
        return new ResponseEntity<>(userItemList,HttpStatus.OK);
    }
    @GetMapping("/checkuser/{userId}")
    public @ResponseBody ResponseEntity<String> checkUser(@PathVariable String userId) throws JsonProcessingException{
        if (!users.containsKey(userId)) return new ResponseEntity<>("NotFound",HttpStatus.OK);
        return new ResponseEntity<>("Found",HttpStatus.OK);
    }

//    @PostMapping("/addbankaccount")
//    @PostMapping("/removebankaccount")
//    @GetMapping("/getbankaccounts")

//    @PostMapping("/removeuser")
//
//
//
//    @GetMapping("/getmyteam")
//    @GetMapping("/getuserdetails")
//    @GetMapping("/getmytotalamount")



    //displaying all details for postman testing

  /*  @GetMapping("/displayUsers")
    public @ResponseBody Map<String,User> displayUsers(){
        return users;
    }
    @GetMapping("/displayUserCred")
    public @ResponseBody Map<String,String> displayUserCred(){
        return userCredentials;
    }
    @GetMapping("/displayDUsers")
    public @ResponseBody Map<String,String> displayDUsers(){
        return defaultLoginUsers;
    }*/

   /* @GetMapping("/resettransactions/{userId}")
    public @ResponseBody String resetTransactions(@PathVariable String userId){
        users.get(userId).getTransactions().clear();
        return "Done";
    }
*/



}