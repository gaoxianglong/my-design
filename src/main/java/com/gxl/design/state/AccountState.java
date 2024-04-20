package com.gxl.design.state;

/**
 * 账户状态类
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 16:08
 */
public interface AccountState {
    /**
     * 取现
     *
     * @param accountService
     * @return
     * @throws Exception
     */
    Result withdraw(AccountService accountService);

    /**
     * 判断是否支持
     *
     * @param request
     * @return
     */
    boolean isSupport(AccountRequest request);
}
