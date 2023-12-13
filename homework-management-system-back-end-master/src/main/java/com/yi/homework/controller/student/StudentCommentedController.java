package com.yi.homework.controller.student;

import com.yi.homework.controller.BaseController;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.student.StudentCommentedService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentCommentedController
 * Package Name: com.yi.homework.controller.student
 *
 * @author yipple
 * @date 2020/6/19
 * @since 0.0.1
 */
@RestController // 表示该类是一个Controller，负责处理HTTP请求和响应
@RequestMapping("/student/commented") // 定义处理的URL前缀
public class StudentCommentedController extends BaseController { // 继承自BaseController类

    private final StudentCommentedService studentCommentedService; // 声明一个StudentCommentedService类型的私有成员变量

    public StudentCommentedController(StudentCommentedService studentCommentedService) { // 构造函数，依赖注入StudentCommentedService
        this.studentCommentedService = studentCommentedService;
    }

    /**
     * 获取学生已评价作业列表的总页数的方法
     * 接收两个参数，分别是homeworkId和homeworkTitle，并返回一个ResultVO对象作为响应结果
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/count") // 处理HTTP请求为GET方法的URL
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        return studentCommentedService.getPageCount(homeworkId, homeworkTitle);
    }

    /**
     * 获取指定索引的学生已评价作业列表的方法
     * 接收一个Integer类型的index作为路径参数，以及homeworkId和homeworkTitle两个参数，返回一个ResultVO对象作为响应结果
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/{index}") // 处理HTTP请求为GET方法的URL，并带有路径参数index
    public ResultVO getPage(@PathVariable Integer index, Long homeworkId, String homeworkTitle) {
        return studentCommentedService.getPage(index, homeworkId, homeworkTitle);
    }

}
