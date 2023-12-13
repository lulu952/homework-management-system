package com.yi.homework.manager;

import com.yi.homework.dao.StudentDAO;
import com.yi.homework.dao.TeacherDAO;
import com.yi.homework.model.bo.AuthInfoBO;
import com.yi.homework.model.constant.UserType;
import com.yi.homework.model.entity.Student;
import com.yi.homework.model.entity.Teacher;
import com.yi.homework.util.Md5Encrypt;
import org.springframework.stereotype.Component;

/**
 * Project Name: homework-management-system-back-end
 * File Name: UserManager
 * Package Name: com.yi.homework.manager
 *
 * @author yipple
 * @date 2020/6/17
 * @since 0.0.1
 */
@Component
public class UserManager extends BaseManager {
    // 学生数据访问对象
    private final StudentDAO studentDAO;
    // 教师数据访问对象
    private final TeacherDAO teacherDAO;
    // MD5加密对象
    private final Md5Encrypt md5Encrypt;

    public UserManager(StudentDAO studentDAO, TeacherDAO teacherDAO, Md5Encrypt md5Encrypt) {
        this.studentDAO = studentDAO;
        this.teacherDAO = teacherDAO;
        this.md5Encrypt = md5Encrypt;
    }

    // 根据登录ID和用户类型获取认证信息
    public AuthInfoBO getAuthInfoByLoginId(String userId, Integer userType) {
        switch (userType) {
            case UserType.STUDENT: // 如果用户类型是学生
                // 返回从学生数据访问对象获取的认证信息
                return AuthInfoBO.fromStudent(studentDAO.selectByStringId(userId));
            case UserType.TEACHER: // 如果用户类型是教师
                // 返回从教师数据访问对象获取的认证信息
                return AuthInfoBO.fromTeacher(teacherDAO.selectByStringId(userId));
            default:
                return null; // 如果用户类型不是学生也不是教师，则返回空
        }
    }
    // 检查用户ID是否有效
    public Boolean checkIsUserIdValid(String userId, Integer userType) {
        switch (userType) {
            case UserType.STUDENT: // 如果用户类型是学生
                // 返回从学生数据访问对象获取的学生对象是否为空
                return studentDAO.selectByStringId(userId) == null;
            case UserType.TEACHER: // 如果用户类型是教师
                // 返回从教师数据访问对象获取的教师对象是否为空
                return teacherDAO.selectByStringId(userId) == null;
            default:
                return false; // 如果用户类型不是学生也不是教师，则返回false
        }
    }

    // 注册用户
    public Boolean register(String userId, String username, String password, Integer userType) {
        switch (userType) {
            case UserType.STUDENT:  // 如果用户类型是学生
                Student student = new Student();
                student.setStudentId(Long.valueOf(userId));
                student.setStudentName(username);
                student.setPassword(passwordMd5(password)); // 对密码进行MD5加密
                // 插入学生对象到数据库中，并返回插入是否成功
                return studentDAO.insert(student) > 0;
            case UserType.TEACHER: // 如果用户类型是教师
                Teacher teacher = new Teacher();
                teacher.setTeacherId(Long.valueOf(userId));
                teacher.setTeacherName(username);
                teacher.setPassword(passwordMd5(password)); // 对密码进行MD5加密
                // 插入教师对象到数据库中，并返回插入是否成功
                return teacherDAO.insert(teacher) > 0;
            default:
                return false; // 如果用户类型不是学生也不是教师，则返回false
        }
    }

    public String passwordMd5(String password) {
        String reversedPassword = new StringBuffer(password).reverse().toString();
        return md5Encrypt.getHexString(password + reversedPassword);
    }

}
