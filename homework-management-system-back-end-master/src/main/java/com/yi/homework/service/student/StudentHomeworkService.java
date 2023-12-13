package com.yi.homework.service.student;

import com.yi.homework.manager.student.StudentHomeworkManager;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import com.yi.homework.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentHomeworkService
 * Package Name: com.yi.homework.service.student
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@Service
public class StudentHomeworkService extends BaseService {
    // 声明一个私有的StudentHomeworkManager变量，用于管理学生作业的业务
    private final StudentHomeworkManager studentHomeworkManager;

    // 构造函数，用于注入StudentHomeworkManager依赖
    public StudentHomeworkService(StudentHomeworkManager studentHomeworkManager) {
        this.studentHomeworkManager = studentHomeworkManager;
    }

    /**
     * 提交学生作业的方法，接收StudentHomeworkItemVO对象作为参数
     * @param studentHomeworkItemVO
     * @return
     */
    public ResultVO submitStudentHomework(StudentHomeworkItemVO studentHomeworkItemVO) {
        studentHomeworkItemVO.setStudentId(getUserId());

        // 调用StudentHomeworkManager的submitStudentHomework方法提交学生作业
        if (studentHomeworkManager.submitStudentHomework(studentHomeworkItemVO)) {
            return result("提交作业成功"); // 返回成功的结果
        } else {
            return failedResult("提交作业失败"); // 返回失败的结果
        }
    }

    /**
     * 获取作业的方法，接收作业ID作为参数
     * @param homeworkId
     * @return
     */
    public ResultVO getHomework(Long homeworkId) {
        // 调用StudentHomeworkManager的getHomework方法获取作业信息
        return result(studentHomeworkManager.getHomework(homeworkId));
    }

    /**
     * 获取分页数的方法，接收作业ID和作业标题作为参数
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        // 调用StudentHomeworkManager的getPageCount方法获取分页数，并传入用户ID作为参数
        return result(studentHomeworkManager.getPageCount(getUserId(), homeworkId, homeworkTitle));
    }

    /**
     * 获取指定页数的方法，接收页数、作业ID和作业标题作为参数
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public ResultVO getPage(Integer index, Long homeworkId, String homeworkTitle) {
        // 调用StudentHomeworkManager的getPage方法获取指定页数的作业信息，并传入用户ID作为参数
        return result(studentHomeworkManager.getPage(index, getUserId(), homeworkId, homeworkTitle));
    }

}
