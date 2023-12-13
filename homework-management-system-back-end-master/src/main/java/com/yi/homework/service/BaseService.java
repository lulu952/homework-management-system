package com.yi.homework.service;

import com.yi.homework.manager.LoginStatusManager;
import com.yi.homework.model.bo.LoginStatusBO;
import com.yi.homework.model.vo.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * Project Name: homework-management-system-back-end
 * File Name: BaseService
 * Package Name: com.yi.homework.service
 *
 * @author yipple
 * @date 2020/6/17
 * @since 0.0.1
 */
public class BaseService {

    @Autowired
    private HttpSession httpSession; // 声明一个HttpSession类型的私有成员变量，并使用@Autowired注解进行自动注入

    @Autowired
    private LoginStatusManager loginStatusManager; // 声明一个LoginStatusManager类型的私有成员变量，并使用@Autowired注解进行自动注入

    private LoginStatusBO getLoginStatus() {
        // 返回当前用户的登录状态对象
        return loginStatusManager.getLoginStatus(httpSession);
    }

    protected Long getUserId() {
        // 返回当前用户的ID
        return getLoginStatus().getUserId();
    }

    protected ResultVO result(Object data) {
        // 返回一个成功的ResultVO对象，包含数据和默认成功消息
        return new ResultVO(ResultVO.SUCCESS, "success", data);
    }

    protected ResultVO result(Object data, String message) {
        // 返回一个成功的ResultVO对象，包含数据和指定的成功消息
        return new ResultVO(ResultVO.SUCCESS, message, data);
    }

    protected ResultVO failedResult(String message) {
        // 返回一个失败的ResultVO对象，包含指定的失败消息
        // 数据为null
        return new ResultVO(ResultVO.FAIL, message, null);
    }

    protected ResultVO failedResult(String message, Object data) {
        // 返回一个失败的ResultVO对象，包含指定的失败消息和数据
        return new ResultVO(ResultVO.FAIL, message, data);
    }

}
