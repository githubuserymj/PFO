package com.offer.controller;

import com.offer.util.BaiDuFaceUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("face")
public class FaceController {
    @Autowired
    BaiDuFaceUtil baiDuFaceUtil;

    @RequestMapping(value = "faceMatch",method = RequestMethod.POST)
    @ResponseBody
    public String faceMatch(@Param("image1") String image1, @Param("image2") String image2){
        int comma1 = image1.indexOf(",");
        image1 = image1.substring(comma1 + 1);
        int comma2 = image2.indexOf(",");
        image2 = image2.substring(comma1 + 1);
        return baiDuFaceUtil.match(image1,image2);
    }

}
