package com.yi.homework.service.student;

import com.yi.homework.manager.student.StudentSubmittedManager;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.request.UpdateStudentHomeworkItemVO;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentSubmittedService
 * Package Name: com.yi.homework.service.student
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@Service
public class StudentSubmittedService extends BaseService {
    // 声明一个StudentSubmittedManager类型的私有变量，用于处理学生提交的作业信息
    private final StudentSubmittedManager studentSubmittedManager;

    // 构造函数，接收一个StudentSubmittedManager参数，并将其赋值给studentSubmittedManager变量
    public StudentSubmittedService(StudentSubmittedManager studentSubmittedManager) {
        this.studentSubmittedManager = studentSubmittedManager;
    }

    /**
     * 更新学生作业的方法，接收一个UpdateStudentHomeworkItemVO对象作为参数
     * @param updateStudentHomeworkItemVO
     * @return
     */
    public ResultVO updateStudentHomework(UpdateStudentHomeworkItemVO updateStudentHomeworkItemVO) {
        // 调用studentSubmittedManager的updateStudentHomeworkByVO方法，根据传入的VO对象更新学生作业
        if (studentSubmittedManager.updateStudentHomeworkByVO(updateStudentHomeworkItemVO)) {
            // 更新成功，返回一个成功的ResultVO对象，消息为"更新作业成功"
            return result("更新作业成功");
        } else {
            // 更新失败，返回一个失败的ResultVO对象，消息为"老师已点评，不能更新作业"
            return failedResult("老师已点评，不能更新作业");
        }
    }

    /**
     * 获取学生作业的方法，接收一个作业ID作为参数
     * @param homeworkId
     * @return
     */
    public ResultVO getStudentHomework(Long homeworkId) {
        // 调用studentSubmittedManager的getStudentHomework方法，根据学生ID和作业ID获取学生作业信息
        return result(studentSubmittedManager.getStudentHomework(getUserId(), homeworkId));
    }

    /**
     * 获取学生作业页数的方法，接收一个作业ID和作业标题作为参数
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        // 调用studentSubmittedManager的getPageCount方法，根据作业ID、作业标题和学生ID获取作业页数
        return result(studentSubmittedManager.getPageCount(homeworkId, homeworkTitle, getUserId()));
    }

    /**
     * 获取学生作业某一页的方法，接收一个页码、作业ID和作业标题作为参数
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public ResultVO getPage(Integer index, Long homeworkId, String homeworkTitle) {
        // 调用studentSubmittedManager的getPage方法，根据页码、作业ID、作业标题和学生ID获取作业某一页的信息
        return result(studentSubmittedManager.getPage(index, homeworkId, homeworkTitle, getUserId()));
    }

}
