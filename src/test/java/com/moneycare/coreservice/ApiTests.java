package com.moneycare.coreservice;

import com.moneycare.coreservice.model.AddUserRequest;
import com.moneycare.coreservice.model.BasicUserEntity;
import com.moneycare.coreservice.model.UserAuthEntity;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @GetMapping("/getuserrating/{userId}")
 * @PostMapping("/updateterm")
 * @GetMapping("/getterm/{userId}")
 * @PostMapping("/addrating")
 * @PostMapping("/addwithdrawrequest")
 * @GetMapping("/getwithdrawrequests/{userId}")
 * @PostMapping("/approvalwithdrawrequest")
 * @GetMapping("/usertransactions/{userId}")
 * @GetMapping("/displayallusers/{userId}")
 * @GetMapping("/checkuser/{userId}")
 * @PostMapping("/addwithdrawrequest")
 * @PostMapping("/addrating")
 * @GetMapping("/getterm/{userId}")
 * @PostMapping("/updateterm")
 * @GetMapping("/getuserrating/{userId}")
 * @PostMapping("/resetpassword")
 * @PostMapping("/edituserdetails")
 * @GetMapping("/getuserdetails/{userId}")
 * @GetMapping("/getmytotalamount/{userId}")
 * @GetMapping("/getbankaccounts/{userId}")
 * @GetMapping("/getpendingapprovals/{userId}")
 * @GetMapping("/getteam/{userId}")
 * @PostMapping("/removebankaccount")
 * @PostMapping("/addbankaccount")
 * @PostMapping("/approveuser")
 * @PostMapping("/adduser")
 * @PostMapping("/makelogin")
 */
public class ApiTests {
//    @Test
    public void adminAdduser(){
       UserAuthEntity srcUser = new UserAuthEntity("admin", "admin");
       BasicUserEntity targetUser = new BasicUserEntity("unit", "test",
               "345345345@moneycare.com","345345345","342123");
        AddUserRequest addUserRequest = new AddUserRequest(srcUser, targetUser);
        try {
           String response =  PostRequest.sendRequest(API.ADDUSER,addUserRequest.toString());
           System.out.println(response);
           assert (response!=null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void makeLoginWithDefaultPassword(){

    }

    public void resetDefualtPassword(){

    }
    public void makeLogin(){

    }
    public void userAddUser(){

    }

    public void approveUser(){

    }
    public void addBankAccount(){

    }
    public void removeBankAccount(){

    }
    public void getBankAccounts(){

    }
    public void getTeam(){

    }
    public void getPendingApproval(){

    }
    public void getUserDetails(){

    }
    public void editUserDetails(){

    }
    public void addwithdrawrequest(){

    }
    public void getwithdrawrequests(){

    }
    public void updateTerm(){

    }
    public void getTerm(){

    }
}
