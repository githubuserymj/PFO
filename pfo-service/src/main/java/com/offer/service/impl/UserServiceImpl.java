package com.offer.service.impl;

import com.offer.mapper.PfoUserMapper;
import com.offer.pojo.PfoUser;
import com.offer.pojo.PfoUserExample;
import com.offer.service.UserService;
import com.offer.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.List;
import com.offer.constant.ConstantValue;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PfoUserMapper pfoUserMapper;

    @Override
    public List<PfoUser> queryUsers(PfoUser pfoUser) {
        return pfoUserMapper.queryUsers(pfoUser);
    }

    @Override
    public int addPfoUser(PfoUser pfoUser) throws NoSuchPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp nowTime = new Timestamp(currentTimeMillis);//满足条件当前时间存入用户注册时间
        pfoUser.setRegDate(nowTime);
        //使用公钥对用户密码进行加密
        pfoUser.setUserPassword(RSAUtil.encrypt(pfoUser.getUserPassword(),ConstantValue.RSApublicKey));
        return pfoUserMapper.insertSelective(pfoUser);
    }

    @Override
    public int updatePfoUser(PfoUser pfoUser) {
        System.out.println(pfoUser.toString());
        return pfoUserMapper.updateByPrimaryKeySelective(pfoUser);
    }

    @Override
    public int deletePfoUser(Long userId) {
        return pfoUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public String queryUserPwd(String userPhone) {
        return pfoUserMapper.queryUserPwd(userPhone);
    }

    @Override
    public int updateUserPwd(String userPhone,String newUserPwd){
        return pfoUserMapper.updateUserPwd(userPhone,newUserPwd);
    }

    @Override
    public PfoUser userLogin(PfoUser pfoUser) throws BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, IOException {
        //用户名加密码登录
        if(null != pfoUser.getUserName() && pfoUser.getUserPassword()!=null){
            PfoUser tempUser = new PfoUser();
            String requestUserPwd = pfoUser.getUserPassword();//用户提交的密码
            tempUser.setUserName(pfoUser.getUserName());//设置用户提交的用户名作为搜索条件
            List<PfoUser> userList = queryUsers(tempUser);
            if(userList.size() == 1){
                PfoUser user = userList.get(0);
                String serverRsaUserPwd = user.getUserPassword();//服务器用户加密密码
                String serverUserPwd = RSAUtil.decrypt(serverRsaUserPwd,ConstantValue.RSAprivateKey);//获取解密后的用户密码,及真实密码
                if(serverUserPwd.equals(requestUserPwd)){
                    return user;
                }
            }
        }
        //验证码登录
        if(null != pfoUser.getUserPhone()){
            PfoUser tempUser = new PfoUser();
            String requestUserPhone = pfoUser.getUserPhone();//用户提交的手机号
            tempUser.setUserPhone(requestUserPhone);//设置用户提交的手机号作为搜索条件
            List<PfoUser> userList = queryUsers(tempUser);
            if(userList.size() == 1){
                return userList.get(0);
            }
        }
        return null;
    }
}
