package com.gxl.design.handler;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 账户服务
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/6 14:01
 */
@Service("accountService_")
public class AccountService {
    @Resource
    private NormalHandler normalHandler;
    @Resource
    private OverdraftHandler overdraftHandler;

    /**
     * 取现
     *
     * @param request
     * @return
     */
    public Result withdraw(AccountRequest request) {
        return normalHandler.withdraw(request);
    }

    @PostConstruct
    public void init() {
        // 依赖关系设置
        normalHandler.addNext(overdraftHandler);
    }
}
