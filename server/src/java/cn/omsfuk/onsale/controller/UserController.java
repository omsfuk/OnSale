package cn.omsfuk.onsale.controller;


import cn.omsfuk.onsale.base.Result;
import cn.omsfuk.onsale.base.ResultCache;
import cn.omsfuk.onsale.service.UserService;
import cn.omsfuk.onsale.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v2/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public Result signIn(@Valid UserVO user, Errors errors) {
        if (errors.hasErrors()) {
            return ResultCache.INVALID_INPUT;
        }

        return userService.login(user);
    }

    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public Result signUp(@Valid UserVO user, Errors errors) {
        if (errors.hasErrors()) {
            return ResultCache.INVALID_INPUT;
        }
        return userService.register(user);
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    public Result find(@Valid UserVO user) {
        return userService.findUser(user);
    }

}
