package com.yi.homework.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yi.homework.dao.mapper.StudentHomeworkMapper;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentHomework
 * Package Name: com.yi.homework.dao
 *
 * @author yipple
 * @date 2020/6/15
 * @since 0.0.1
 */
// 声明该类为Spring的Repository（数据访问层）组件，由Spring容器进行管理
@Repository
public class StudentHomeworkDAO extends BaseDAO {
    // 声明一个常量，每页的数据量为7条
    public static final int PAGE_SIZE = 7;

    // 声明一个成员变量，用于与数据库交互的StudentHomeworkMapper
    private final StudentHomeworkMapper studentHomeworkMapper;

    // 构造函数，接收一个StudentHomeworkMapper类型的参数，并将其赋值给成员变量studentHomeworkMapper
    public StudentHomeworkDAO(StudentHomeworkMapper studentHomeworkMapper) {
        this.studentHomeworkMapper = studentHomeworkMapper;
    }

    // 插入学生作业数据的方法，调用studentHomeworkMapper的insert方法，并传入学生作业对象作为参数
    public int insert(StudentHomework studentHomework) {
        return studentHomeworkMapper.insert(studentHomework);
    }

    // 根据学生作业ID删除学生作业数据的方法，调用studentHomeworkMapper的deleteById方法，并传入学生作业ID作为参数
    public int deleteById(Long studentHomeworkId) {
        return studentHomeworkMapper.deleteById(studentHomeworkId);
    }

    // 根据学生作业ID更新学生作业数据的方法，调用studentHomeworkMapper的updateById方法，并传入学生作业对象作为参数
    public int updateById(StudentHomework studentHomework) {
        return studentHomeworkMapper.updateById(studentHomework);
    }

    // 根据学生作业ID查询学生作业数据的方法，调用studentHomeworkMapper的selectById方法，并传入学生作业ID作为参数
    public StudentHomework selectById(Long studentHomeworkId) {
        return studentHomeworkMapper.selectById(studentHomeworkId);
    }

    // 统计学生作业数据条数的方法，调用studentHomeworkMapper的count方法，并传入相关查询参数
    public int count(Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        return studentHomeworkMapper.count(teacherId, homeworkId, homeworkTitle, studentId, studentName);
    }

    public List<StudentHomeworkItemVO> getPage(Integer index, Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        // 创建Page对象，用于分页查询，指定页码和页面大小
        Page<StudentHomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        // 执行分页查询，调用studentHomeworkMapper的getPage方法，并获取查询结果的记录列表
        return studentHomeworkMapper.getPage(page, teacherId, homeworkId, homeworkTitle, studentId, studentName).getRecords();
    }

    // 统计带有教师评论的学生作业数据条数的方法，调用studentHomeworkMapper的countWithTeacherComment方法，并传入相关查询参数
    public int countWithTeacherComment(Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        return studentHomeworkMapper.countWithTeacherComment(homeworkId, homeworkTitle, studentId, studentName);
    }

    public List<StudentHomeworkItemVO> getPageWithTeacherComment(Integer index, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        // 创建Page对象，用于分页查询，指定页码和页面大小
        Page<StudentHomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        // 执行带有教师评论的分页查询，调用studentHomeworkMapper的getPageWithTeacherComment方法，并获取查询结果的记录列表
        return studentHomeworkMapper.getPageWithTeacherComment(page, homeworkId, homeworkTitle, studentId, studentName).getRecords();
    }

    // 此方法根据提供的参数统计没有教师评论的学生作业记录数量。
    public int countWithoutTeacherComment(Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        // 调用studentHomeworkMapper中的countWithoutTeacherComment方法，传入给定的参数。
        return studentHomeworkMapper.countWithoutTeacherComment(homeworkId, homeworkTitle, studentId, studentName);
    }

    // 此方法根据提供的参数检索一页没有教师评论的StudentHomeworkItemVO对象。
    public List<StudentHomeworkItemVO> getPageWithoutTeacherComment(Integer index, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        // 创建一个Page对象，指定索引和PAGE_SIZE（假设为常量）以进行分页。
        Page<StudentHomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        // 调用studentHomeworkMapper中的getPageWithoutTeacherComment方法，传入Page对象和其他参数。
        // 获取页面中的记录，并将它们作为List返回。
        return studentHomeworkMapper.getPageWithoutTeacherComment(page, homeworkId, homeworkTitle, studentId, studentName).getRecords();
    }

    // 此方法根据提供的ID检索特定学生和作业的StudentHomeworkItemVO对象。
    public StudentHomeworkItemVO getStudentHomeworkVO(Long studentId, Long homeworkId) {
        // 调用studentHomeworkMapper中的getStudentHomework方法，传入提供的studentId和homeworkId。
        return studentHomeworkMapper.getStudentHomework(studentId, homeworkId);
    }

    // 此方法删除与特定作业ID相关联的学生作业记录。
    public int deleteStudentHomeworkByHomeworkId(Long homeworkId) {
        // 创建LambdaQueryWrapper以指定删除条件（homeworkId匹配）。
        LambdaQueryWrapper<StudentHomework> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StudentHomework::getHomeworkId, homeworkId);
        // 调用studentHomeworkMapper中的delete方法，使用LambdaQueryWrapper删除记录。
        return studentHomeworkMapper.delete(lambdaQueryWrapper);
    }

}
