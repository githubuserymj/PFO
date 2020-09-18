package com.offer.service;

import com.offer.pojo.PfoUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
@Repository
public interface UserService {
    /**
     * @param pfoUser pfoUser的每一个不为空的属性即为查询条件，为空表示查询所有
     * @return 查询符合条件的用户
     */
    List<PfoUser> queryUsers(PfoUser pfoUser);

    /**
     * 根据pfouser参数增加新用户
     * @param pfoUser
     * @return mysql插入状态码
     */
    int addPfoUser(PfoUser pfoUser) throws NoSuchPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException;

    /**
     * 根据pfouser的参数
     * @param pfoUser
     * @return mysql更新状态码
     */
    int updatePfoUser(PfoUser pfoUser);

    /**
     * 根据userId进行用户删除
     * @param userId
     * @return
     */
    int deletePfoUser(Long userId);

    /**
     * 根据手机号找回密码
     * @param userPhone
     * @return
     */
    String queryUserPwd(String userPhone);

    /**
     * 根据手机号修改用户密码
     * @param userPhone
     * @param newUserPwd
     * @return
     */
    int updateUserPwd(String userPhone,String newUserPwd);

    /**
     * 传入数据登录
     * @param pfoUser
     * @return 返回登录状态 true成功 false失败
     */
    PfoUser userLogin(PfoUser pfoUser) throws BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, IOException;
}
