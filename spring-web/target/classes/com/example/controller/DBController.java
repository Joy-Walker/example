package com.example.controller;

import com.example.db1.mapper.TestDb1Mapper;
import com.example.db2.mapper.TestDb2Mapper;
import com.example.service.TestDb1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :panligang
 * @description : xxx
 * @create :2024-03-29 17:10:00
 */

@RestController
public class DBController {

    Logger logger = LoggerFactory.getLogger(DBController.class);

    @Autowired
    TestDb1Mapper db1Mapper;

    @Autowired
    TestDb2Mapper db2Mapper;

    @Autowired
    TestDb1Service db1Service;


    @RequestMapping("/db1")
    public int db1() {
        logger.debug("====================");
        return db1Mapper.select();
    }
    @RequestMapping("/db2")
    public int db2() {
        return db2Mapper.select();
    }


    @RequestMapping("/insert")
    public int insert() {
        return db1Service.insert();
    }
}
