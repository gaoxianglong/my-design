package com.gxl.design.state;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 账户服务类
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 16:22
 */
@Service
@Scope("prototype")
@Getter
public class AccountService {
    /**
     * 默认由普通账户状态开始
     */
    @Resource(name = "normalState")
    private AccountState defaultState;
    @Resource
    private OverdraftState overdraftState;
    private AccountRequest request;

    /**
     * 提现处理
     *
     * @param request
     * @return
     */
    public Result withdraw(AccountRequest request) {
        this.request = request;
        return defaultState.withdraw(this);
    }

    /**
     * 状态转换
     *
     * @param accountState
     */
    public void setAccountState(AccountState accountState) {
        this.defaultState = accountState;
    }
}
