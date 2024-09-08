package com.hoavd.fbstore.admin.config.security;

import com.hoavd.fbstore.admin.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AdminConfig adminConfig;

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors()
      .and()
      .httpBasic().disable()
      .csrf().disable()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
      .antMatchers(adminConfig.getUriUnauthorized()).permitAll()
      .anyRequest().authenticated()
//      .anyRequest().permitAll()
      .and()
      .exceptionHandling()
      .authenticationEntryPoint(unauthorizedEntryPoint())
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .apply(new JwtConfigurer(jwtTokenProvider));
  }

  @Bean
  public AuthenticationEntryPoint unauthorizedEntryPoint() {
    return (request, response, authException) -> response
      .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
  }
}
