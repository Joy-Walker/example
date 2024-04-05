package com.example.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-03 10:19:00
 */
@Service
public class ClientDetailsServiceImpl implements org.springframework.security.oauth2.provider.ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails baseClientDetails = new BaseClientDetails("app1", "resourceId1", "all", "refresh_token,password,client_credentials", "", "");
        baseClientDetails.setClientSecret("123456");
        return baseClientDetails;
    }
}
