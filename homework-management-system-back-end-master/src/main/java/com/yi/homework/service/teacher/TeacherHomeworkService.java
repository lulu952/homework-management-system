package com.yi.homework.service.teacher;

import com.yi.homework.manager.teacher.TeacherHomeworkManager;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.request.AddHomeworkItemVO;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Project Name: homework-management-system-back-end
 * File Name: TeacherHomeworkService
 * Package Name: com.yi.homework.service.teacher
 *
 * @author yipple
 * @date 2020/6/18
 * @since 0.0.1
 */
@Service
public class TeacherHomeworkService extends BaseService {
    // 声明一个TeacherHomeworkManager对象
    private final TeacherHomeworkManager teacherHomeworkManager;

    // 构造函数，接收TeacherHomeworkManager对象作为参数，并将其赋值给teacherHomeworkManager变量
    public TeacherHomeworkService(TeacherHomeworkManager teacherHomeworkManager) {
        this.teacherHomeworkManager = teacherHomeworkManager;
    }

    /**
     * 添加作业的方法，接收AddHomeworkItemVO对象作为参数
     * @param addHomeworkItemVO
     * @return
     */
    public ResultVO addHomework(AddHomeworkItemVO addHomeworkItemVO) {
        // 设置addHomeworkItemVO对象的教师ID为当前用户的ID
        addHomeworkItemVO.setTeacherId(getUserId());

        // 调用teacherHomeworkManager的addHomeworkByVO方法来添加作业，并根据返回值决定返回成功或失败的结果
        if (teacherHomeworkManager.addHomeworkByVO(addHomeworkItemVO)) {
            return result("发布作业成功");
        } else {
            return failedResult("发布作业失败");
        }
    }

    /**
     * 删除作业的方法，接收作业ID作为参数
     * @param homeworkId
     * @return
     */
    public ResultVO deleteHomework(Long homeworkId) {
        // 调用teacherHomeworkManager的deleteHomework方法来删除指定ID的作业，并根据返回值决定返回成功或失败的结果
        if (teacherHomeworkManager.deleteHomework(homeworkId)) {
            return result("删除作业成功");
        } else {
            return failedResult("删除作业失败");
        }
    }

    /**
     * 更新作业的方法，接收一个Homework对象作为参数
     * @param homework
     * @return
     */
    public ResultVO updateHomework(Homework homework) {
        // 调用teacherHomeworkManager的updateHomework方法来更新作业，并根据返回值决定返回成功或失败的结果
        if (teacherHomeworkManager.updateHomework(homework)) {
            return result("更新作业成功");
        } else {
            return failedResult("更新作业失败");
        }
    }

    /**
     * 获取作业的方法，接收作业ID作为参数
     * @param homeworkId
     * @return
     */
    public ResultVO getHomework(Long homeworkId) {
        // 调用teacherHomeworkManager的getHomework方法来获取指定ID的作业，并将结果返回
        return result(teacherHomeworkManager.getHomework(homeworkId));
    }

    /**
     * 获取作业页数的方法，接收作业ID和作业标题作为参数
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        // 调用teacherHomeworkManager的getPageCount方法来获取作业的页数，并将结果返回
        return result(teacherHomeworkManager.getPageCount(getUserId(), homeworkId, homeworkTitle));
    }

    /**
     * 获取指定页数的作业的方法，接收页码、作业ID和作业标题作为参数
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    public ResultVO getPage(Integer index, Long homeworkId, String homeworkTitle) {
        // 调用teacherHomeworkManager的getPage方法来获取指定页码的作业，并将结果返回
        return result(teacherHomeworkManager.getPage(index, getUserId(), homeworkId, homeworkTitle));
    }

}
