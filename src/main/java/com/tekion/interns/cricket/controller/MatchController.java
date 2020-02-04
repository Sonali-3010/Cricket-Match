package com.tekion.interns.cricket.controller;

import com.tekion.interns.cricket.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
    @RequestMapping("/")
    public FinalMatchReport index() {
        Team t1 =  new Team("IND");
        Team t2 = new Team("AUS");
        MatchAdmin admin = new MatchAdmin(t1,t2);
        admin.ConductMatch();
        return new FinalMatchReport(admin.getTossResult(), admin.getInnings1(), admin.getInnings2(), admin.getMatchResult());
    }
}
