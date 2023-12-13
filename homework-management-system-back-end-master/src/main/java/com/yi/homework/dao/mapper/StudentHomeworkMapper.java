package com.yi.homework.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Repository;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentHomeworkMapper
 * Package Name: com.yi.homework.dao.mapper
 *
 * @author yipple
 * @date 2020/6/15
 * @since 0.0.1
 */
@Repository
public interface StudentHomeworkMapper extends BaseMapper<StudentHomework> {

    // 计算特定教师、作业和学生的作业数量
    Integer count(Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    // 获取特定教师、作业和学生的作业分页数据
    IPage<StudentHomeworkItemVO> getPage(IPage<StudentHomeworkItemVO> page, Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    // 计算带有教师评论的特定作业和学生的作业数量
    Integer countWithTeacherComment(Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    // 获取带有教师评论的特定作业和学生的作业分页数据
    IPage<StudentHomeworkItemVO> getPageWithTeacherComment(IPage<com.yi.homework.model.vo.response.table.StudentHomeworkItemVO> page, Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    // 计算没有教师评论的特定作业和学生的作业数量
    Integer countWithoutTeacherComment(Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    // 获取没有教师评论的特定作业和学生的作业分页数据
    IPage<StudentHomeworkItemVO> getPageWithoutTeacherComment(IPage<com.yi.homework.model.vo.response.table.StudentHomeworkItemVO> page, Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    // 获取特定学生和作业的作业详细信息
    StudentHomeworkItemVO getStudentHomework(Long studentId, Long homeworkId);

}
