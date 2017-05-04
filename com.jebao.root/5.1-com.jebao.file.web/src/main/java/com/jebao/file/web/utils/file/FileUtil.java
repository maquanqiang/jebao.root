package com.jebao.file.web.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2016/12/16.
 */
public class FileUtil {
    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : "";
        fileExtension = fileExtension.toLowerCase();
        return fileExtension;
    }
    public static FileOutputStream getFileOutputStream(String filePath, String fileName) throws FileNotFoundException {
        String fileFullPath = Paths.get(filePath, fileName).toString();
        try {
            return new FileOutputStream(new File(fileFullPath));
        } catch (FileNotFoundException ex) {
            File parent = new File(filePath);
            if (!parent.exists()) {
                parent.mkdirs();
            }
            return new FileOutputStream(new File(fileFullPath));
        }
    }
}
