package com.example.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-03 10:17:00
 */
@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Qualifier(value = "clientDetailsServiceImpl")
    @Autowired
    ClientDetailsService clientDetailsService;

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    AuthorizationCodeServices authorizationCodeServices;

//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService);
//    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//                .inMemory()
//                .withClient("client1")
//                .secret("{noop}secret")
//                .authorizedGrantTypes("authorization_code",
//                        "password", "client_credentials", "implicit", "refresh_token")
//                .scopes("all")
//                .autoApprove(false)
//                .redirectUris("http://www.baidu.com")
//                .accessTokenValiditySeconds(3600);
        clients.withClientDetails(clientDetailsService);
    }


    @Bean
    public AuthorizationServerTokenServices tokenService(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);//客户端详情,从容器中获取
        tokenServices.setTokenStore(new InMemoryTokenStore());//token的存储方式,保存在内存中
        tokenServices.setSupportRefreshToken(true);//支持刷新令牌
        tokenServices.setAccessTokenValiditySeconds(3600);//token的有效期
        tokenServices.setRefreshTokenValiditySeconds(7200);//刷新token的有效期
        return tokenServices;
    }



    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 限制哪些客户端可以调用获取令牌的接口
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .allowFormAuthenticationForClients();
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)//认证管理器,密码模式时使用
        endpoints .authorizationCodeServices(authorizationCodeServices)//授权码服务，授权码模式时使用
                .tokenServices(tokenService())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//允许以POST方式获取令牌
    }



    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        //授权码保存在内存中
        return new InMemoryAuthorizationCodeServices();
    }

}
