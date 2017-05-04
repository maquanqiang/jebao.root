package com.jebao.file.web.controller;

import com.jebao.file.web.utils.constants.Constants;
import com.jebao.file.web.utils.http.HttpClientUtil;
import com.jebao.file.web.utils.map.MapUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/16.
 */
@RestController
@RequestMapping("/proxySimple/")
public class ProxySimpleController {
    public static final String FILE_UPLOAD_SERVICE_URL = Constants.FILE_UPLOAD_SERVICE_URL;
    //通过代理指向文件上传的服务器
    //减少前台与后台的关联
    @RequestMapping("uploadFile")
    public void uploadFile(String dir, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String errorMsg= filterRequest(request);
        if(errorMsg!=null) {
            String jsonResult=String.format("{\"error\":1,\"message\":\"[上传失败]%s\",\"url\":null}",errorMsg);
            responseWrite(response, jsonResult);
            return;
        }
        Map<String, MultipartFile> multipartFileMap = ((MultipartHttpServletRequest) request).getFileMap();
        MultipartFile file=MapUtil.getFirstOrNull(multipartFileMap);
        String targetURL=String.format("%s/filePlugin/uploadFile?dir=%s",FILE_UPLOAD_SERVICE_URL, URLEncoder.encode(dir, "UTF-8"));
        String jsonResult = HttpClientUtil.FileUploadByMultipartFile(file, targetURL);
        responseWrite(response, jsonResult);
        return ;
    }
    private void responseWrite(HttpServletResponse response, String jsonResult) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResult);
    }

    private String filterRequest(HttpServletRequest request) {
        //
        Boolean isMultipartFile = request instanceof StandardMultipartHttpServletRequest;
        if (!isMultipartFile) {
            //过滤非MultipartFile的请求
            return "request is not StandardMultipartHttpServletRequest object";
        }
        //
        Map<String, MultipartFile> multipartFileMap = ((MultipartHttpServletRequest) request).getFileMap();
        MultipartFile file= MapUtil.getFirstOrNull(multipartFileMap);
        if(file==null)
        {
            return "当前文件为空，请选择文件";
        }
        return null;
    }
}
