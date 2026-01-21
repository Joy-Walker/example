package com.test;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * 基于 flink 的词频统计 demo
 *
 * @author panda
 * @date 2021/7/12
 */
public class Union {

    public static void main(String[] args) throws Exception {
        
        // 使用本地模式并开启 WebUI

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> stream1 = env.fromElements("A", "B", "C");
        DataStream<String> stream2 = env.fromElements("1", "2", "3");

        DataStream<String> unionStream = stream1.union(stream2);
        unionStream.print();



        
        // 执行
        env.execute("Word Count App");

    }
}