package com.yi.homework.manager.student;

import com.yi.homework.dao.HomeworkDAO;
import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.table.HomeworkItemVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentHomeworkManager
 * Package Name: com.yi.homework.manager.student
 *
 * @author yipple
 * @date 2020/6/17
 * @since 0.0.1
 */
@Component // 使用@Component注解将该类声明为一个Spring组件
public class StudentHomeworkManager extends BaseManager {
    // 声明一个HomeworkDAO类型的成员变量
    private final HomeworkDAO homeworkDAO;
    // 声明一个StudentHomeworkDAO类型的成员变量
    private final StudentHomeworkDAO studentHomeworkDAO;

    // 构造函数
    public StudentHomeworkManager(HomeworkDAO homeworkDAO, StudentHomeworkDAO studentHomeworkDAO) {
        // 在构造函数中通过依赖注入将HomeworkDAO的实例赋值给成员变量
        this.homeworkDAO = homeworkDAO;
        // 在构造函数中通过依赖注入将StudentHomeworkDAO的实例赋值给成员变量
        this.studentHomeworkDAO = studentHomeworkDAO;
    }

    public Boolean submitStudentHomework(StudentHomeworkItemVO studentHomeworkItemVO) {
        StudentHomework studentHomework = new StudentHomework();
        // 从传入的studentHomeworkItemVO对象获取学生ID并设置给studentHomework对象
        studentHomework.setStudentId(studentHomeworkItemVO.getStudentId());
        // 从传入的studentHomeworkItemVO对象获取作业ID并设置给studentHomework对象
        studentHomework.setHomeworkId(studentHomeworkItemVO.getHomeworkId());
        // 从传入的studentHomeworkItemVO对象获取标题并设置给studentHomework对象
        studentHomework.setTitle(studentHomeworkItemVO.getTitle());
        // 从传入的studentHomeworkItemVO对象获取内容并设置给studentHomework对象
        studentHomework.setContent(studentHomeworkItemVO.getContent());

        // 调用studentHomeworkDAO的insert方法将studentHomework对象插入数据库，并返回插入是否成功
        return studentHomeworkDAO.insert(studentHomework) > 0;
    }

    public HomeworkItemVO getHomework(Long homeworkId) {
        // 调用homeworkDAO的getHomeworkItemVO方法查询指定作业ID的作业，然后返回作业对象
        return homeworkDAO.getHomeworkItemVO(homeworkId);
    }

    public Integer getPageCount(Long studentId, Long homeworkId, String homeworkTitle) {
        // 使用homeworkDAO的countStudentRestHomework方法查询学生未完成的作业数量
        int count = homeworkDAO.countStudentRestHomework(studentId, homeworkId, homeworkTitle);
        // 调用calculatePageNum方法计算作业的页数，并返回结果
        return calculatePageNum(count, HomeworkDAO.PAGE_SIZE);
    }

    public List<HomeworkItemVO> getPage(Integer index, Long studentId, Long homeworkId, String homeworkTitle) {
        // 使用homeworkDAO的getStudentRestHomeworkPage方法进行分页查询，并返回查询结果列表
        return homeworkDAO.getStudentRestHomeworkPage(index, studentId, homeworkId, homeworkTitle);
    }

}
