package com.api;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

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
        register(BookApi.class);
        register(SupplierApi.class);
        register(CourseApi.class);
        register(CourseBkApi.class);
    }
}
