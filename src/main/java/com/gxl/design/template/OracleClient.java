package com.gxl.design.template;

import org.springframework.stereotype.Service;

/**
 * Oracle客户端
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 20:54
 */
@Service
public class OracleClient extends DatabaseTemplate {
    @Override
    public Result doExecute(String sql) {
        return Result.buildResult(true, String.format("oracle,sql:%s", sql));
    }

    @Override
    public void init() {
        System.out.println("oracle init...");
    }

    @Override
    public void close() {
        System.out.println("oracle close...");
    }
}
