package com.yi.homework.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.homework.model.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * Project Name: homework-management-system-back-end
 * File Name: TeacherMapper
 * Package Name: com.yi.homework.dao.mapper
 *
 * @author yipple
 * @date 2020/6/15
 * @since 0.0.1
 */
@Repository
// TeacherMapper接口继承自BaseMapper接口，该接口可能提供了一些基本的CRUD（创建、读取、更新、删除）操作
public interface TeacherMapper extends BaseMapper<Teacher> {
}
