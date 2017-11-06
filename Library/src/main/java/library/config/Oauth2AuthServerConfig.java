package library.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}
	@Bean
	public DefaultAccessTokenConverter accessTokenConverter() {
	    return new DefaultAccessTokenConverter();
	}

	  @Override
	  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	    oauthServer
	      .tokenKeyAccess("permitAll()")
	      .checkTokenAccess("isAuthenticated()");
	  }
	  
	  @Override
	  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	    clients
	      .inMemory()
	        .withClient("client")
	          .secret("password")
	          .authorizedGrantTypes("password","authorization_code", "refresh_token")
	          .accessTokenValiditySeconds(3600)
	          .refreshTokenValiditySeconds(28*24*3600)
	          .scopes("read","write");
	  }

	  @Override
	  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    endpoints
	      .tokenStore(tokenStore())
	      .authenticationManager(authenticationManager)
	      .accessTokenConverter(accessTokenConverter())
	      .tokenEnhancer(tokenEnhancer());
	  }

	  @Bean
	  public TokenStore tokenStore() {
	    return new JdbcTokenStore(dataSource);
	  }
}
