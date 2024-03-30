package com.example.client;

import com.example.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-22 22:22:00
 */
@FeignClient(name = "userClient",url = "http://127.0.0.1:10000/aaa")
public interface UserClient extends UserApi {
}
