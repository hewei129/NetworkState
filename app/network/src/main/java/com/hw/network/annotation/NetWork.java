package com.hw.network.annotation;





import com.hw.network.NetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hewei(David)
 * @date 2020-03-25  15:27
 * @Copyright ©  Shanghai Yejia Digital Technology Co., Ltd.
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetWork {
    NetType netType() default NetType.AUTO;
}
