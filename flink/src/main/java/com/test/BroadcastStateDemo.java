package com.test;

import org.apache.flink.api.common.state.*;
import org.apache.flink.api.common.typeinfo.*;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.*;

/**
 * 1、每一个算子中都会维护一份广播出来的数据
 * 将规则广播到所有算子的并发实例中
 *
 * 2、数据丰富这种场景下，广播状态会比较有用
 */
public class BroadcastStateDemo {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 用户行为数据流：格式 "user1,login"
        DataStream<String> dataStream = env.fromElements(
                "user1,login",
                "user2,click",
                "user3,logout"
        );

        // 广播规则流（行为白名单）
        DataStream<String> ruleStream = env.fromElements("login", "logout");

        // 广播状态描述符：存储允许的行为集合
        MapStateDescriptor<String, String> ruleStateDescriptor =
                new MapStateDescriptor<>(
                        "rules",
                        BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO);

        // 将规则流广播
        BroadcastStream<String> broadcastRuleStream = ruleStream.broadcast(ruleStateDescriptor);

        // 连接数据流和广播流
        BroadcastConnectedStream<String, String> connectedStream =
                dataStream.connect(broadcastRuleStream);

        // 处理逻辑
        DataStream<String> result = connectedStream.process(
                new BroadcastProcessFunction<String, String, String>() {


                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                    }

                    @Override
                    public void processElement(
                            String value,
                            ReadOnlyContext ctx,
                            Collector<String> out) throws Exception {
                        String[] parts = value.split(",");
                        String user = parts[0];
                        String action = parts[1];

                        ReadOnlyBroadcastState<String, String> broadcastState =
                                ctx.getBroadcastState(ruleStateDescriptor);

                        if (broadcastState.contains(action)) {
                            out.collect("允许行为: " + value);
                        } else {
                            out.collect("不允许行为: " + value);
                        }
                    }

                    @Override
                    public void processBroadcastElement(
                            String rule,
                            Context ctx,
                            Collector<String> out) throws Exception {
                        BroadcastState<String, String> broadcastState =
                                ctx.getBroadcastState(ruleStateDescriptor);
                        broadcastState.put(rule, rule); // 简单地存储行为名作为 key/value
                    }
                }
        );

        result.print();
        env.execute("Flink Broadcast State Simple Demo");
    }
}
