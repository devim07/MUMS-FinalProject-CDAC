package com.project.mums.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.mums.entities.Cust;
import com.project.mums.entities.Emp;
import com.project.mums.entities.Login;
import com.project.mums.repository.CustRepo;
import com.project.mums.repository.EmpRepo;
import com.project.mums.repository.LoginRepo;

@Component
public class EventListnerBean{
//	private static final Logger LOG
//		=Logger.getLogger(EventListnerBean.class);
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	private EmpRepo empRepo;
	
	@Autowired 
	private CustRepo custRepo;
	
	@Autowired
	private Login login;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//LOG.info("Generating Login details for existing users in DB");
		List<Emp> emps = this.empRepo.findAll();
		List<Cust> custs = this.custRepo.findAll();
		for (Emp emp:emps){
			login.setUserName(emp.getEmpno().toLowerCase());
			login.setid(emp.getEmpno());
			login.setPassword(this.passwordEncoder.encode(emp.getEmpno().toUpperCase()));
			String role="";
			if(emp.getJob()=='M')
				role="ROLE_ADMIN";
			else 
				role="ROLE_EMPLOYEE";
			login.setRole(role);
			//System.out.println(login);
			this.loginRepo.save(login);
		}for(Cust cust:custs) {
			String custNo="cust"+cust.getCustno();
			login.setUserName(custNo.toLowerCase());
			login.setPassword(this.passwordEncoder.encode(custNo.toUpperCase()));
			login.setid(((Integer)cust.getCustno()).toString());
			login.setRole("ROLE_CUSTOMER");
			//System.out.println(login);
			this.loginRepo.save(login);
		}			
	}
}
