package com.yi.homework.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yi.homework.dao.mapper.StudentMapper;
import com.yi.homework.model.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * Project Name: homework-management-system-back-end
 * File Name: StudentDAO
 * Package Name: com.yi.homework.dao
 *
 * @author yipple
 * @date 2020/6/15
 * @since 0.0.1
 */
// 声明该类为Spring的Repository（数据访问层）组件，由Spring容器进行管理
@Repository
public class StudentDAO extends BaseDAO {
    // 声明一个常量 PAGE_SIZE，表示每页的学生数量
    public static final int PAGE_SIZE = 7;

    // 声明一个私有的成员变量 studentMapper，用于与数据库交互
    private final StudentMapper studentMapper;

    // 构造函数，接收一个StudentMapper类型的参数，并将其赋值给成员变量
    public StudentDAO(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    // 插入学生数据的方法，调用 studentMapper 的 insert 方法，并传入学生对象作为参数
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    // 根据学生ID删除学生数据的方法，调用 studentMapper 的 deleteById 方法，并传入学生ID作为参数
    public int deleteById(Long studentId) {
        return studentMapper.deleteById(studentId);
    }

    // 根据学生ID更新学生数据的方法，调用 studentMapper 的 updateById 方法，并传入学生对象作为参数
    public int updateById(Student student) {
        return studentMapper.updateById(student);
    }

    // 根据学生ID查询学生数据的方法，调用 studentMapper 的 selectById 方法，并传入学生ID作为参数
    public Student selectById(Long studentId) {
        return studentMapper.selectById(studentId);
    }

    public Student selectByStringId(String studentId) {
        // 创建 LambdaQueryWrapper 对象，用于构建条件查询的包装器
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        // 添加条件：学生ID等于指定的字符串学生ID
        wrapper.eq(Student::getStudentId, studentId);
        // 执行查询操作，调用 studentMapper 的 selectOne 方法，并传入条件包装器
        return studentMapper.selectOne(wrapper);
    }

}
