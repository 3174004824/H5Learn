package com.h5education.demo.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket petApi(Environment environment) {
        Profiles of = Profiles.of("dev","test");
        boolean b = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(b)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.h5education")) //指定提供接口所在的基包
                .build();
    }
    //通过apiInfo()属性配置文档信息
    private ApiInfo apiInfo(){
        Contact contact = new Contact("王诚","http://localhost.com","3174004824@qq.com");
        return new ApiInfo("H5 API文档", // 标题
                "H5所有接口API文档", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
    /*
            @Api()用于类；
        标识这个类是swagger的资源
        　　tags–表示分组说明标签

        @ApiOperation()用于方法；
        表示一个http请求的操作
        　　value用于方法描述

        　　notes用于提示内容

        @ApiModel()用于实体类
        表示对类进行说明，用于参数用实体类接收

              value–表示对象名
              description–描述


        @ApiModelProperty()用于实体类字段
        表示对model属性的说明或者数据操作更改
        　　value–字段说明
        　　name–重写属性名字
        　　dataType–重写属性类型
        　　required–是否必填
        　　example–举例说明
        　　hidden–隐藏


        @ApiImplicitParam() 用于 controller 方法
        表示单独的请求参数
        　　name–参数ming
        　　value–参数说明
        　　dataType–数据类型
        　　paramType–参数类型
        　　example–举例说明

        @ApiImplicitParams() 用于 controller 方法，包含多个 @ApiImplicitParam


        @ApiIgnore()用于类或者方法上，可以不被swagger显示在页面上

        说明：简单的标记只需要@Api(tags="") 和 @ApiOperation(value="",notes="")
     */

}
