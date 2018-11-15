package com.liangjinhai.supercat.resume;

import com.liangjinhai.supercat.common.resultEntity.BaseResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.*;

@Component
@Path("/resume")
public class ResumeResource {
    @GET
    @Path("/download")
    public BaseResult download(HttpServletResponse response) {
        File file = new File("D://liangjinhai.pdf");
        if (file.exists()) {
            response.setContentType("application/force-download");//
            response.addHeader("Content-Disposition", "attachment;fileName=" + "liangjinhai.pdf");// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return BaseResult.success();
    }
}
