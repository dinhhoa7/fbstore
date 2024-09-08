package com.hoavd.fbstore.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class CustomerSwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .ignoredParameterTypes(AuthenticationPrincipal.class)
      .apiInfo(this.apiInfo())
      .securityContexts(Collections.singletonList(securityContext()))
      .securitySchemes(Collections.singletonList(this.apiKey()))
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.hoavd.fbstore.customer.controller"))
      .paths(PathSelectors.any())
      .build();
  }

  private ApiInfo apiInfo(){
    return new ApiInfoBuilder()
      .title("CUSTOMER")
      .description("Customer System")
      .version("1.0.0")
      .build();
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
  }

  private ApiKey apiKey() {
    return new ApiKey("Bearer", "Authorization", "header");
  }
}
