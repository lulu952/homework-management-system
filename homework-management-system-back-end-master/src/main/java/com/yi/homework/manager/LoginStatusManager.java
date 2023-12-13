package com.yi.homework.manager;

import com.yi.homework.model.bo.LoginStatusBO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Project Name: homework-management-system-back-end
 * File Name: LoginStatusManager
 * Package Name: com.yi.homework.manager
 *
 * @author yipple
 * @date 2020/6/16
 * @since 0.0.1
 */

/**
 * 定义了一个名为LoginStatusManager的类，它是BaseManager的子类，并使用@Component注解将其声明为一个组件（用于依赖注入）。
 * 该类的主要目的是管理用户的登录状态。
 */
@Component
public class LoginStatusManager extends BaseManager {
    // 定义一个常量，表示在会话中存储登录状态的属性名
    private static final String SESSION_USER_STATUS = "user_status";

    // 设置登录状态方法，将登录状态对象存储在会话中
    public void setLoginStatus(HttpSession httpSession, LoginStatusBO loginStatusBO) {
        httpSession.setAttribute(SESSION_USER_STATUS, loginStatusBO);
    }

    /**
     * 获取登录状态方法。
     * 从会话中获取登录状态对象，如果不存在，则创建一个新的对象并存储在会话中后返回
     * @param httpSession
     * @return
     */
    public LoginStatusBO getLoginStatus(HttpSession httpSession) {
        // 从会话中获取登录状态对象
        LoginStatusBO loginStatusBO = (LoginStatusBO) httpSession.getAttribute(SESSION_USER_STATUS);

        // 如果登录状态对象为空，表示会话中没有存储登录状态，则创建一个新的对象，并存储在会话中
        if (loginStatusBO == null) {
            loginStatusBO = new LoginStatusBO();
            setLoginStatus(httpSession, loginStatusBO);
        }
        // 返回登录状态对象
        return loginStatusBO;
    }

}
