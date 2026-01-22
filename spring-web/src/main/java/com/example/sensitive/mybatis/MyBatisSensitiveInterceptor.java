package com.example.sensitive.mybatis;

import com.example.sensitive.DesensitizedUtil;
import com.example.sensitive.Sensitive;
import com.mysql.cj.xdevapi.Statement;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.Field;
import java.util.List;

@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class MyBatisSensitiveInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        List<Object> results = (List<Object>) invocation.proceed();
        for (Object obj : results) {
            if (obj == null) continue;
            processSensitiveFields(obj);
        }
        return results;
    }

    private void processSensitiveFields(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            Sensitive ann = field.getAnnotation(Sensitive.class);
            if (ann != null && field.getType() == String.class) {
                field.setAccessible(true);
                String origin = (String) field.get(obj);
                field.set(obj, DesensitizedUtil.desensitize(origin, ann.type()));
            }
        }
    }
}