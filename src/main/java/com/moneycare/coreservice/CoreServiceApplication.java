package com.moneycare.coreservice;

import com.moneycare.coreservice.apis.UserApis;
import com.moneycare.coreservice.model.ApprovalRequest;
import com.moneycare.coreservice.model.BasicUserEntity;
import com.moneycare.coreservice.model.User;
import com.moneycare.coreservice.model.UserAuthEntity;
import com.moneycare.coreservice.transactions.DefaultLoginUsers;
import com.moneycare.coreservice.transactions.PendingRequests;
import com.moneycare.coreservice.transactions.UserCredentials;
import com.moneycare.coreservice.transactions.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(scanBasePackages={"com.moneycare.coreservice"})
public class CoreServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CoreServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting");
		if(System.getenv("deployment").equals("local")){
			initiliseLocalCommonStore();
		}
		else{

		}
	}

	private void initiliseLocalCommonStore() {
		DefaultLoginUsers defaultLoginUsers = DefaultLoginUsers.getDefaultLoginUsersInstance();
		PendingRequests pendingRequests = PendingRequests.getPendingRequestsInstance();
		UserCredentials userCredentials = UserCredentials.getUserCredentialsInstance();
		Users users = Users.getUsersInstance();

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


}
