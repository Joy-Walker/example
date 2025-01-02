package com.test.jd.enums;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-08 14:43:00
 */
public class SaveCacheServiceImpl implements SaveService{
    @Override
    public void save(Object obj) {
        System.out.println("保存到cache中");
    }
}
