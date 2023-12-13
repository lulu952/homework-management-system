package com.yi.homework.service.student;

import com.yi.homework.manager.student.StudentCommentedManager;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentCommentedService
 * Package Name: com.yi.homework.service.student
 *
 * @author yipple
 * @date 2020/6/19
 * @since 0.0.1
 */
@Service
public class StudentCommentedService extends BaseService {
    // 声明一个私有的StudentCommentedManager变量，用于管理学生点评的业务
    private final StudentCommentedManager studentCommentedManager;

    // 构造函数，用于注入StudentCommentedManager依赖
    public StudentCommentedService(StudentCommentedManager studentCommentedManager) {
        this.studentCommentedManager = studentCommentedManager;
    }

    /**
     * 获取分页数的方法，接收作业ID和作业标题作为参数
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        // 调用StudentCommentedManager的getPageCount方法并返回结果
        return result(studentCommentedManager.getPageCount(homeworkId, homeworkTitle, getUserId()));
    }

    /**
     * 获取指定页数的方法，接收页数、作业ID和作业标题作为参数
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public ResultVO getPage(Integer index, Long homeworkId, String homeworkTitle) {
        // 调用StudentCommentedManager的getPage方法并返回结果
        return result(studentCommentedManager.getPage(index, homeworkId, homeworkTitle, getUserId()));
    }

}
