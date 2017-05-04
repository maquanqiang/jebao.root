package com.jebao.file.web.utils.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/12/16.
 */
public class HttpClientUtil {
    public static String FileUploadByMultipartFile(final MultipartFile file, String targetURL) throws IOException {
        HttpClient httpclient = new HttpClient();
        PostMethod filePost = new PostMethod(targetURL);
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        PartSource fisPS = new PartSource() {
            public long getLength() {
                return file.getSize();
            }

            public String getFileName() {
                return file.getOriginalFilename();
            }

            public InputStream createInputStream() throws IOException {
                return file.getInputStream();
            }
        };
        Part[] parts = new Part[]{new FilePart("file", fisPS)};
        filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
        try {
            int status = httpclient.executeMethod(filePost);
            return filePost.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
