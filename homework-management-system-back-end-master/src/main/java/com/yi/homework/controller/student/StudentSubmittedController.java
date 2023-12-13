package com.yi.homework.controller.student;

import com.yi.homework.controller.BaseController;
import com.yi.homework.model.vo.request.UpdateStudentHomeworkItemVO;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.student.StudentSubmittedService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentSubmittedController
 * Package Name: com.yi.homework.controller.student
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@RestController // 表示该类是一个Controller，负责处理HTTP请求和响应
@RequestMapping("/student/submitted") // 定义处理的URL前缀
public class StudentSubmittedController extends BaseController { // 继承自BaseController类

    private final StudentSubmittedService studentSubmittedService; // 声明一个StudentSubmittedService类型的私有成员变量

    public StudentSubmittedController(StudentSubmittedService studentSubmittedService) { // 构造函数，依赖注入StudentSubmittedService
        this.studentSubmittedService = studentSubmittedService;
    }

    /**
     * 更新学生作业的方法
     * 使用@Validated注解进行请求参数的校验，@RequestBody注解用于将请求体映射到updateStudentHomeworkItemVO对象
     * 返回一个ResultVO对象作为响应结果
     * @param updateStudentHomeworkItemVO
     * @return
     */
    @PutMapping // 处理HTTP请求为PUT方法的URL
    public ResultVO updateStudentHomework(@Validated @RequestBody UpdateStudentHomeworkItemVO updateStudentHomeworkItemVO) {
        return studentSubmittedService.updateStudentHomework(updateStudentHomeworkItemVO);
    }

    /**
     * 获取指定作业ID的学生作业的方法
     * 将路径参数homeworkId映射到方法参数Long类型的homeworkId
     * 返回一个ResultVO对象作为响应结果
     * @param homeworkId
     * @return
     */
    @GetMapping("/{homeworkId}") // 处理HTTP请求为GET方法的URL，并带有路径参数homeworkId
    public ResultVO getStudentHomework(@PathVariable("homeworkId") Long homeworkId) {
        return studentSubmittedService.getStudentHomework(homeworkId);
    }

    /**
     * 获取学生已提交作业列表的总页数的方法
     * 接收两个参数，分别是homeworkId和homeworkTitle，并返回一个ResultVO对象作为响应结果
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/count") // 处理HTTP请求为GET方法的URL
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        return studentSubmittedService.getPageCount(homeworkId, homeworkTitle);
    }

    /**
     * 获取指定索引的学生已提交作业列表的方法
     * 接收一个Integer类型的index作为路径参数，以及homeworkId和homeworkTitle两个参数，返回一个ResultVO对象作为响应结果
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/{index}") // 处理HTTP请求为GET方法的URL，并带有路径参数index
    public ResultVO getPage(@PathVariable Integer index, Long homeworkId, String homeworkTitle) {
        return studentSubmittedService.getPage(index, homeworkId, homeworkTitle);
    }

}

