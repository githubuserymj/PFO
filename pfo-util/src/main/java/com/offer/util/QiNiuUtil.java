package com.offer.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.net.URLEncoder;

/*
* 七牛云工具类
* */
@Component
public class QiNiuUtil {
    //从springboot的application.properties文件获取你注册的七牛云有关信息

    private static final String accessKey = "QikB14njJ-Ay2wVIWn4yt_-RKiQMsLNPL-rcFODK";
    private static final String secretKey = "AuOH65CrBQ7RUvO8VXb0tiWMnTRnvyPFR-2pVXyi";
    private static final String bucket = "pfospace";
    private static final String path = "pfoonline.online/";

//    private static final String accessKey = "XotRNE7PhPE-y03xnXYhRQUlIetQEyuNzsfmCnat";
//    private static final String secretKey = "EnXsLzHAz5av3ifZykcb57mDzogjCEYznWqRBC-O";
//    private static final String bucket = "pfomidea";
//    private static final String path = "pz1fx5091.bkt.clouddn.com/";

    /**
     * 上传图片
     *  @param file     上传的文件对象
     *  @param key      上传文件保存的文件名
     * @return  url     图片链接
     * @throws QiniuException
     */
    public static String uploadToken(FileInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            // 密钥配置
            Auth auth = Auth.create(accessKey, secretKey);
            // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
            String upToken = auth.uploadToken(bucket);
            try {
                // 调用put方法上传
                Response response = uploadManager.put(file, key, upToken, null, null);

                // 返回这张存储照片的地址
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String encodedFileName = URLEncoder.encode(putRet.key, "utf-8");
                //获取下载地址,前台可以通过img访问
                String finalUrl = String.format("%s/%s", path, encodedFileName);

                return finalUrl;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 删除文件
     * @param key
     * @return
     * @throws QiniuException
     */
    public static boolean delete(String key) throws QiniuException {
        // 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
        Configuration cfg = new Configuration(Zone.zone2());
        // 密钥配置
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth,cfg);
        Response response = bucketManager.delete(bucket, key);
        return response.statusCode == 200 ? true:false;
    }
}
