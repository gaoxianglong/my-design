package com.gxl.design.template;

/**
 * 数据库渠道模版类
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 20:50
 */
public abstract class DatabaseTemplate {
    /**
     * 模版方法,定义通用算法骨架
     *
     * @param sql
     */
    public void execute(String sql) {
        init();
        System.out.println(doExecute(sql));
        close();
    }

    /**
     * 执行CRUD
     *
     * @param sql
     */
    public abstract Result doExecute(String sql);

    /**
     * 数据库相关初始化
     */
    public abstract void init();

    /**
     * 关闭数据库连接等资源
     */
    public abstract void close();
}
