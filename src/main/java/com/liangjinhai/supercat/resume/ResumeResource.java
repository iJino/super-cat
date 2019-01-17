package com.liangjinhai.supercat.resume;

import com.liangjinhai.supercat.common.resultEntity.BaseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URL;

@Component
@Path("/resume")
public class ResumeResource {

//    @Value("${resume_download_path}")
//    private String resumeDownloadPath;

    private static final String downloadPath = ResourceUtils.CLASSPATH_URL_PREFIX + "static/resume/liangjinhai-resume.pdf";

    @GET
    @Path("/download")
    public Response download(@Context HttpServletResponse response) {
        File file = null;
        try {
            file = ResourceUtils.getFile(downloadPath);
            if (file.exists()) {
                response.setContentType("application/force-download");//
                response.addHeader("Content-Disposition", "attachment;fileName=" + "liangjinhai.pdf");// 设置文件名
                return Response.ok(file).build();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
