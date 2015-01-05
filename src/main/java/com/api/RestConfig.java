package com.api;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.bean.college.College;

/**
 * DATE:2014/11/24
 * TIME:22:24
 * Created by guofan on 2014/11/24
 */
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(PubApi.class);
        register(ClassApi.class);
        register(CollegeApi.class);
    }
}
