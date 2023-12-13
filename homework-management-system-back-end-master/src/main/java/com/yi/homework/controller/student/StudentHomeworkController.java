package com.yi.homework.controller.student;

import com.yi.homework.controller.BaseController;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import com.yi.homework.service.student.StudentHomeworkService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentHomeworkController
 * Package Name: com.yi.homework.controller.student
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@RestController // 表示该类是一个Controller，负责处理HTTP请求和响应
@RequestMapping("/student/homework") // 定义处理的URL前缀
public class StudentHomeworkController extends BaseController { // 继承自BaseController类

    private final StudentHomeworkService studentHomeworkService; // 声明一个StudentHomeworkService类型的私有成员变量

    public StudentHomeworkController(StudentHomeworkService studentHomeworkService) { // 构造函数，依赖注入StudentHomeworkService
        this.studentHomeworkService = studentHomeworkService;
    }

    /**
     * 提交学生作业的方法
     * 使用@Validated注解进行请求参数的校验，@RequestBody注解用于将请求体映射到studentHomeworkItemVO对象
     * 返回一个ResultVO对象作为响应结果
     * @param studentHomeworkItemVO
     * @return
     */
    @PostMapping // 处理HTTP请求为POST方法的URL
    public ResultVO submitHomework(@Validated @RequestBody StudentHomeworkItemVO studentHomeworkItemVO) {
        return studentHomeworkService.submitStudentHomework(studentHomeworkItemVO);
    }

    /**
     * 获取指定作业ID的学生作业的方法
     * 将路径参数homeworkId映射到方法参数Long类型的homeworkId
     * 返回一个ResultVO对象作为响应结果
     * @param homeworkId
     * @return
     */
    @GetMapping("/{homeworkId}") // 处理HTTP请求为GET方法的URL，并带有路径参数homeworkId
    public ResultVO getHomework(@PathVariable("homeworkId") Long homeworkId) {
        return studentHomeworkService.getHomework(homeworkId);
    }

    /**
     * 获取学生作业列表的总页数的方法
     * 接收两个参数，分别是homeworkId和homeworkTitle，并返回一个ResultVO对象作为响应结果
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/count") // 处理HTTP请求为GET方法的URL
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        return studentHomeworkService.getPageCount(homeworkId, homeworkTitle);
    }

    /**
     * 获取指定索引的学生作业列表的方法
     * 接收一个Integer类型的index作为路径参数，以及homeworkId和homeworkTitle两个参数，返回一个ResultVO对象作为响应结果
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/{index}") // 处理HTTP请求为GET方法的URL，并带有路径参数index
    public ResultVO getPage(@PathVariable Integer index, Long homeworkId, String homeworkTitle) {
        return studentHomeworkService.getPage(index, homeworkId, homeworkTitle);
    }

}

