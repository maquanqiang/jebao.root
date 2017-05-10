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
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/18.
 */
@RestController
@RequestMapping("/attachFiles/")
public class AttachFilesController {
    // 图片的存储路径：public static final String ROOT = "upload-dir";//上传文件的根目录--相对地址
    public static final String ROOT = Constants.FILE_UPLOAD_DIR;//上传文件的根目录--绝对地址
    private final ResourceLoader resourceLoader;
    @Autowired
    public AttachFilesController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    /**
     * 兼容老系统的图片的存储地址
     * 参考：http://www.jebao.net/attachFiles/webfile/pl_Web_Show_Materials/sl_smallloan_project/6009127581728/20161216171002366.jpg
     */
    @RequestMapping(method = RequestMethod.GET, value = "webfile/**/{filename:.+}")
    public ResponseEntity<?> webFile(@PathVariable String filename, HttpServletRequest request) {
        String fullFileName=request.getRequestURI();
        String filePath = Paths.get(ROOT, fullFileName).toString();
        try {
            Resource resource = resourceLoader.getResource("file:" + filePath);
            if (!resource.exists()) {
                return new ResponseEntity<String>("File Not found.", HttpStatus.NOT_FOUND);
                //return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(resourceLoader.getResource("file:" + filePath));
        } catch (Exception e) {
            return new ResponseEntity<String>("Exception Not found.", HttpStatus.NOT_FOUND);
        }
    }
}
