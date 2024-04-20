package com.gxl.design;

import com.gxl.design.state.AccountRequest;
import com.gxl.design.state.AccountService;
import com.gxl.design.strategy.PaymentService;
import com.gxl.design.template.DatabaseTemplate;
import com.gxl.design.template.MysqlClient;
import com.gxl.design.template.OracleClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

/**
 * 启动器
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/4 09:48
 */
public class Starter {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

//        // 策略模式的运用
//        PaymentService paymentService = context.getBean(PaymentService.class);
//        System.out.println(paymentService.payment(new BigDecimal(100), new BigDecimal(90), 0));
//        System.out.println(paymentService.payment(new BigDecimal(100), new BigDecimal(180), 1));
//        System.out.println(paymentService.payment(new BigDecimal(100), new BigDecimal(10), 2));
//        System.out.println();

        {
            // 责任链模式的运用
            com.gxl.design.handler.AccountService accountService = context.getBean("accountService_", com.gxl.design.handler.AccountService.class);
            System.out.println(accountService.withdraw(new com.gxl.design.handler.AccountRequest("123456", new BigDecimal(100), new BigDecimal(90), false, null)));
            System.out.println(accountService.withdraw(new com.gxl.design.handler.AccountRequest("123456", new BigDecimal(100), new BigDecimal(200), false, null)));
            System.out.println(accountService.withdraw(new com.gxl.design.handler.AccountRequest("123456", new BigDecimal(100), new BigDecimal(200), true, new BigDecimal(300))));
            System.out.println(accountService.withdraw(new com.gxl.design.handler.AccountRequest("123456", new BigDecimal(100), new BigDecimal(200), true, new BigDecimal(100))));
            System.out.println();
        }

//        // 状态模式的运用
//        com.gxl.design.state.AccountService accountService = context.getBean("accountService", com.gxl.design.state.AccountService.class);
//        System.out.println(accountService.withdraw(new AccountRequest("123456", new BigDecimal(100), new BigDecimal(90), false, null)));
//        System.out.println(accountService.withdraw(new AccountRequest("123456", new BigDecimal(100), new BigDecimal(200), false, null)));
//        System.out.println(accountService.withdraw(new AccountRequest("123456", new BigDecimal(100), new BigDecimal(200), true, new BigDecimal(300))));
//        System.out.println(accountService.withdraw(new AccountRequest("123456", new BigDecimal(100), new BigDecimal(200), true, new BigDecimal(100))));
//        System.out.println();
//
//        // 模版方法模式
//        DatabaseTemplate mysqlClient = context.getBean("mysqlClient", MysqlClient.class);
//        mysqlClient.execute("SELECT * FROM TABLE WHERE id = 1");
//        DatabaseTemplate oracleClient = context.getBean("oracleClient", OracleClient.class);
//        oracleClient.execute("UPDATE TABLE SET name = ? WHERE id = ?");
    }
}
