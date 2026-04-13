package com.test;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.data.message.AiMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class StreamingExample {
    public static void main(String[] args) throws InterruptedException {
        // 1. 注意这里使用的是 StreamingChatModel
        StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .apiKey("sk-a907bbc17b074d03bde1ec0ca4c08c96")
                .modelName("qwen-plus")
                .build();

        List<ChatMessage> chatHistory = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n你：");
            String input = scanner.nextLine();
            if ("quit".equalsIgnoreCase(input) || "exit".equalsIgnoreCase(input)) {
                System.out.println("再见！");
                break;
            }
            if (input.trim().isEmpty()) {
                continue;
            }
            UserMessage userMessage = new UserMessage(input);
            chatHistory.add(userMessage);
            CountDownLatch countDownLatch = new CountDownLatch(1);

            model.generate(chatHistory, new StreamingResponseHandler<AiMessage>() {
                @Override
                public void onNext(String token) {
                    // 每生成一个字/单词，都会触发这个方法
                    System.out.print(token);
                    System.out.flush(); // 强制刷新控制台输出，实现打字机效果
                }

                @Override
                public void onError(Throwable error) {
                    error.printStackTrace();
                    countDownLatch.countDown();
                }

                @Override
                public void onComplete(Response<AiMessage> response) {
                    System.out.println("\n\n[回答结束]");
                    countDownLatch.countDown();
                }
            });

            countDownLatch.await();
        }
        scanner.close();
    }
}