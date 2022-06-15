package ${package}.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 说明：swagger文档配置
 * @author ${projectAuthor}
 * 创建时间： 2020年02月03日 4:04 下午
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("${package}.web"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("${rootArtifactId}")
                        .description("${rootArtifactId},对接后端请找 ${projectAuthor}")
                        .version("1.0")
                        .contact(new Contact("${projectAuthor}","blog.csdn.net","505857426@qq.com"))
                        .license("The Apache License")
                        .licenseUrl("https://www.leshiguang.com")
                        .build());
    }
}
