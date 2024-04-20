package com.gxl.design.state;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 支付结果
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 18:39
 */
@Data
@AllArgsConstructor
public class Result {
    private boolean rlt;
    private String msg;

    /**
     * 构建结果
     *
     * @param rlt
     * @param msg
     * @return
     */
    public static Result buildResult(boolean rlt, String msg) {
        return new Result(rlt, msg);
    }
}
