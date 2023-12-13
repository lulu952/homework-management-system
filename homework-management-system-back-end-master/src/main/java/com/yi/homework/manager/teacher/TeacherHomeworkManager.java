package com.yi.homework.manager.teacher;

import com.yi.homework.dao.HomeworkDAO;
import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.request.AddHomeworkItemVO;
import com.yi.homework.model.vo.response.table.HomeworkItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project Name: homework-management-system-back-end
 * File Name: TeacherHomeworkManager
 * Package Name: com.yi.homework.manager.teacher
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@Component
public class TeacherHomeworkManager extends BaseManager {
    // 创建私有变量
    private final HomeworkDAO homeworkDAO;
    private final StudentHomeworkDAO studentHomeworkDAO;

    // 构造函数，接受HomeworkDAO和StudentHomeworkDAO两个参数
    public TeacherHomeworkManager(HomeworkDAO homeworkDAO, StudentHomeworkDAO studentHomeworkDAO) {
        this.homeworkDAO = homeworkDAO;
        this.studentHomeworkDAO = studentHomeworkDAO;
    }

    /**
     * 添加作业的方法，接受一个Homework对象作为参数
     * @param homework
     * @return
     */
    public Boolean addHomework(Homework homework) {
        // 调用HomeworkDAO的insert方法插入作业，并返回插入结果
        return homeworkDAO.insert(homework) > 0;
    }

    /**
     * 根据AddHomeworkItemVO对象添加作业的方法
     * @param addHomeworkItemVO
     * @return
     */
    public Boolean addHomeworkByVO(AddHomeworkItemVO addHomeworkItemVO) {
        // 创建一个新的Homework对象
        Homework homework = new Homework();
        // 从AddHomeworkItemVO对象中获取相关属性，并设置到Homework对象中
        homework.setTeacherId(addHomeworkItemVO.getTeacherId());
        homework.setHomeworkTitle(addHomeworkItemVO.getHomeworkTitle());
        homework.setHomeworkContent(addHomeworkItemVO.getHomeworkContent());
        // 调用addHomework方法，将新的Homework对象作为参数
        return addHomework(homework);
    }

    /**
     * 删除作业的方法，接受一个作业ID作为参数
     * @param homeworkId
     * @return
     */
    public Boolean deleteHomework(Long homeworkId) {
        // 调用HomeworkDAO的deleteById方法删除指定作业ID的作业，并返回删除结果
        // 同时还调用StudentHomeworkDAO的deleteStudentHomeworkByHomeworkId方法删除与该作业相关的学生作业
        return homeworkDAO.deleteById(homeworkId) > 0 && studentHomeworkDAO.deleteStudentHomeworkByHomeworkId(homeworkId) > 0;
    }

    /**
     * 更新作业的方法，接受一个Homework对象作为参数
     * @param homework
     * @return
     */
    public Boolean updateHomework(Homework homework) {
        // 调用HomeworkDAO的updateById方法更新作业信息，并返回更新结果
        return homeworkDAO.updateById(homework) > 0;
    }

    /**
     * 根据作业ID获取作业对象的方法
     * @param homeworkId
     * @return
     */
    public HomeworkItemVO getHomework(Long homeworkId) {
        // 调用HomeworkDAO的getHomeworkItemVO方法，根据作业ID获取对应的作业对象
        return homeworkDAO.getHomeworkItemVO(homeworkId);
    }

    /**
     * 获取分页总数的方法，接受教师ID、作业ID和作业标题作为参数
     * @param teacherId
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public Integer getPageCount(Long teacherId, Long homeworkId, String homeworkTitle) {
        // 调用HomeworkDAO的countTeacherHomework方法，获取符合条件的作业数量
        int count = homeworkDAO.countTeacherHomework(teacherId, homeworkId, homeworkTitle);
        // 调用父类的calculatePageNum方法计算分页总数，传入作业数量和每页显示记录数
        return calculatePageNum(count, HomeworkDAO.PAGE_SIZE);
    }

    /**
     * 获取指定页码的作业列表的方法，接受页码、教师ID、作业ID和作业标题作为参数
     * @param index
     * @param teacherId
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public List<HomeworkItemVO> getPage(Integer index, Long teacherId, Long homeworkId, String homeworkTitle) {
        // 调用HomeworkDAO的getTeacherHomeworkPage方法，获取指定页码的作业列表
        return homeworkDAO.getTeacherHomeworkPage(index, teacherId, homeworkId, homeworkTitle);
    }
}
