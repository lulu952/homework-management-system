package com.yi.homework.manager;

/**
 * Project Name: homework-management-system-back-end
 * File Name: BaseManager
 * Package Name: com.yi.homework.manager
 *
 * @author yipple
 * @date 2020/6/16
 * @since 0.0.1
 */
public class BaseManager {
    // 计算页数方法，根据记录数和页面大小计算需要的页数
    protected int calculatePageNum(int recordNum, int pageSize) {
        // 使用三元运算符判断是否有余数，如果有余数，则页数加1，否则直接除以页面大小
        return recordNum % pageSize == 0 ? recordNum / pageSize : recordNum / pageSize + 1;
    }

}
