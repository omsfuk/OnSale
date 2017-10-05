package cn.omsfuk.onsale.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/9/10
 */

@Component
public abstract class FileUtil {

    public static String CLASSPATH;

    @Value("${image.path}")
    public static String IMAGE_PATH;

    static {
        try {
            CLASSPATH = ResourceUtils.getFile("classpath:.").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 固定保存到指定位置
     * @return
     */
    public static boolean save(MultipartFile file) {
        String extension = "jpg";
        if (file.getName() != null && !"".equals(file.getName()) && file.getName().indexOf(".") != -1) {
            extension = file.getName().substring(file.getName().indexOf("."));
        }
        try {
            file.transferTo(new File(IMAGE_PATH + UUIDUtil.uuid() + extension));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean save(MultipartFile[] files) {
        for (int i = 0; i < files.length; i++) {
            if (!save(files[i])) {
                return false;
            }
        }
        return true;
    }






    public static String mixedWithDate(String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append(mixedDate()).append('/').append(fileName);
        return sb.toString();
    }

    private static String mixedDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder();
        sb.append(CLASSPATH).append("/static/img/").append(year).append('/').append(month)
                .append('/').append(day);
        return sb.toString();
    }

    public static File mixedWithDate(MultipartFile file) throws IOException {
        String dir = mixedDate();
        new File(dir).mkdirs();
        File fil = new File(dir + '/' + file.getOriginalFilename());
        file.transferTo(fil);
        return fil;
    }

    public static File save(String fileName, MultipartFile file) {
        File fil = new File(CLASSPATH + "/static/img/" + fileName);
        try {
            file.transferTo(fil);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fil;
    }
}
