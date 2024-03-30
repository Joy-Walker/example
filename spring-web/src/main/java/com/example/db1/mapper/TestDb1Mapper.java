package com.example.db1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-29 16:56:00
 */
@Mapper
public interface TestDb1Mapper {

     @Select("select count(1) from test")
     int select();

     @Insert("insert into test values (2,2)")
     int insertTest();


     @Insert("insert into test1 values (3,3)")
     int insertTest1();
}
