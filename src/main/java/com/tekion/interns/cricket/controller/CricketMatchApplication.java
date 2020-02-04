package com.tekion.interns.cricket.controller;

import com.tekion.interns.cricket.service.MatchAdmin;
import com.tekion.interns.cricket.service.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CricketMatchApplication {

	public static void main(String[] args) {
//		Team t1 =  new Team("IND");
//		Team t2 = new Team("AUS");
//		MatchAdmin admin = new MatchAdmin(t1,t2);
//		admin.ConductMatch();
		SpringApplication.run(CricketMatchApplication.class, args);
	}

}
