package com.yi.homework.service;

import com.yi.homework.manager.LoginStatusManager;
import com.yi.homework.manager.UserManager;
import com.yi.homework.model.bo.AuthInfoBO;
import com.yi.homework.model.bo.LoginStatusBO;
import com.yi.homework.model.vo.response.ResultVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Project Name: homework-management-system-back-end
 * File Name: UserService
 * Package Name: com.yi.homework.service
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */

/**
 * 该类是一个实现业务逻辑的UserService，它继承了BaseService类，提供了用户登录、注册、注销和获取登录状态等功能。
 * 它使用@Autowired注解将HttpSession、UserManager和LoginStatusManager自动注入，并在构造方法中进行依赖注入。
 * 该类通过调用UserManager和LoginStatusManager的相应方法来完成具体的业务逻辑，并使用BaseService中提供的方法来构造结果对象。
 */

@Service
public class UserService extends BaseService {

    private final HttpSession httpSession; // 声明一个HttpSession类型的私有成员变量

    private final UserManager userManager; // 声明一个UserManager类型的私有成员变量

    private final LoginStatusManager loginStatusManager; // 声明一个LoginStatusManager类型的私有成员变量

    /**
     * UserService的构造方法，用于注入依赖
     * 接受一个HttpSession对象、UserManager对象和LoginStatusManager对象作为参数
     * 并将它们分别赋值给对应的成员变量
     * @param httpSession
     * @param userManager
     * @param loginStatusManager
     */
    public UserService(HttpSession httpSession, UserManager userManager, LoginStatusManager loginStatusManager) {
        this.httpSession = httpSession;
        this.userManager = userManager;
        this.loginStatusManager = loginStatusManager;
    }

    /**
     * 用户登录方法
     * @param userId
     * @param password
     * @param userType
     * @return
     */
    public ResultVO login(String userId, String password, Integer userType) {
        // 根据用户ID和用户类型获取认证信息对象AuthInfoBO
        AuthInfoBO authInfoBO = userManager.getAuthInfoByLoginId(userId, userType);

        if (authInfoBO == null) {
            // 如果认证信息对象为空，表示用户不存在，返回一个失败的ResultVO对象
            return failedResult("用户不存在");
        }

        // 对输入的密码进行加密，比较加密后的密码是否与认证信息对象中的密码匹配
        String encryptedPassword = userManager.passwordMd5(password);
        if (!encryptedPassword.equals(authInfoBO.getPassword())) {
            // 如果密码不匹配，返回一个失败的ResultVO对象
            return failedResult("密码错误");
        }

        // 根据认证信息对象构造登录状态对象LoginStatusBO
        LoginStatusBO loginStatusBO = LoginStatusBO.fromAuthInfo(authInfoBO);
        // 将登录状态对象存储到Session中
        loginStatusManager.setLoginStatus(httpSession, loginStatusBO);

        // 返回一个成功的ResultVO对象，包含登录状态对象作为数据
        return result(loginStatusBO);
    }

    /**
     * 用户注册方法
     * @param userId
     * @param username
     * @param password
     * @param userType
     * @return
     */
    public ResultVO register(String userId, String username, String password, Integer userType) {
        // 检查账号是否已经注册
        if (!userManager.checkIsUserIdValid(userId, userType)) {
            // 如果账号已经注册，返回一个失败的ResultVO对象
            return failedResult("该账号已注册");
        }

        // 调用UserManager的register方法进行用户注册
        if (!userManager.register(userId, username, password, userType)) {
            // 如果注册过程中发生未知错误，返回一个失败的ResultVO对象
            return failedResult("注册时发生未知错误");
        } else {
            // 如果注册成功，返回一个成功的ResultVO对象，包含注册成功消息和用户名
            return result("注册成功", username);
        }
    }

    /**
     * 用户注销方法
     * @return
     */
    public ResultVO logout() {
        // 将Session中的登录状态对象置为null，表示用户已注销
        loginStatusManager.setLoginStatus(httpSession, null);
        // 返回一个成功的ResultVO对象，包含注销成功消息
        return result("注销成功");
    }

    /**
     * 获取用户登录状态方法
     * @return
     */
    public ResultVO getLoginStatus() {
        // 通过LoginStatusManager获取当前Session中的登录状态对象LoginStatusBO
        // 并将其作为数据返回一个成功的ResultVO对象
        return result(loginStatusManager.getLoginStatus(httpSession));
    }

}
