package com.gxl.design.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 账户请求
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 16:09
 */
@Data
@AllArgsConstructor
public class AccountRequest {
    private String account;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 取款金额
     */
    private BigDecimal amount;
    /**
     * 是否允许透支
     */
    private boolean overdraw;
    /**
     * 透支额度
     */
    private BigDecimal overdrawBlance;
}
