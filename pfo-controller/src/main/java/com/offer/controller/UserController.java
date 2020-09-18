package com.offer.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.offer.constant.ConstantValue;
import com.offer.pojo.PfoUser;
import com.offer.service.UserService;
import com.offer.util.QiNiuUtil;
import com.offer.util.RSAUtil;
import com.offer.vo.ResultData;
import org.apache.ibatis.annotations.Param;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.offer.util.ImageCodeUtil;
import com.offer.util.SendSmsUtil;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("user")
//@SessionAttributes({"user"})//添加key放入session中，可添加多个
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 接收数据进行查询
     * @param pfoUser
     * @return
     */

    @RequestMapping("queryUsers")
    @ResponseBody
    public ResultData queryUsers(PfoUser pfoUser){
        ResultData resultData = new ResultData();
        List<PfoUser> userList = userService.queryUsers(pfoUser);

        if(null == userList || userList.size() == 0){
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }else {
            resultData.setCode(0);
            for(PfoUser user:userList){//不返回密码到前端
                user.setUserPassword("此操作不显示密码");
            }
            resultData.setData(userList);
        }
        return resultData;
    }

    /**
     * 接收参数用以增加新用户
     * @param session 从session中获取发送给用户的验证码
     * @param pfoUser 相关新用户注册参数
     * @param registSmsCode 用户提交上来的手机验证码
     * @return
     */
    @RequestMapping("addUser")
    @ResponseBody
    public ResultData addPfoUser(HttpSession session, PfoUser pfoUser, String registSmsCode) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException {
        ResultData resultData = new ResultData();
        if(null != pfoUser.getUserName() && !pfoUser.getUserName().equals("") && null != pfoUser.getUserPassword() && !pfoUser.getUserPassword().equals("") && null != pfoUser.getUserPhone() && !pfoUser.getUserPhone().equals("")){
            if(null == pfoUser.getUserPhoto() || pfoUser.getUserPhoto().equals("")){
                pfoUser.setUserPhoto("/pfo/img/logo/defaultUserPhoto.svg");//设置用户默认头像
            }
            PfoUser userWithName = new PfoUser();
            userWithName.setUserName(pfoUser.getUserName());
            PfoUser userWithPhone = new PfoUser();
            userWithPhone.setUserPhone(pfoUser.getUserPhone());
            ResultData checkUserName = queryUsers(userWithName);//查询其用户名防止重复注册
            ResultData checkUserPhone = queryUsers(userWithPhone);//查询其电话防止重复注册
            //查询添加的用户是否存在，存在则不添加getCode()!=0表示未查询到用户
            if(checkUserName.getCode()!=0 && checkUserPhone.getCode() != 0){
                String relSmsCode = (String) session.getAttribute("smsCode");//获取session中真正的验证码
                if(null == relSmsCode){
                    resultData.setCode(4);
                    resultData.setMessage("请发送短信验证码");
                    return resultData;
                }else{
                    //手机验证码与session中的不匹配
                    if(!relSmsCode.equals(registSmsCode)){
                        resultData.setCode(3);
                        resultData.setMessage("手机验证码输入错误");
                        return resultData;
                    }
                    else{
                        int insertStatus = userService.addPfoUser(pfoUser);
                        if(insertStatus>0){
                            resultData.setCode(0);
                            resultData.setData(pfoUser);
                        }else{
                            resultData.setCode(3);
                            resultData.setMessage("用户添加失败");
                        }
                    }
                }

            }else{
                resultData.setCode(3);
                resultData.setMessage("用户已存在");
            }
        }
        else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }

    /**
     * 接收参数进行用户数据更新
     * @param pfoUser
     * @return
     */
    @RequestMapping("updateUser")
    @ResponseBody
    public ResultData updatePfoUser(PfoUser pfoUser, @Param("userBirthStr") String userBirthStr, MultipartFile multipartFile, HttpSession session) throws IOException, ParseException {
        ResultData resultData = new ResultData();
        PfoUser user = (PfoUser) session.getAttribute("user");
        if(null == user){
            resultData.setCode(4);
            resultData.setMessage("请登录后进行操作");
            return resultData;
        }
        if(!user.getUserId().equals(pfoUser.getUserId())){
            resultData.setCode(4);
            resultData.setMessage("当前登录用户与操作所属目标用户不符，无法进行此操作！");
            return resultData;
        }

        if(null != userBirthStr && !userBirthStr.equals("")){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date userBirth = simpleDateFormat.parse(userBirthStr);
            pfoUser.setBirthday(userBirth);
            System.out.println("更新日期："+pfoUser.toString());
        }
        if(null != pfoUser.getUserId()){
            //更新用户图片
            if(null != multipartFile){
                String oldName = multipartFile.getOriginalFilename();
                String time = String.valueOf(System.currentTimeMillis());//获取当前系统时间戳
                String newName = time + oldName.substring(oldName.lastIndexOf("."));//时间戳加文件后缀组成新的文件名
                InputStream inputStream = multipartFile.getInputStream();
                FileInputStream file = (FileInputStream) inputStream;
                String imgPath = "http://"+QiNiuUtil.uploadToken(file,newName);
                pfoUser.setUserPhoto(imgPath);//保存图片路径
            }
            int updateStatus = userService.updatePfoUser(pfoUser);
            if(updateStatus>0){
                resultData.setCode(0);
                PfoUser sessionUser = (PfoUser) session.getAttribute("user");
                //每次更新也要跟新登录用户session里的用户数据
                if(null != sessionUser){
                    PfoUser checkUser = new PfoUser();
                    checkUser.setUserId(pfoUser.getUserId());
                    PfoUser updatedUser = ((List<PfoUser>) queryUsers(checkUser).getData()).get(0);
                    session.setAttribute("user",updatedUser);
                    PfoUser sessionUser1 = (PfoUser) session.getAttribute("user");
                    System.out.println("更新后session:"+sessionUser1.toString());

                }
            }else{
                resultData.setCode(3);
                resultData.setMessage("更新失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数错误");
        }
        return resultData;
    }

    /**
     * 接收userId进行用户删除
     * @param userId
     * @return
     */
    @RequestMapping("deleteUser")
    @ResponseBody
    public ResultData deletePfoUser(Long userId){
        ResultData resultData = new ResultData();
        if(null != userId){
            int deleteStatus = userService.deletePfoUser(userId);
            if(deleteStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("删除失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }

        return resultData;
    }

    /**
     * 根据手机号及验证码找回密码
     * @param userPhone
     * @param smsCode
     * @return
     */
    @RequestMapping("queryUserPwd")
    @ResponseBody
    public ResultData queryUserPwd(String userPhone,String smsCode,HttpSession session) throws BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, IOException {
        ResultData resultData = new ResultData();
        if(null != userPhone && !userPhone.equals("") && null != smsCode && smsCode != ""){
            String relSmsCode = (String) session.getAttribute("smsCode");//获取session中真正的验证码
            if(null == relSmsCode || relSmsCode.equals("")){
                resultData.setCode(4);
                resultData.setMessage("请发送短信验证码");
                return resultData;
            }
            //手机验证码与session中的不匹配
            if(relSmsCode.equals(smsCode)){
                String RsaUserPwd = userService.queryUserPwd(userPhone);
                if(null != RsaUserPwd && !RsaUserPwd.equals("")){
                    try{
                        String userPwd = RSAUtil.decrypt(RsaUserPwd,ConstantValue.RSAprivateKey);
                        resultData.setCode(0);
                        resultData.setData(userPwd);
                    }catch (IllegalArgumentException e){
                        resultData.setCode(4);
                        resultData.setMessage("密码解析失败");
                    }

                }else{
                    resultData.setCode(3);
                    resultData.setMessage("密码不存在");
                }
            }else{
                resultData.setCode(4);
                resultData.setMessage("验证码错误");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数错误");
        }
        return resultData;
    }

    /**
     * 更新用户密码
     * 参数正确后对密码加密后存入数据库
     * @param userPhone 手机号
     * @param newUserPwd 新密码
     * @param smsCode 提交上来的短信验证码
     * @param session
     * @return
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws IOException
     */
    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultData updateUserPwd(String userPhone,String newUserPwd,String smsCode,HttpSession session) throws NoSuchPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        ResultData resultData = new ResultData();
        if(null != userPhone && !userPhone.equals("") && null != newUserPwd && !newUserPwd.equals("") && null != smsCode && !smsCode.equals("")){
            String relSmsCode = (String) session.getAttribute("smsCode");//获取session中真正的验证码
            if(null == relSmsCode || relSmsCode.equals("")){
                resultData.setCode(4);
                resultData.setMessage("请发送短信验证码");
                return resultData;
            }
            if(relSmsCode.equals(smsCode)){
                String RsaNewUserPwd = "";
                try{
                    RsaNewUserPwd = RSAUtil.encrypt(newUserPwd,ConstantValue.RSApublicKey);
                }catch (IllegalArgumentException e){
                    resultData.setCode(4);
                    resultData.setMessage("密码加密失败");
                    return resultData;
                }
                int updateStatus= userService.updateUserPwd(userPhone,RsaNewUserPwd);
                if(updateStatus>0){
                    resultData.setCode(0);
                }else{
                    resultData.setCode(3);
                    resultData.setMessage("密码设置失败");
                }
            }else{
                resultData.setCode(4);
                resultData.setMessage("验证码错误");
            }

        }else{
            resultData.setCode(4);
            resultData.setMessage("参数错误");
        }
        return resultData;
    }



    /**
     * 用户登录 可通过（用户名+密码或者手机号+密码或者手机号+验证码）方式登录
     * 查询用户是否存在，存在则存入session中
     * @param name_phone 用户名或者手机号
     * @param password_smsCode 密码或者验证码
     * @param loginWay 登录方式（userAndPwd：密码登录/phoneAndSmsCode：验证码登录）
     * @param session
     * @return
     */
    @RequestMapping("userLogin")
    @ResponseBody
    public ResultData userLogin(String name_phone,String password_smsCode,String loginWay,HttpSession session) throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        ResultData resultData = new ResultData();
        if((null != name_phone && name_phone != "") && (null != password_smsCode && password_smsCode != "")){
            PfoUser user = new PfoUser();
            //用户名+密码登录
            if(loginWay.equals("userAndPwd")){
                user.setUserName(name_phone);//设置用户名
                user.setUserPassword(password_smsCode);//设置密码
            }
            //手机号+验证码登录
            else if(loginWay.equals("phoneAndSmsCode")){
                user.setUserPhone(name_phone);//设置电话号
                String relSmsCode = (String) session.getAttribute("smsCode");//获取session中真正的验证码
                if(null != relSmsCode){
                    //手机验证码与session中的不匹配
                    if(!relSmsCode.equals(password_smsCode)){
                        resultData.setCode(3);
                        resultData.setMessage("手机验证码输入错误");
                        return resultData;
                    }
                }else{
                    resultData.setCode(3);
                    resultData.setMessage("请发送短信验证码!");
                    return resultData;
                }

            }
            else{
                resultData.setCode(4);
                resultData.setMessage("参数有误");
                return resultData;
            }
            //执行登录操作
            PfoUser pfoUser = userService.userLogin(user);
            if(null != pfoUser){
                resultData.setCode(0);
                session.setAttribute("user",pfoUser);
            }else{
                resultData.setCode(3);
                resultData.setMessage("登录失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }
        return resultData;
    }

    /**
     * 删除session中的user即代表退出
     * @param session
     * @return
     */
    @RequestMapping("userLogout")
    @ResponseBody
    public ResultData userLogout(HttpSession session){
        session.removeAttribute("user");
        ResultData resultData = new ResultData();
        resultData.setCode(0);
        return resultData;
    }


    /**
     * 获取session中的user，如不存在则表明用户未登录
     * @param session
     * @return
     */
    @RequestMapping("getUser")
    @ResponseBody
    public ResultData getUser(HttpSession session,HttpServletResponse response){
        ResultData resultData = new ResultData();
        PfoUser user = (PfoUser) session.getAttribute("user");
        if(null != user){
            resultData.setCode(0);
//            System.out.println("session中的user："+user.toString());
            user.setUserPassword("此操作不显示密码");
            Cookie cookieUserId = new Cookie("userId",user.getUserId().toString());
            cookieUserId.setPath("/");
            cookieUserId.setMaxAge(5*60);
            response.addCookie(cookieUserId);
            Cookie cookieUserName = new Cookie("userName",user.getUserName());
            cookieUserName.setPath("/");
            cookieUserName.setMaxAge(5*60);
            response.addCookie(cookieUserName);

            String userSex = user.getGender();
            Cookie cookieUserSex;
            if(null == userSex){
                cookieUserSex = new Cookie("userSex","未知");
            }else{
                cookieUserSex = new Cookie("userSex",userSex);
            }
            cookieUserSex.setPath("/");
            cookieUserSex.setMaxAge(5*60);
            response.addCookie(cookieUserSex);

            Cookie cookieUserType = new Cookie("userType",user.getUserType().toString());
            cookieUserType.setPath("/");
            cookieUserType.setMaxAge(5*60);
            response.addCookie(cookieUserType);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd&HH:mm:ss");
            Cookie cookieUserBirth = new Cookie("userBirth",simpleDateFormat.format(user.getBirthday()));
            cookieUserBirth.setPath("/");
            cookieUserBirth.setMaxAge(5*60);
            response.addCookie(cookieUserBirth);

            String userEmail = user.getUserEmail();
            Cookie cookieUserEmail;
            if(null == userEmail){
                cookieUserEmail = new Cookie("userEmail","未设置");
            }else{
                cookieUserEmail = new Cookie("userEmail",userEmail);
            }
            cookieUserEmail.setPath("/");
            cookieUserEmail.setMaxAge(5*60);
            response.addCookie(cookieUserEmail);

            Integer useroCoin = user.getoCoin();
            Cookie cookieUseroCoin;
            if(null == useroCoin){
                cookieUseroCoin = new Cookie("oCoin","0");
            }else{
                cookieUseroCoin = new Cookie("oCoin",useroCoin.toString());
            }
            cookieUseroCoin.setPath("/");
            cookieUseroCoin.setMaxAge(5*60);
            response.addCookie(cookieUseroCoin);

            Cookie cookieUserPhone = new Cookie("userPhone",user.getUserPhone());
            cookieUserPhone.setPath("/");
            cookieUserPhone.setMaxAge(5*60);
            response.addCookie(cookieUserPhone);
            Cookie cookieUserPhoto = new Cookie("userPhoto",user.getUserPhoto());
            cookieUserPhoto.setPath("/");
            cookieUserPhoto.setMaxAge(5*60);
            response.addCookie(cookieUserPhoto);
            Cookie cookieUserSignature = new Cookie("userSignature",user.getUserSignature());
            cookieUserSignature.setPath("/");
            cookieUserSignature.setMaxAge(5*60);
            response.addCookie(cookieUserSignature);
//            resultData.setData(user);
        }else{
            resultData.setCode(3);
            resultData.setMessage("用户未登录");
        }
        return resultData;
    }

    /**
     * 获取图形验证码以及存入session
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("getImgCode")
    @ResponseBody
    public ResultData getImgCode(HttpSession session) throws IOException {
        ResultData resultData = new ResultData();
        //调用自己写的验证码实现类的方法生成验证码的文字和图片存储到map中
        Map<String, Object> charMap = ImageCodeUtil.generatorCharVerificationCode();
        //获取生成的验证码图片
        BufferedImage bufferedImage = (BufferedImage) charMap.get("verificationCodeImage");
        //获取map中的验证码
        String imgCode = charMap.get("verificationCode").toString();
        System.out.println("验证码:" + imgCode);
        // 将验证码存入session·
        session.setAttribute("imgCode",imgCode);
        //对图片的二进制进行base64编码后传入前台页面。
        String imgStr = ImageCodeUtil.getImgString(bufferedImage);
        if(null != imgStr && imgStr != ""){
            resultData.setCode(0);
            resultData.setData(imgStr);
        }else{
            resultData.setCode(3);
            resultData.setMessage("验证码获取失败");
        }
        return resultData;
    }

    /**
     * 获取短信验证码
     * @param session
     * @param imgCode
     * @return
     */
    @RequestMapping("getSmsCode")
    @ResponseBody
    public ResultData getSmsCode(HttpSession session,String imgCode,String phone) throws ClientException {
        ResultData resultData = new ResultData();
        String relImgCode = (String) session.getAttribute("imgCode");
        if(relImgCode.equals(imgCode)){
            String smsCode = String.valueOf((int) (Math.random() * 9000) + 1000);
            SendSmsResponse sendSmsResponse = SendSmsUtil.sendSms(phone,smsCode);
            System.out.println(sendSmsResponse.getBizId());
            System.out.println(sendSmsResponse.getCode());
            System.out.println(sendSmsResponse.getMessage());
            System.out.println(sendSmsResponse.getRequestId());
            if(sendSmsResponse.getCode().equals("OK")){
                session.setAttribute("smsCode",smsCode);
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("短信发送失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("图形验证码输入错误");
        }
        return resultData;
    }

}

