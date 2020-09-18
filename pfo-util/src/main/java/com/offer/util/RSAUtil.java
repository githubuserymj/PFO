package com.offer.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import com.offer.constant.ConstantValue;
/**
 * Created by YMJ on 2019-09-20.
 */
//RSA加密工具类
public class RSAUtil {
    //最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117;
    //最大解密明文大小
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取秘钥对(公钥与私钥)
     * KeyPair类有公钥与私钥两个属性
     * @return
     */
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        //初始化秘钥大小
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static PrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //生成RSA秘钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //base64解码
        byte[]decodeKey = Base64.decodeBase64(privateKey.getBytes());
        //编码
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeKey);

        //秘钥工厂生成私钥
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     * @param publickey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String publickey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //生成RSA秘钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //base64解码
        byte[]decodeKey = Base64.decodeBase64(publickey);
        //编码
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodeKey);
        //秘钥工厂生成公钥
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密数据(调用时传入数据数据和公钥即可加密)
     * @param data 待加密数据
     * @param publicKeyStr 公钥(已存入常量类)
     * @return 经过加密与base64编码后的数据
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static String encrypt(String data,String publicKeyStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException {
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        int inputLen  = data.getBytes().length;//获取输入数据的字节长度
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int offset = 0;
        byte[]cache;
        int i = 0;
        //对数据进行分段加密
        while (inputLen - offset >0){
            if(inputLen - offset > MAX_ENCRYPT_BLOCK){
                cache = cipher.doFinal(data.getBytes(),offset,MAX_ENCRYPT_BLOCK);
            }else{
                cache = cipher.doFinal(data.getBytes(),offset,inputLen-offset);
            }
            outputStream.write(cache,0,cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        //将加密后的数据转化为字节数组
        byte[]encryptedData = outputStream.toByteArray();
        outputStream.close();
        //将字节数组进行base64编码后返回（所提获取数据应将其先进行base64解码，再用私钥进行RSA解密可得原数据）
        return new String(Base64.encodeBase64(encryptedData));
    }

    /**
     * RSA解密(调用时传入数据和私钥即可解密)
     * @param data 待解密数据
     * @param privateKeyStr 私钥(已存入常量类)
     * @return
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String decrypt(String data,String privateKeyStr) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        //先进行base64解码
        byte[]base64DecodedDataBytes = Base64.decodeBase64(data);
        int inputLen  = base64DecodedDataBytes.length;//获取输入数据的字节长度
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int offset = 0;
        byte[]cache;
        int i = 0;
        // 对数据分段解密
           while (inputLen - offset > 0) {
               if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(base64DecodedDataBytes, offset, MAX_DECRYPT_BLOCK);
               } else {
                 cache = cipher.doFinal(base64DecodedDataBytes,offset,inputLen-offset);
                     }
           outputStream.write(cache, 0, cache.length);
             i++;
            offset = i * MAX_DECRYPT_BLOCK;
           }
        byte[] decryptedData = outputStream.toByteArray();
         outputStream.close();
             // 解密后的内容
              return new String(decryptedData, "UTF-8");
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException {//
////        //确保后端有唯一的公钥与私钥
//////        KeyPair keyPair = getKeyPair();        //初始化秘钥大小并生成秘钥对
//////        String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));//获取base64编码后的公钥
//////        String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));//获取base64编码后的私钥
//////        System.out.println("公钥："+publicKey);
//////        System.out.println("私钥："+privateKey);
//        String data = "在";
//////        String encryptData = encrypt(data,publicKey);
//        String encryptData = encrypt(data,ConstantValue.RSApublicKey);
//        System.out.println("加密后的内容："+encryptData);
////
//////        String decryptData = decrypt(encryptData, privateKey);
//        String decryptData = decrypt(encryptData, ConstantValue.RSAprivateKey);
//        System.out.println("解密后的数据："+decryptData);
////
//    }

}
