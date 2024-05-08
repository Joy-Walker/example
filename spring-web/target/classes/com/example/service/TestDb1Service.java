package com.example.service;

import com.example.db1.mapper.TestDb1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-30 00:36:00
 */
@Service
public class TestDb1Service {

    @Autowired
    private TestDb1Mapper testDb1Mapper;

    @Transactional(transactionManager = "firstTransactionManager",rollbackFor = Exception.class)
//    @Transactional(transactionManager = "secondTransactionManager",rollbackFor = Exception.class)
    public int insert(){
        testDb1Mapper.insertTest();
        int i = 1 / 0;
        testDb1Mapper.insertTest1();
        return 1;
    }
}
