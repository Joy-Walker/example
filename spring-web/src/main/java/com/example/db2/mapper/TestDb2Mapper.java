package com.example.db2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-29 16:56:00
 */
@Mapper
public interface TestDb2Mapper {

     @Select("select count(1) from test")
     int select();
}
