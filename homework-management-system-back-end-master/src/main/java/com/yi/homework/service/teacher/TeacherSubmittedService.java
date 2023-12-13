package com.yi.homework.service.teacher;

import com.yi.homework.manager.teacher.TeacherSubmittedManager;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.BaseService;
import org.springframework.stereotype.Component;

/**
 * Project Name: homework-management-system-back-end
 * File Name: TeacherSubmittedService
 * Package Name: com.yi.homework.service.teacher
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */

/**
 * TeacherSubmittedService 类是一个组件 (@Component)，扩展自 BaseService 类。它提供了一组方法来处理教师提交的业务。
 */
@Component
public class TeacherSubmittedService extends BaseService {
    // 声明一个私有的TeacherSubmittedManager变量，用来管理教师提交的业务
    private final TeacherSubmittedManager teacherSubmittedManager;

    // 构造函数，用来注入TeacherSubmittedManager依赖
    public TeacherSubmittedService(TeacherSubmittedManager teacherSubmittedManager) {
        this.teacherSubmittedManager = teacherSubmittedManager;
    }

    /**
     * 更新学生作业的方法，接收一个StudentHomework对象作为参数
     * @param studentHomework
     * @return
     */
    public ResultVO updateStudentHomework(StudentHomework studentHomework) {
        // 调用TeacherSubmittedManager的updateStudentHomework方法
        if (teacherSubmittedManager.updateStudentHomework(studentHomework)) {
            return result("点评学生作业成功"); // 成功时返回结果为"点评学生作业成功
        } else {
            return failedResult("点评学生作业失败"); // 失败时返回结果为"点评学生作业失败"
        }
    }

    /**
     * 获取学生作业的方法，接收一个学生作业ID作为参数
     * @param studentHomeworkId
     * @return
     */
    public ResultVO getStudentHomework(Long studentHomeworkId) {
        // 调用TeacherSubmittedManager的getStudentHomework方法并返回结果
        return result(teacherSubmittedManager.getStudentHomework(studentHomeworkId));
    }

    /**
     * 获取分页数的方法，接收作业ID、作业标题、学生ID、学生姓名作为参数
     * @param homeworkId
     * @param homeworkTitle
     * @param studentId
     * @param studentName
     * @return
     */
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        // 调用TeacherSubmittedManager的getPageCount方法并返回结果
        return result(teacherSubmittedManager.getPageCount(getUserId(), homeworkId, homeworkTitle, studentId, studentName));
    }

    /**
     * 获取指定页数的方法，接收页数、作业ID、作业标题、学生ID、学生姓名作为参数
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @param studentId
     * @param studentName
     * @return
     */
    public ResultVO getPage(Integer index, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        // 调用TeacherSubmittedManager的getPage方法并返回结果
        return result(teacherSubmittedManager.getPage(index, getUserId(), homeworkId, homeworkTitle, studentId, studentName));
    }
}
