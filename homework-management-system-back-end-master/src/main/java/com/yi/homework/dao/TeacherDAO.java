package com.yi.homework.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yi.homework.dao.mapper.TeacherMapper;
import com.yi.homework.model.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * Project Name: homework-management-system-back-end
 * File Name: TeacherDAO
 * Package Name: com.yi.homework.dao
 *
 * @author yipple
 * @date 2020/6/15
 * @since 0.0.1
 */
@Repository
public class TeacherDAO extends BaseDAO {
    // 声明一个常量，用于指定页面大小。
    public static final int PAGE_SIZE = 7;

    // 声明一个TeacherMapper属性，用于处理数据库与Teacher对象的映射。
    private final TeacherMapper teacherMapper;

    // TeacherDAO类的构造函数，接受一个TeacherMapper作为参数，用于依赖注入。
    public TeacherDAO(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    // 插入Teacher对象到数据库中。
    public int insert(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    // 根据教师ID从数据库中删除教师记录。
    public int deleteById(Long teacherId) {
        return teacherMapper.deleteById(teacherId);
    }

    // 根据教师对象的ID更新数据库中的教师记录。
    public int updateById(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }

    // 根据教师ID从数据库中查询对应的教师记录。
    public Teacher selectById(Long teacherId) {
        return teacherMapper.selectById(teacherId);
    }

    // 根据字符串形式的教师ID从数据库中查询对应的教师记录。
    public Teacher selectByStringId(String teacherId) {
        // 创建一个LambdaQueryWrapper，指定根据Teacher对象的ID属性进行等值匹配。
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTeacherId, teacherId);
        // 从数据库中查询符合条件的单个教师记录。
        return teacherMapper.selectOne(wrapper);
    }

}
