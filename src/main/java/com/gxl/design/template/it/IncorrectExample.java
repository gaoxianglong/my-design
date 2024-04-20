package com.gxl.design.template.it;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 错误示范
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/7 13:11
 */
public class IncorrectExample {
    public static void main(String[] args) {
        System.out.println(execute("update table set xx=? where id = ?", "mysql"));
        System.out.println(execute("select * from table", "oracle"));
        System.out.println(execute("insert into table vales(xx,xx,xx)", "oceanbase"));
        System.out.println(execute("delete from table where id = xx", "sqlserver"));
    }

    /**
     * 选择不同的数据库执行CRUD
     *
     * @param sql
     * @param type
     * @return
     */
    static Result execute(String sql, String type) {
        if ("mysql".equals(type)) {
            Database client = new MySqlClient();
            // 初始化和获取链接
            client.init();
            // 执行CRUD
            client.execute(sql);
            // 资源清理
            client.close();
            return Result.buildResult(true, "Mysql数据库操作成功!");
        } else if ("oracle".equals(type)) {
            Database client = new OracleClient();
            client.init();
            client.execute(sql);
            client.close();
            return Result.buildResult(true, "Oracle数据库操作成功!");
        } else if ("oceanbase".equals(type)) {
            Database client = new OracleClient();
            client.init();
            client.execute(sql);
            client.close();
            return Result.buildResult(true, "Oceanbase数据库操作成功!");
        }
        return Result.buildResult(false, "不存在的数据库!");
    }

    /**
     * 定义数据库操作的基本方法
     */
    interface Database {
        /**
         * 数据库相关初始化
         */
        void init();

        /**
         * SQL执行
         *
         * @param sql
         */
        void execute(String sql);

        /**
         * 释放连接资源
         */
        void close();
    }

    /**
     * Mysql客户端
     */
    static class MySqlClient implements Database {

        @Override
        public void init() {
            System.out.println("init");
        }

        @Override
        public void execute(String sql) {
            System.out.printf("execute sql:%s\n", sql);
        }

        @Override
        public void close() {
            System.out.println("close");
        }
    }

    /**
     * Oracle客户端
     */
    static class OracleClient implements Database {

        @Override
        public void init() {
            System.out.println("init");
        }

        @Override
        public void execute(String sql) {
            System.out.printf("execute sql:%s\n", sql);
        }

        @Override
        public void close() {
            System.out.println("close");
        }
    }

    /**
     * 封装响应结果
     */
    @Data
    @AllArgsConstructor
    static class Result {
        boolean rlt;
        String msg;

        /**
         * 构建响应结果
         *
         * @param rlt
         * @param msg
         * @return
         */
        static Result buildResult(boolean rlt, String msg) {
            return new Result(rlt, msg);
        }
    }
}
