package com.springcloud.authorization.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 *  授权服务
 *
 *  @author liangjinquan
 *
 * */
@Configuration
@EnableAuthorizationServer
@SuppressWarnings("all")
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	
    /**
     * token转换器(token生成方式)
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    /**
     * token参数增强
     * @return
     */
    public TokenEnhancer getTokenEnhancer() {
    	return new TokenEnhancer() {
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				 Map<String, Object> info = new HashMap<>();
			        info.put("message", "hello world");
			        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
			        return accessToken;
			}
		};
    }

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
		
		// TokenEnhancerChain=设计模式中的组合模式。
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		List<TokenEnhancer> enhancers = new ArrayList<>();
		enhancers.add(getTokenEnhancer());
		enhancers.add(accessTokenConverter());
		enhancerChain.setTokenEnhancers(enhancers);
		// 添加tokenEnhancer
		endpoints.tokenEnhancer(enhancerChain);
		// 添加自定义授权方式
	//        endpoints.tokenGranter(getTokenGranter(endpoints));
	}

    /**
     * 自定义授权方式
     * @param endpoints
     * @return
     */
	private TokenGranter getTokenGranter(AuthorizationServerEndpointsConfigurer endpoints){
        // // 自定义授权方式
        // TokenGranter codeTokenGranter = new MyTokenGranter(authenticationManager, endpoints.getTokenServices());
        // 默认密码模式
        ResourceOwnerPasswordTokenGranter passwordTokenGranter = new ResourceOwnerPasswordTokenGranter(authenticationManager,
                endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        // // 授权码模式
        // AuthorizationCodeTokenGranter authorizationCodeTokenGranter = new AuthorizationCodeTokenGranter(endpoints.getTokenServices(),
        //         endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());


        // CompositeTokenGranter tokenGranters = new CompositeTokenGranter(Arrays.asList(codeTokenGranter, passwordTokenGranter, authorizationCodeTokenGranter));
	CompositeTokenGranter tokenGranters = new CompositeTokenGranter(Arrays.asList(passwordTokenGranter));
        return tokenGranters;
    }

	@Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	    security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("secret"))
                .scopes("admin", "user")
                .accessTokenValiditySeconds(1300)
                .refreshTokenValiditySeconds(3600)
                .authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token", "verificationCode");
//                .redirectUris("www.baidu.com");
//                .authorizedGrantTypes("refresh_token", "password", "verificationCode", "authorization_code");
// clients.inMemory()
//                 .withClient("client-a") //client端唯一标识
//                     .secret(passwordEncoder.encode("client-a-secret")) //客户端的密码，这里的密码应该是加密后的
//                     .authorizedGrantTypes("authorization_code", "password") //授权模式标识
//                     .scopes("read_user_info") //作用域
//                     // .resourceIds("resource1") //资源id
//                     .redirectUris("http://localhost:9001/callback"); //回调地址
    }


}
