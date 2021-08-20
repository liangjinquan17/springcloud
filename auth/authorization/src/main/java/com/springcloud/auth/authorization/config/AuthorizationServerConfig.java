package com.springcloud.auth.authorization.config;

import com.springcloud.auth.authorization.oauth.password.PasswordTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 授权服务
 * @author liangjinquan
 *
 */
@Configuration
@SuppressWarnings("all")
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	@Qualifier("authUserDetailService")
	private UserDetailsService userDetailsService;
	
    /**
     * token转换器(token生成方式)
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("shenfanapu");
        return converter;
    }

    /**
     * token参数增强
     * 可存储敏感与非敏感信息
     * @return
     */
    public TokenEnhancer getTokenEnhancer() {
    	return new TokenEnhancer() {
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
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
	    endpoints.tokenGranter(getTokenGranter(endpoints));
	    // token存储、校验
	    endpoints.tokenStore(getTokenStore());
	}
	
	/**
	 * jwt解析、校验token
	 * @return
	 */
	private TokenStore getTokenStore() {
		return new RedisTokenStore(redisTemplate.getConnectionFactory());
	}

    /**
     * 自定义授权方式
     * @param endpoints
     * @return
     */
	private TokenGranter getTokenGranter(AuthorizationServerEndpointsConfigurer endpoints){
		// 密码校验
		PasswordTokenGranter passwordTokenGranter = new PasswordTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(), authenticationManager);
		// token刷新
        RefreshTokenGranter refreshTokenGranter = new RefreshTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        CompositeTokenGranter tokenGranters = new CompositeTokenGranter(Arrays.asList(passwordTokenGranter, refreshTokenGranter));
        return tokenGranters;
    }

	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		// 支持将client参数放在header或body中
        oauthServer.allowFormAuthenticationForClients();
        oauthServer.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("secret"))
				// 多端登录可以用scopes区分
                .scopes("consumer", "admin", "province")
                .accessTokenValiditySeconds(33600)
                .refreshTokenValiditySeconds(7200)
                .authorizedGrantTypes("password", "refresh_token");
    }
}
