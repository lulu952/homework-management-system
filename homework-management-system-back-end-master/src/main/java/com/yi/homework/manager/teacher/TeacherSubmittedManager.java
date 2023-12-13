package com.yi.homework.manager.teacher;

import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project Name: homework-management-system-back-end
 * File Name: TeacherSubmittedManager
 * Package Name: com.yi.homework.manager.teacher
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@Service
public class TeacherSubmittedManager extends BaseManager {
    // 声明一个私有成员变量，类型为StudentHomeworkDAO
    private final StudentHomeworkDAO studentHomeworkDAO;

    // 构造函数，接受一个StudentHomeworkDAO对象作为参数
    public TeacherSubmittedManager(StudentHomeworkDAO studentHomeworkDAO) {
        this.studentHomeworkDAO = studentHomeworkDAO;
    }

    /**
     * 更新学生作业的函数，接受一个StudentHomework对象作为参数，返回一个Boolean值
     * @param studentHomework
     * @return
     */
    public Boolean updateStudentHomework(StudentHomework studentHomework) {
        // 调用studentHomeworkDAO对象的updateById方法，并将studentHomework对象作为参数传递
        // updateById方法返回一个大于0的整数表示更新成功，否则表示更新失败
        return studentHomeworkDAO.updateById(studentHomework) > 0;
    }

    /**
     * 获取指定学生作业的详细信息，接受一个Long类型的studentHomeworkId参数，返回一个StudentHomeworkItemVO对象
     * @param studentHomeworkId
     * @return
     */
    public StudentHomeworkItemVO getStudentHomework(Long studentHomeworkId) {
        // 根据studentHomeworkId查询对应的学生作业对象
        StudentHomework studentHomework = studentHomeworkDAO.selectById(studentHomeworkId);
        // 调用studentHomeworkDAO对象的getStudentHomeworkVO方法，传递学生ID和作业ID作为参数
        // 返回一个StudentHomeworkItemVO对象，表示学生作业的详细信息
        return studentHomeworkDAO.getStudentHomeworkVO(studentHomework.getStudentId(), studentHomework.getHomeworkId());
    }

    /**
     * 计算分页总数的函数，接受多个参数，包括教师ID、作业ID、作业标题、学生ID和学生姓名
     * 返回一个Integer值，表示分页总数
     * @param teacherId
     * @param homeworkId
     * @param homeworkTitle
     * @param studentId
     * @param studentName
     * @return
     */
    public Integer getPageCount(Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        // 调用studentHomeworkDAO对象的count方法，传递多个参数，统计符合条件的学生作业的数量
        int count = studentHomeworkDAO.count(teacherId, homeworkId, homeworkTitle, studentId, studentName);
        // 调用calculatePageNum函数计算分页总数，传递作业DAO定义的PAGE_SIZE常量作为参数
        return calculatePageNum(count, StudentHomeworkDAO.PAGE_SIZE);
    }

    /**
     * 获取分页数据的函数，接受多个参数，包括当前页索引、教师ID、作业ID、作业标题、学生ID和学生姓名
     * 返回一个List<StudentHomeworkItemVO>集合，表示分页数据
     * @param index
     * @param teacherId
     * @param homeworkId
     * @param homeworkTitle
     * @param studentId
     * @param studentName
     * @return
     */
    public List<StudentHomeworkItemVO> getPage(Integer index, Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        // 调用studentHomeworkDAO对象的getPage方法，传递多个参数，获取指定分页的学生作业数据
        return studentHomeworkDAO.getPage(index, teacherId, homeworkId, homeworkTitle, studentId, studentName);
    }
}
