package com.yi.homework.manager.student;

import com.yi.homework.dao.HomeworkDAO;
import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentCommentedManager
 * Package Name: com.yi.homework.manager.student
 *
 * @author yipple
 * @date 2020/6/19
 * @since 0.0.1
 */
@Component
public class StudentCommentedManager extends BaseManager {
    // 声明一个StudentHomeworkDAO类型的成员变量
    private final StudentHomeworkDAO studentHomeworkDAO;

    //构造函数中注入了一个StudentHomeworkDAO实例，并将其赋值给成员变量studentHomeworkDAO。
    public StudentCommentedManager(StudentHomeworkDAO studentHomeworkDAO) {
        this.studentHomeworkDAO = studentHomeworkDAO;
    }

    /**
     * 根据指定的作业ID、作业标题和学生ID查询带有教师评论的学生作业数量，
     * 并通过调用calculatePageNum方法计算并返回作业的页数。
     * @param homeworkId
     * @param homeworkTitle
     * @param studentId
     * @return
     */
    public Integer getPageCount(Long homeworkId, String homeworkTitle, Long studentId) {
        // 调用StudentHomeworkDAO的countWithTeacherComment方法查询带有教师评论的学生作业数量
        int count = studentHomeworkDAO.countWithTeacherComment(homeworkId, homeworkTitle, studentId, null);
        // 调用calculatePageNum方法计算作业的页数，并返回结果
        return calculatePageNum(count, HomeworkDAO.PAGE_SIZE);
    }

    /**
     * 根据指定的索引、作业ID、作业标题和学生ID在数据库中进行分页查询，并返回查询结果列表，
     * 包含带有教师评论的学生作业项(StudentHomeworkItemVO)。
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @param studentId
     * @return
     */
    public List<StudentHomeworkItemVO> getPage(Integer index, Long homeworkId, String homeworkTitle, Long studentId) {
        // 使用StudentHomeworkDAO的getPageWithTeacherComment方法进行分页查询，并返回结果列表
        return studentHomeworkDAO.getPageWithTeacherComment(index, homeworkId, homeworkTitle, studentId, null);
    }

}
