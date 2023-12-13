package com.yi.homework.controller.teacher;

import com.yi.homework.controller.BaseController;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.request.AddHomeworkItemVO;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.teacher.TeacherHomeworkService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Project Name: homework-management-system-back-end
 * File Name: TeacherHomeworkController
 * Package Name: com.yi.homework.controller.teacher
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */

/**
 * TeacherHomeworkController 是一个具体的控制器类，用于处理与教师作业相关的请求。
 * 它继承了 BaseController 提供的通用方法，并通过构造函数注入TeacherHomeworkService对象，
 * 以便调用其中的业务方法来处理请求并返回相应的结果。根据不同的请求类型和路径，它定义了多个处理方法，
 * 每个方法都由一个特定的HTTP方法注解标识，用于指定请求方法类型。
 */

// @RestController 是一个Spring Framework的注解，用于标识该类是一个基于RESTful风格的控制器，可以处理HTTP请求和生成相应的响应。
@RestController
// @RequestMapping("teacher/homework") 注解将指定该控制器处理对 "/teacher/homework" 路径的请求。
@RequestMapping("teacher/homework")
// TeacherHomeworkController 是一个控制器类，继承自 BaseController。它扩展了 BaseController 提供的公共方法。
public class TeacherHomeworkController extends BaseController {
    /**
     * 这是一个私有成员变量 teacherHomeworkService，它是 TeacherHomeworkService 类的实例。
     * 通过构造函数注入的方式将 TeacherHomeworkService 的实例传入该控制器类
     */
    private final TeacherHomeworkService teacherHomeworkService;

    /**
     * 这是 TeacherHomeworkController 类的构造函数。
     * 它接收一个 TeacherHomeworkService 类型的参数，并将其赋值给控制器中的 teacherHomeworkService 成员变量。
     * @param teacherHomeworkService
     */
    public TeacherHomeworkController(TeacherHomeworkService teacherHomeworkService) {
        this.teacherHomeworkService = teacherHomeworkService;
    }

    /**
     * @PostMapping 注解表示这是一个处理 HTTP POST 方法的请求处理方法。
     * 它接收一个被 @Validated 注解校验过的 AddHomeworkItemVO 对象作为请求体，
     * 并调用 teacherHomeworkService 的 addHomework 方法进行处理并返回结果。
     * @param addHomeworkItemVO
     * @return
     */
    @PostMapping
    public ResultVO addHomework(@Validated @RequestBody AddHomeworkItemVO addHomeworkItemVO) {
        return teacherHomeworkService.addHomework(addHomeworkItemVO);
    }

    /**
     * @DeleteMapping 注解表示这是一个处理 HTTP DELETE 方法的请求处理方法。
     * 它接收一个通过 @PathVariable 注解绑定的 homeworkId 参数，
     * 并调用 teacherHomeworkService 的 deleteHomework 方法进行处理并返回结果。
     * @param homeworkId
     * @return
     */
    @DeleteMapping("/{homeworkId}")
    public ResultVO deleteHomework(@PathVariable Long homeworkId) {
        return teacherHomeworkService.deleteHomework(homeworkId);
    }

    /**
     * @PutMapping 注解表示这是一个处理 HTTP PUT 方法的请求处理方法。
     * 它接收一个被 @Validated 注解校验过的 Homework 对象作为请求体，
     * 并调用 teacherHomeworkService 的 updateHomework 方法进行处理并返回结果。
     * @param homework
     * @return
     */
    @PutMapping
    public ResultVO updateHomework(@Validated @RequestBody Homework homework) {
        return teacherHomeworkService.updateHomework(homework);
    }

    /**
     * @GetMapping 注解表示这是一个处理 HTTP GET 方法的请求处理方法。
     * 它接收一个通过 @PathVariable 注解绑定的 homeworkId 参数，
     * teacherHomeworkService 的 getHomework 方法进行处理并返回结果。
     * @param homeworkId
     * @return
     */
    @GetMapping("/{homeworkId}")
    public ResultVO getHomework(@PathVariable("homeworkId") Long homeworkId) {
        return teacherHomeworkService.getHomework(homeworkId);
    }

    /**
     * @RequestMapping 注解表示这是一个处理 HTTP GET 方法的请求处理方法。
     * 它接收 homeworkId 和 homeworkTitle 作为参数，并调用
     * teacherHomeworkService 的 getPageCount 方法进行处理并返回结果。
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/count")
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        return teacherHomeworkService.getPageCount(homeworkId, homeworkTitle);
    }

    /**
     * @RequestMapping 注解表示这是一个处理 HTTP GET 方法的请求处理方法。
     * 它接收 index、homeworkId 和 homeworkTitle 作为参数，并调用
     * teacherHomeworkService 的 getPage 方法进行处理并返回结果。
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, Long homeworkId, String homeworkTitle) {
        return teacherHomeworkService.getPage(index, homeworkId, homeworkTitle);
    }

}
