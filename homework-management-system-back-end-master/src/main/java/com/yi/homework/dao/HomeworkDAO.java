package com.yi.homework.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yi.homework.dao.mapper.HomeworkMapper;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.response.table.HomeworkItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project Name: homework-management-system-back-end
 * File Name: HomeworkDAO
 * Package Name: com.yi.homework.dao.mapper
 *
 * @author yipple
 * @date 2020/6/15
 * @since 0.0.1
 */
@Repository
public class HomeworkDAO extends BaseDAO {
    // 定义分页每页大小
    public static final int PAGE_SIZE = 7;
    // HomeworkMapper 对象
    private final HomeworkMapper homeworkMapper;
    // 构造函数，接收 HomeworkMapper 对象作为参数
    public HomeworkDAO(HomeworkMapper homeworkMapper) {
        this.homeworkMapper = homeworkMapper;
    }

    // 插入一条 Homework 记录
    public int insert(Homework homework) {
        return homeworkMapper.insert(homework);
    }

    // 根据 homeworkId 删除一条 Homework 记录
    public int deleteById(Long homeworkId) {
        return homeworkMapper.deleteById(homeworkId);
    }

    // 根据 homeworkId 更新一条 Homework 记录
    public int updateById(Homework homework) {
        return homeworkMapper.updateById(homework);
    }

    // 根据 homeworkId 查询一条 Homework 记录
    public Homework selectById(Long homeworkId) {
        return homeworkMapper.selectById(homeworkId);
    }

    // 根据 homeworkId 和 homeworkTitle 统计符合条件的 Homework 记录数
    public int count(Long homeworkId, String homeworkTitle) {
        return homeworkMapper.count(homeworkId, homeworkTitle);
    }

    // 分页查询 Homework 记录，并返回 HomeworkItemVO 的列表
    public List<HomeworkItemVO> getPage(Integer index, Long homeworkId, String homeworkTitle) {
        Page<HomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return homeworkMapper.getPage(page, homeworkId, homeworkTitle).getRecords();
    }

    // 根据 studentId、homeworkId 和 homeworkTitle 统计学生剩余的 Homework 记录数
    public int countStudentRestHomework(Long studentId, Long homeworkId, String homeworkTitle) {
        return homeworkMapper.countStudentRestHomework(studentId, homeworkId, homeworkTitle);
    }

    // 分页查询学生剩余的 Homework 记录，并返回 HomeworkItemVO 的列表
    public List<HomeworkItemVO> getStudentRestHomeworkPage(Integer index, Long studentId, Long homeworkId, String homeworkTitle) {
        Page<HomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return homeworkMapper.getStudentRestHomeworkPage(page, studentId, homeworkId, homeworkTitle).getRecords();
    }

    // 根据 teacherId、homeworkId 和 homeworkTitle 统计教师的 Homework 记录数
    public int countTeacherHomework(Long teacherId, Long homeworkId, String homeworkTitle) {
        return homeworkMapper.countTeacherHomework(teacherId, homeworkId, homeworkTitle);
    }

    // 分页查询教师的 Homework 记录，并返回 HomeworkItemVO 的列表
    public List<HomeworkItemVO> getTeacherHomeworkPage(Integer index, Long teacherId, Long homeworkId, String homeworkTitle) {
        Page<HomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return homeworkMapper.getTeacherHomeworkPage(page, teacherId, homeworkId, homeworkTitle).getRecords();
    }

    // 通过作业ID获取作业项的值对象（Value Object）。
    public HomeworkItemVO getHomeworkItemVO(Long homeworkId) {
        return homeworkMapper.getHomeworkItemVO(homeworkId);
    }

}
