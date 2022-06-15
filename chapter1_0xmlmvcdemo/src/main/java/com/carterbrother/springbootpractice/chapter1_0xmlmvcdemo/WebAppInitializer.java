package com.carterbrother.springbootpractice.chapter1_0xmlmvcdemo;


import com.carterbrother.springbootpractice.chapter1_0xmlmvcdemo.config.SpringContainer;
import com.carterbrother.springbootpractice.chapter1_0xmlmvcdemo.config.SpringMvcContainer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author carterbrother
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringContainer.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcContainer.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }
}
