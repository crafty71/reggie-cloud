package com.reggie.config;

import java.util.function.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2 {

  @Bean
  public Docket createRestApi() {
    Predicate<RequestHandler> userPredicate = RequestHandlerSelectors.basePackage("com.reggie.controller");
    Predicate<RequestHandler> dishPredicate = RequestHandlerSelectors.basePackage("com.reggie.dish.controller");
    Predicate<RequestHandler> dishFlavorPredicate = RequestHandlerSelectors.basePackage("com.reggie.dishFlavor.controller");
    Predicate<RequestHandler> categoryFlavorPredicate = RequestHandlerSelectors.basePackage("com.reggie.category.controller");
//    Predicate<RequestHandler>  setMealDishPredicate = RequestHandlerSelectors.basePackage("com.reggie.setMealDish.controller");

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo(){
    return new ApiInfoBuilder()
        .title("瑞吉外卖接口文档")
        .description("描述：瑞吉外卖项目API文档")
        .contact(new Contact("crafty","https://github.com/crafty71","cristina565654121@gmail.com"))
        .license("Apache 3.0")
        .version("v1.0")
        .build();
  }
}
