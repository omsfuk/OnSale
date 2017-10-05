package cn.omsfuk.onsale.controller;


import cn.omsfuk.onsale.base.Result;
import cn.omsfuk.onsale.base.ResultCache;
import cn.omsfuk.onsale.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image/upload")
public class ImageController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Result upload(@RequestParam("file") MultipartFile[] files) {
        if (!fileService.save(files)) {
            return ResultCache.INTERNAL_SERVER_ERROR;
        }
        return ResultCache.OK;
    }

}
