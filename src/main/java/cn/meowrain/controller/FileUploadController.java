package cn.meowrain.controller;

import cn.meowrain.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Value("${file-save-path}")
    private String fileSavePath;
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam(value = "file")MultipartFile file, HttpServletRequest req) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy.MM.dd/");
        String originFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString()  + "." + originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String format = sdf.format(new Date());
        String realPath = fileSavePath + format;
        File folder = new File(realPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        file.transferTo(new File(realPath + fileName));
        String respUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploads/" + format + "/" + fileName ;
        return Result.success(respUrl);
    }
}
