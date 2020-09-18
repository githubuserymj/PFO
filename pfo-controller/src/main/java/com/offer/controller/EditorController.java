package com.offer.controller;

import com.offer.util.QiNiuUtil;
import com.offer.vo.EditorResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
public class EditorController {
    // 图片上传
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public EditorResultData uploadFile(
            @RequestParam("myFile") MultipartFile multipartFile,
            HttpServletRequest request) {

        try {
            //保存的文件：路径、文件名要唯一.后缀
            //文件保存的路径
            //产生一个全局的唯一的标识
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            String oldFileName  = UUID.randomUUID() + multipartFile.getOriginalFilename().substring( multipartFile.getOriginalFilename().lastIndexOf("."));
            /*//缓存路径
            String Path = "E:\\img\\"+ oldFileName;
            // 将图片写到硬盘
            multipartFile.transferTo(new File(Path));*/
//            /* url 使用浏览器的访问路径 */
//            String imgURL = "http://localhost:8080/img/"+oldFileName;
            String path = QiNiuUtil.uploadToken(inputStream, oldFileName);
            String[] str = { path };
            EditorResultData erd = new EditorResultData(str);
            return erd;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
