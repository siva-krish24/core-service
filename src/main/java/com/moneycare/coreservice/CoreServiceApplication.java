package com.moneycare.coreservice;

import com.moneycare.coreservice.datamanagement.*;

import com.moneycare.coreservice.datamanagement.wrapper.*;
import com.moneycare.coreservice.model.ApprovalRequest;
import com.moneycare.coreservice.model.BasicUserEntity;
import com.moneycare.coreservice.model.User;
import com.moneycare.coreservice.model.UserAuthEntity;
import com.moneycare.coreservice.transactions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;


@SpringBootApplication(scanBasePackages={"com.moneycare.coreservice"})
@EnableWebMvc
@EnableScheduling
public class CoreServiceApplication implements CommandLineRunner {
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


	DefaultLoginUsers defaultLoginUsers = DefaultLoginUsers.getDefaultLoginUsersInstance();
	PendingRequests pendingRequests = PendingRequests.getPendingRequestsInstance();
	UserCredentials userCredentials = UserCredentials.getUserCredentialsInstance();
	PendingWithdrawRequests pendingWithdrawRequests = PendingWithdrawRequests.getPendingWithdrawRequestsInstance();
	Users users = Users.getUsersInstance();

	public static void main(String[] args) {
		SpringApplication.run(CoreServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting");
		userCredentialsRepo.save(new UserCredentialsPair("admin", "admin"));

		if(System.getenv("deployment")!=null && System.getenv("deployment").equals("local")){
			//set environment vairable deployment=local
			initiliseLocalCommonStore();
		}
		else{
			initiliseFromDataStore();
		}
//		SyncUpData sd =  new SyncUpData();
//		Timer timer = new Timer("Timer");
//		long delay = 1000L;
//		timer.schedule(sd, delay);
	}

	private void initiliseFromDataStore(){

		defaultLoginUserRepositry.findAll().stream().forEach(val -> defaultLoginUsers.put(val.getKey(),val.getValue()));
		pendingRequestRepo.findAll().stream().forEach(val -> pendingRequests.put(val.getKey(), val.getValue()));
		pendingWithdrawlRequestRepo.findAll().stream().forEach(val -> pendingWithdrawRequests.put(val.getKey(),val.getValue()));
		ratingRepo.findAll().stream().forEach(val -> Ratings.ratings.put(val.getKey(), val.getValue()));
		userCredentialsRepo.findAll().forEach(val -> userCredentials.put(val.getKey(), val.getValue()));
		usersRepo.findAll().forEach(val -> users.put(val.getKey(), val.getValue()));
	}

	private void initiliseLocalCommonStore() {


		users.put("user1", new User("fn1", "ln1","ss@moneycare.com","user1"));
		defaultLoginUsers.put("default1", "mny123");
		defaultLoginUsers.put("default2", "mny123");
		defaultLoginUsers.put("default3", "mny123");

		UserAuthEntity u1 = new UserAuthEntity("user1","pwd1");
		UserAuthEntity u2 = new UserAuthEntity("user2","pwd2");
		UserAuthEntity u3 = new UserAuthEntity("user3","pwd3");

		userCredentials.put(u1.getUserName(),u1.getPassword());
		userCredentials.put(u2.getUserName(),u2.getPassword());
		userCredentials.put(u3.getUserName(),u3.getPassword());
		userCredentials.put("admin","admin");

		BasicUserEntity bu1 = new BasicUserEntity("fn1","ln1","ss@moneycare.com","u1fn1ln1345");
		BasicUserEntity bu2 = new BasicUserEntity("fn2","ln2","ss@moneycare.com","u2fn2ln2345");
		BasicUserEntity bu3 = new BasicUserEntity("fn3","ln3","ss@moneycare.com","u3fn3ln3345");

		ApprovalRequest ar1 = new ApprovalRequest(u1, bu1);
		ApprovalRequest ar2 = new ApprovalRequest(u2, bu2);
		ApprovalRequest ar3 = new ApprovalRequest(u3, bu3);

		pendingRequests.put("pr1", ar1);
		pendingRequests.put("pr2", ar2);
		pendingRequests.put("pr3", ar3);



	}

	@EnableAsync
	public class SyncUpData {
		@Async
		@Scheduled(fixedRate = 60*1000)
		public void run() {
			long startTime = System.currentTimeMillis();
				System.out.println( ": DataSync is running... at: " + LocalDateTime.now());
				 List<DefaulLoginPair> curD = defaultLoginUsers.entrySet().stream().map(val -> new DefaulLoginPair(val.getKey(),val.getValue())).collect(Collectors.toList());
				defaultLoginUserRepositry.saveAll(curD);
				List<UsersPair> curUsers= users.entrySet().stream().map(val -> new UsersPair(val.getKey(),val.getValue())).collect(Collectors.toList());
				usersRepo.saveAll(curUsers);
				List<PendingRequestPair> ppair = pendingRequests.entrySet().stream().map(val -> new PendingRequestPair(val.getKey(),val.getValue())).collect(Collectors.toList());
				pendingRequestRepo.saveAll(ppair);
				List<PendingWithdrawlRequestPair> pwpair = pendingWithdrawRequests.entrySet().stream().map(val -> new PendingWithdrawlRequestPair(val.getKey(),val.getValue())).collect(Collectors.toList());
				pendingWithdrawlRequestRepo.saveAll(pwpair);
				List<RatingPair> rrPair = Ratings.ratings.entrySet().stream().map(val -> new RatingPair(val.getKey(),val.getValue())).collect(Collectors.toList());
				ratingRepo.saveAll(rrPair);
				List<UserCredentialsPair> upair = userCredentials.entrySet().stream().map(val -> new UserCredentialsPair(val.getKey(),val.getValue())).collect(Collectors.toList());
				 userCredentialsRepo.saveAll(upair);

			}
	}

}
