package cn.omsfuk.onsale.controller;


import cn.omsfuk.onsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/active", method = RequestMethod.GET)
    public String active(@RequestParam(required = true) String email, @RequestParam(required = true) String ac_code) {
        if (userService.activeAccountByEamil(email, ac_code)) {
            return "active_success";
        }
        return "active_failure";
    }
}
