package com.bs.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bus")
public class BusController {
    @RequestMapping("myms")
    public String say(){
        return "bus/message/mymessage";
    }

    @RequestMapping("manage")
    public String reply(){
        return "bus/message/manage";
    }

    @RequestMapping("addscore")
    public String addscore(){
        return "bus/score/scoreadd";
    }

    @RequestMapping("search")
    public String scoreSearch(){
        return "bus/score/search";
    }

    @RequestMapping("stuscore")
    public String stuScore(){
        return "bus/score/stuscore";
    }
}
