package com.yi.homework.controller;

import com.yi.homework.model.vo.request.LoginVO;
import com.yi.homework.model.vo.request.RegisterVO;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project Name: homework-management-system-back-end
 * File Name: UserController
 * Package Name: com.yi.homework.controller
 *
 * @author yipple
 * @date 2020/6/17
 * @since 0.0.1
 */

/**
 * 这个类主要实现了：一个基于用户的RESTful API控制器，包括登录、获取登录状态、注册和注销功能。
 * UserController通过依赖注入UserService的实例，来处理与用户相关的操作。
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {/**这是一个控制器类的声明，使用了@RestController注解，
                                                    表示该类是一个RESTful风格的控制器，用于处理HTTP请求和响应。
                                                    @RequestMapping("/user")注解 指定了该控制器的基本请求路径为"/user"。
                                                    */
    //在控制器类中声明了一个UserService类型的私有变量userService
    private final UserService userService;
    //在构造函数中进行依赖注入的初始化。这里通过构造函数的方式将UserService实例注入到了UserController中。
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 定义了一个处理POST请求的方法login。
     * @PostMapping("/login")注解表示该方法处理路径为"/login"的POST请求。
     * @Validated注解 表示对LoginVO参数进行校验。@RequestBody注解表示请求体中的数据将会映射到LoginVO对象中。
     * 方法内部根据LoginVO对象的属性获取用户ID、密码和用户类型，并调用userService的login方法进行登录操作，并返回ResultVO对象作为响应结果。
     * @param loginVO
     * @return
     */
    @PostMapping("/login")
    public ResultVO login(@Validated @RequestBody LoginVO loginVO) {
        String userId = loginVO.getUserId();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();

        return userService.login(userId, password, userType);
    }

    /**
     * 定义了一个处理GET请求的方法getLoginStatus。
     * @RequestMapping("/login/status")注解 表示该方法处理路径为"/login/status"的GET请求。
     * 方法内部调用userService的getLoginStatus方法获取登录状态，并返回ResultVO对象作为响应结果。
     * @return
     */
    @RequestMapping("/login/status")
    public ResultVO getLoginStatus() {
        return userService.getLoginStatus();
    }

    /**
     * 定义了一个处理POST请求的方法register。
     * @PostMapping("/register")注解 表示该方法处理路径为"/register"的POST请求。
     * @Validated注解 表示对RegisterVO参数进行校验。
     * @RequestBody注解 表示请求体中的数据将会映射到RegisterVO对象中。方法内部根据RegisterVO对象
     * 的属性获取用户ID、用户名、密码和用户类型，并调用userService的register方法进行注册操作，并返回ResultVO对象作为响应结果。
     * @param registerVO
     * @return
     */
    @PostMapping("/register")
    public ResultVO register(@Validated @RequestBody RegisterVO registerVO) {
        String userId = registerVO.getUserId();
        String username = registerVO.getUsername();
        String password = registerVO.getPassword();
        Integer userType = registerVO.getUserType();

        return userService.register(userId, username, password, userType);
    }

    /**
     * 定义了一个处理任意类型请求的方法logout。
     * @RequestMapping("/logout")注解 表示该方法处理路径为"/logout"的任意类型请求（如GET、POST等）。
     * 方法内部调用userService的logout方法进行注销操作，并返回ResultVO对象作为响应结果。
     * @return
     */
    @RequestMapping("/logout")
    public ResultVO logout() {
        return userService.logout();
    }

}
