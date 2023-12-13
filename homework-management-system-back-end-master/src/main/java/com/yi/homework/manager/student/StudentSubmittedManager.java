package com.yi.homework.manager.student;

import com.yi.homework.dao.HomeworkDAO;
import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.request.UpdateStudentHomeworkItemVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentSubmittedManager
 * Package Name: com.yi.homework.manager.student
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@Component
public class StudentSubmittedManager extends BaseManager {
    // 创建一个私有变量studentHomeworkDAO
    private final StudentHomeworkDAO studentHomeworkDAO;
    // 构造方法
    public StudentSubmittedManager(StudentHomeworkDAO studentHomeworkDAO) {
        this.studentHomeworkDAO = studentHomeworkDAO;
    }
    // 更新学生作业
    public Boolean updateStudentHomework(StudentHomework studentHomework) {
        // 根据学生作业ID获取原始学生作业
        StudentHomework originalStudentHomework = studentHomeworkDAO.selectById(studentHomework.getStudentHomeworkId());

        // 如果原始学生作业的教师评论不为空，返回false
        if (originalStudentHomework.getTeacherComment() != null) {
            return false;
        }

        // 更新学生作业并返回更新是否成功
        return studentHomeworkDAO.updateById(studentHomework) > 0;
    }

    // 根据视图对象更新学生作业
    public Boolean updateStudentHomeworkByVO(UpdateStudentHomeworkItemVO updateStudentHomeworkItemVO) {
        // 创建一个新的学生作业对象并设置相关属性
        StudentHomework updatedStudentHomework = new StudentHomework();
        updatedStudentHomework.setStudentHomeworkId(updateStudentHomeworkItemVO.getStudentHomeworkId());
        updatedStudentHomework.setStudentId(updateStudentHomeworkItemVO.getStudentId());
        updatedStudentHomework.setHomeworkId(updateStudentHomeworkItemVO.getHomeworkId());
        updatedStudentHomework.setTitle(updateStudentHomeworkItemVO.getTitle());
        updatedStudentHomework.setContent(updateStudentHomeworkItemVO.getContent());

        // 调用updateStudentHomework方法更新学生作业
        return updateStudentHomework(updatedStudentHomework);
    }

    // 获取学生作业
    public StudentHomeworkItemVO getStudentHomework(Long studentId, Long homeworkId) {
        // 根据学生ID和作业ID获取学生作业
        return studentHomeworkDAO.getStudentHomeworkVO(studentId, homeworkId);
    }

    // 获取页数
    public Integer getPageCount(Long homeworkId, String homeworkTitle, Long studentId) {
        // 获取满足条件的学生作业数量
        int count = studentHomeworkDAO.countWithoutTeacherComment(homeworkId, homeworkTitle, studentId, null);
        // 计算页数并返回
        return calculatePageNum(count, HomeworkDAO.PAGE_SIZE);
    }
    // 获取指定页的学生作业列表
    public List<StudentHomeworkItemVO> getPage(Integer index, Long homeworkId, String homeworkTitle, Long studentId) {
        // 根据页数和其他参数获取学生作业列表
        return studentHomeworkDAO.getPageWithoutTeacherComment(index, homeworkId, homeworkTitle, studentId, null);
    }

}
