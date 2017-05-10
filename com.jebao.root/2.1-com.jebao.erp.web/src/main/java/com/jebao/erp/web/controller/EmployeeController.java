package com.jebao.erp.web.controller;

import com.jebao.erp.service.inf.employee.IEmployeeServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Jack on 2016/11/17.
 */
@Controller
@RequestMapping("/employee/")
public class EmployeeController extends _BaseController {
    @Autowired
    private IEmployeeServiceInf employeeService;

    @RequestMapping("index")
    public String index(Model model) {
        //model.addAttribute("name","jackaa");
        return "employee/index";
    }

    @RequestMapping("upload")
    public String upload(){
        return "employee/upload";
    }

    @RequestMapping("department")
    public String department(){
        return "employee/department";
    }
    @RequestMapping("rank")
    public String rank(){
        return "employee/rank";
    }
    @RequestMapping("uploadtemplate")
    public ResponseEntity<?> uploadtemplate(){
        URL fileUrl = getClass().getResource("/static/uploadtemplates/employee.xlsx");
        if (fileUrl==null){
            return new ResponseEntity<String>("文件不存在.", HttpStatus.NOT_FOUND);
        }
        String filePath = fileUrl.getFile();
        String filename ="员工上传模板.xlsx";
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("file:" + filePath);
            if (!resource.exists()) {
                return new ResponseEntity<String>("File Not found.", HttpStatus.NOT_FOUND);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode(filename)));
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
