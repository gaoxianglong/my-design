package com.gxl.design.strategy;

/**
 * 支付渠道
 *
 * @author gxl
 * @version Id: 1.0.0
 * @since 2024/4/4 09:52
 */
public enum PaymentChannel {
    ALIPAY(0, "支付宝"), WECHATPAY(1, "微信支付");

    int code;
    String desc;

    PaymentChannel(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PaymentChannel getByCode(int code) {
        for (PaymentChannel payChannel : PaymentChannel.values()) {
            if (payChannel.code == code) {
                return payChannel;
            }
        }
        return null;
    }
}
