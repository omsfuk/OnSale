package cn.omsfuk.onsale.service;


import cn.omsfuk.onsale.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@PropertySource("classpath:image.properties")
public class FileService {

    public static String CLASSPATH;

    static {
        try {
            CLASSPATH = ResourceUtils.getFile("classpath:.").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Value("${image.path}")
    public String IMAGE_PATH;

    /**
     * 固定保存到指定位置
     * @return
     */
    public boolean save(MultipartFile file) {
        String extension = ".jpg";
        String fileName = file.getOriginalFilename();
        if (fileName != null && (!"".equals(fileName)) && fileName.indexOf(".") != -1) {
            extension = fileName.substring(fileName.indexOf("."));
        }

        try {
            file.transferTo(new File(IMAGE_PATH + UUIDUtil.uuid() + extension));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean save(MultipartFile[] files) {
        for (int i = 0; i < files.length; i++) {
            if (!save(files[i])) {
                return false;
            }
        }
        return true;
    }
}
