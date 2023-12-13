package com.yi.homework.controller;

import com.yi.homework.model.vo.response.ResultVO;

/**
 * Project Name: homework-management-system-back-end
 * File Name: BaseController
 * Package Name: com.yi.homework.controller
 *
 * @author yipple
 * @date 2020/6/17
 * @since 0.0.1
 */

/**
 * 基类BaseController定义了几个公共方法，用于创建不同类型的ResultVO对象，
 * 以便在子类的控制器方法中根据不同的业务逻辑返回相应的结果。这些方法可以被子类继承和调用，
 * 以统一定义和处理返回结果的方式。
 */

public class BaseController {//这是一个基类的声明，用于提供一些通用的方法供其他控制类继承和使用。

    /**
     * result方法接收一个Object类型的参数data，并返回一个ResultVO对象。
     * 该方法用于创建一个表示成功结果的ResultVO对象，并将给定的data作为成功结果的数据。
     * @param data
     * @return
     */
    protected ResultVO result(Object data) {
        return new ResultVO(ResultVO.SUCCESS, "success", data);
    }

    /**
     * result方法重载。除了接收data参数外，还接收一个String类型的参数message，并返回一个ResultVO对象。
     * 该方法用于创建一个表示成功结果的ResultVO对象，并将给定的data作为成功结果的数据，并将给定的message作为结果的消息。
     * @param data
     * @param message
     * @return
     */
    protected ResultVO result(Object data, String message) {
        return new ResultVO(ResultVO.SUCCESS, message, data);
    }

    /**
     * failedResult方法接收一个String类型的参数message，并返回一个ResultVO对象。
     * 该方法用于创建一个表示失败结果的ResultVO对象，并将给定的message作为结果的消息，并将结果的数据设置为null。
     * @param message
     * @return
     */
    protected ResultVO failedResult(String message) {
        return new ResultVO(ResultVO.FAIL, message, null);
    }

    /**
     * failedResult方法重载。除了接收message参数外，还接收一个Object类型的参数data，并返回一个ResultVO对象。
     * 该方法用于创建一个表示失败结果的ResultVO对象，并将给定的message作为结果的消息，并将给定的data作为结果的数据。
     * @param message
     * @param data
     * @return
     */
    protected ResultVO failedResult(String message, Object data) {
        return new ResultVO(ResultVO.FAIL, message, data);
    }

}
