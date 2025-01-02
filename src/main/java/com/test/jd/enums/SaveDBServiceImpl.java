package com.test.jd.enums;

/**
 * @author :panligang
 * @description :
 * @create :2023-05-08 14:43:00
 */
public class SaveDBServiceImpl implements SaveService{
    @Override
    public void save(Object obj) {
        System.out.println("保存在db中");
    }
}
