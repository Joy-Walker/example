package com.test;

import java.util.*;

public class Test {

    static class Group {
        String groupName;
        List<Integer> tasks;

        public Group(String groupName, List<Integer> tasks) {
            this.groupName = groupName;
            this.tasks = tasks;
        }

        @Override
        public String toString() {
            return "{\"groupName\": \"" + groupName + "\", \"tasks\": " + tasks + "}";
        }
    }

    public static List<Group> getPaginatedData(List<Group> data, int pageSize, int pageNo) {
        List<Group> paginatedData = new ArrayList<>();
        int startIndex = (pageNo - 1) * pageSize;
        int endIndex = startIndex + pageSize;

        // 记录任务索引
        int taskCount = 0;

        for (Group group : data) {
            List<Integer> tasksToAdd = new ArrayList<>();
            for (Integer task : group.tasks) {
                if (taskCount >= startIndex && taskCount < endIndex) {
                    tasksToAdd.add(task);
                }
                taskCount++;
            }
            // 只有当该组中有任务符合分页条件时，才加入分页结果
            if (!tasksToAdd.isEmpty()) {
                paginatedData.add(new Group(group.groupName, tasksToAdd));
            }
            if (taskCount >= endIndex) {
                break;
            }
        }

        return paginatedData;
    }

    public static void main(String[] args) {
        // 示例数据
        List<Group> data = new ArrayList<>();
        data.add(new Group("aaa", Arrays.asList(1, 3, 5)));
        data.add(new Group("bbb", Arrays.asList(2, 4, 6)));

        // 设置每页的最大任务数和页码
        int pageSize = 4;
        int pageNo = 2; // 获取第二页

        // 获取分页后的数据
        List<Group> paginatedData = getPaginatedData(data, pageSize, pageNo);

        // 输出分页结果
        System.out.println("分页结果:");
        paginatedData.forEach(System.out::println);

        // 设置 pageNo 为 1 获取第一页
        pageNo = 1;
        paginatedData = getPaginatedData(data, pageSize, pageNo);

        // 输出第一页的分页结果
        System.out.println("分页结果:");
        paginatedData.forEach(System.out::println);
    }
}
