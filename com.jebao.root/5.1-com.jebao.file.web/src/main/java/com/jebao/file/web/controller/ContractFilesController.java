package com.jebao.file.web.controller;

import com.jebao.file.web.utils.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2016/12/18.
 * 合同下载
 */
@RestController
@RequestMapping("/contractFiles/")
public class ContractFilesController {
    // 图片的存储路径：public static final String ROOT = "upload-dir";//上传文件的根目录--相对地址
    public static final String ROOT = Constants.FILE_UPLOAD_DIR;//上传文件的根目录--绝对地址
    private final ResourceLoader resourceLoader;
    @Autowired
    public ContractFilesController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    /**
     * 合同的存储地址
     * http://localhost:8089//contractFiles/pdfFile/**.pdf
     */
    @RequestMapping(method = RequestMethod.GET, value = "pdfFile/**/{filename:.+}")
    public ResponseEntity<?> pdfFile(@PathVariable String filename, HttpServletRequest request) {
        String fullFileName=request.getRequestURI();
        String filePath = Paths.get(ROOT, fullFileName).toString();
        try {
            Resource resource = resourceLoader.getResource("file:" + filePath);
            if (!resource.exists()) {
                return new ResponseEntity<String>("File Not found.", HttpStatus.NOT_FOUND);
                //return ResponseEntity.notFound().build();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (Exception e) {
            return new ResponseEntity<String>("Exception Not found.", HttpStatus.NOT_FOUND);
        }
    }
}
