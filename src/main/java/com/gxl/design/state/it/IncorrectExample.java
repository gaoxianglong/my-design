package com.gxl.design.state.it;

import com.gxl.design.state.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 错误示例
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 16:45
 */
public class IncorrectExample {
    public static void main(String[] args) {
        System.out.println(withdraw(new AccountRequest("123456", new BigDecimal("100"), new BigDecimal("90"), false, new BigDecimal("0"))));
        System.out.println(withdraw(new AccountRequest("123456", new BigDecimal("100"), new BigDecimal("200"), false, new BigDecimal("0"))));
        System.out.println(withdraw(new AccountRequest("123456", new BigDecimal("50"), new BigDecimal("90"), true, new BigDecimal("100"))));
        System.out.println(withdraw(new AccountRequest("123456", new BigDecimal("50"), new BigDecimal("90"), true, new BigDecimal("50"))));
    }

    /**
     * 取现操作
     *
     * @return
     */
    public static Result withdraw(AccountRequest request) {
        BigDecimal balance = request.getBalance();
        BigDecimal amount = request.getAmount();
        BigDecimal overdrawBlance = request.getOverdrawBlance();

        // 检测账户余额是否足够
        if (balance.compareTo(amount) >= 0) {
            return Result.buildResult(true, String.format("余额足够,扣减金额:%s,剩余余额:%s", amount, balance.subtract(amount)));
        }
        // 检测是否支持透支额度
        else if (request.isOverdraw()) {
            // 检查透支额度是否足够
            if (overdrawBlance.compareTo(amount) >= 0) {
                return Result.buildResult(true, String.format("透支额度足够,扣减金额:%s,剩余透支额度:%s", amount, overdrawBlance.subtract(amount)));
            }
            // 透支额度不够时的处理策略
            else {
                return Result.buildResult(false, "透支额度不足!");
            }
        }
        return Result.buildResult(false, "余额不足!");
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
