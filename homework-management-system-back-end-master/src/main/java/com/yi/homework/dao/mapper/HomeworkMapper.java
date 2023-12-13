package com.yi.homework.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.response.table.HomeworkItemVO;
import org.springframework.stereotype.Repository;

/**
 * Project Name: homework-management-system-back-end
 * File Name: HomeworkMapper
 * Package Name: com.yi.homework.dao.mapper
 *
 * @author yipple
 * @date 2020/6/15
 * @since 0.0.1
 */
@Repository
public interface HomeworkMapper extends BaseMapper<Homework> {
    // 计算具有特定作业ID和作业标题的作业数量
    Integer count(Long homeworkId, String homeworkTitle);

    // 获取具有特定作业ID和作业标题的作业的分页数据
    IPage<HomeworkItemVO> getPage(IPage<HomeworkItemVO> page, Long homeworkId, String homeworkTitle);

    // 计算特定学生未完成的作业数量
    Integer countStudentRestHomework(Long studentId, Long homeworkId, String homeworkTitle);

    // 获取特定学生未完成的作业的分页数据
    IPage<HomeworkItemVO> getStudentRestHomeworkPage(IPage<HomeworkItemVO> page, Long studentId, Long homeworkId, String homeworkTitle);

    // 计算特定教师布置的作业数量
    Integer countTeacherHomework(Long teacherId, Long homeworkId, String homeworkTitle);

    // 获取特定教师布置的作业的分页数据
    IPage<HomeworkItemVO> getTeacherHomeworkPage(Page<HomeworkItemVO> page, Long teacherId, Long homeworkId, String homeworkTitle);

    // 获取特定作业ID对应的作业条目的详细信息
    HomeworkItemVO getHomeworkItemVO(Long homeworkId);

}
