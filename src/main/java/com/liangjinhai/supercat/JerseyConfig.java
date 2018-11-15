package com.liangjinhai.supercat;


import com.liangjinhai.supercat.resume.ResumeResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ResumeResource.class);
    }
}
