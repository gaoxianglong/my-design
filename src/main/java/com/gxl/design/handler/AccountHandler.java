package com.gxl.design.handler;

import lombok.Getter;


/**
 * 账户相关责任链接口
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 13:29
 */
@Getter
public abstract class AccountHandler {
    private AccountHandler nextHandler;

    /**
     * 支付
     *
     * @param request
     * @return
     */
    abstract Result withdraw(AccountRequest request);

    /**
     * 设置下一个处理者
     *
     * @param handler
     * @return
     */
    public void addNext(AccountHandler handler) {
        this.nextHandler = handler;
    }

    /**
     * 是否支持
     *
     * @param request
     * @return
     */
    abstract boolean isSupport(AccountRequest request);
}
