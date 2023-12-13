package com.yi.homework.controller.teacher;

import com.yi.homework.controller.BaseController;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.teacher.TeacherSubmittedService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Project Name: homework-management-system-back-end
 * File Name: TeacherSubmittedController
 * Package Name: com.yi.homework.controller.teacher
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@RestController // 表明该类是一个Controller，负责处理HTTP请求和响应
@RequestMapping("/teacher/submitted")
public class TeacherSubmittedController extends BaseController { // 继承自BaseController类
    // 声明一个TeacherSubmittedService类型的私有成员变量
    private final TeacherSubmittedService teacherSubmittedService;
    // 构造函数，依赖注入TeacherSubmittedService
    public TeacherSubmittedController(TeacherSubmittedService teacherSubmittedService) {
        this.teacherSubmittedService = teacherSubmittedService;
    }

    /**
     * 用于更新学生作业信息的方法，接收一个StudentHomework对象作为请求体，并通过@Validated注解进行参数校验
     * 返回一个ResultVO对象作为响应结果
     * @param studentHomework
     * @return
     */
    @PutMapping // 处理HTTP请求为PUT方法的URL
    public ResultVO updateStudentHomework(@Validated @RequestBody StudentHomework studentHomework) {
        return teacherSubmittedService.updateStudentHomework(studentHomework);
    }

    /**
     * 通过学生作业ID获取学生作业信息的方法
     * 接收一个Long类型的studentHomeworkId作为路径参数，并返回一个ResultVO对象作为响应结果
     * @param studentHomeworkId
     * @return
     */
    @GetMapping("/{studentHomeworkId}")// 处理HTTP请求为GET方法的URL，并带有路径参数studentHomeworkId
    public ResultVO getStudentHomework(@PathVariable("studentHomeworkId") Long studentHomeworkId) {
        return teacherSubmittedService.getStudentHomework(studentHomeworkId);
    }

    /**
     * 获取学生作业列表的总页数的方法
     * 接收四个可选参数，分别是homeworkId、homeworkTitle、studentId和studentName，并返回一个ResultVO对象作为响应结果
     * @param homeworkId
     * @param homeworkTitle
     * @param studentId
     * @param studentName
     * @return
     */
    @RequestMapping("/page/count")// 处理HTTP请求为GET方法的URL
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        return teacherSubmittedService.getPageCount(homeworkId, homeworkTitle, studentId, studentName);
    }

    /**
     * 获取指定索引的学生作业列表的方法
     * 接收一个Integer类型的index作为路径参数，以及四个可选参数，返回一个ResultVO对象作为响应结果
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @param studentId
     * @param studentName
     * @return
     */
    @RequestMapping("/page/{index}")//处理HTTP请求为GET方法的URL，并带有路径参数index
    public ResultVO getPage(@PathVariable Integer index, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        return teacherSubmittedService.getPage(index, homeworkId, homeworkTitle, studentId, studentName);
    }

}

