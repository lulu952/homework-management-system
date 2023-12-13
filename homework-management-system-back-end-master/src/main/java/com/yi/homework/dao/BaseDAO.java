package com.yi.homework.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * Project Name: homework-management-system-back-end
 * File Name: BaseDAO
 * Package Name: com.yi.homework.dao
 *
 * @author yipple
 * @date 2020/6/15
 * @since 0.0.1
 */
public class BaseDAO {
    // 泛型方法，用于查询并返回分页结果
    <T> List<T> selectPage(BaseMapper<T> mapper, LambdaQueryWrapper<T> wrapper, int index, int size) {
        // 创建一个 Page 对象，用于描述分页信息，包括页码和每页大小
        Page<T> page = new Page<>(index, size);
        // 调用 mapper 的 selectPage 方法，传入分页对象和查询条件包装器，获得查询结果
        // 并通过 getRecords() 获取结果集合
        return mapper.selectPage(page, wrapper).getRecords();
    }

}
