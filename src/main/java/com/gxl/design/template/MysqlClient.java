package com.gxl.design.template;

import org.springframework.stereotype.Service;

/**
 * Mysql客户端
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 20:52
 */
@Service
public class MysqlClient extends DatabaseTemplate {
    @Override
    public Result doExecute(String sql) {
        return Result.buildResult(true, String.format("mysql,sql:%s", sql));
    }

    @Override
    public void init() {
        System.out.println("mysql init...");
    }

    @Override
    public void close() {
        System.out.println("mysql close...");
    }
}
