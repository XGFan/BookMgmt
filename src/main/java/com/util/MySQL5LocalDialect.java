package com.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class MySQL5LocalDialect extends MySQL5InnoDBDialect {

    public MySQL5LocalDialect() {
        super();
        registerFunction("convert", new SQLFunctionTemplate(Hibernate.STRING,
                "convert(?1 using ?2)"));
        // 使用上面一种注册方法后仍报错，所以换下面一种，缺点不灵活，写死转换gbk
        registerFunction("convert_gbk", new SQLFunctionTemplate(
                Hibernate.STRING, "convert(?1 using gbk)"));
    }

}
