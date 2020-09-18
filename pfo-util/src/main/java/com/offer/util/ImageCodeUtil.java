package com.offer.util;

//import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by YMJ on 2019-09-16.
 */
//原生AWT图形化工具包
public class ImageCodeUtil {
    //返回map，含有图片验证码的base64编码，和验证码字符
    public static Map<String, Object> generatorCharVerificationCode() {
        //  验证码图片边框宽度
        final int WIDTH = 110;
        //  验证码图片边框高度
        final int HEIGHT = 30;
        //  验证码字符长度
        int CHAR_LENGTH = 6;
        //  验证码字体高度
        int FONT_HEIGHT = HEIGHT - 3;
        //  验证码干扰线条数
        int INTERFERENCE_LINE = 15;
        //  生成验证码所需字符
        char[] charSequence = {
                'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z', '0', '1', '2', '3',
                '4', '5', '6', '7', '8', '9'
        };
        Map<String, Object> verificationCodeMap = null;

        //  生成透明rgb图片
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        //  备份原始画笔颜色
        Color color = graphics.getColor();
        graphics.setColor(Color.WHITE);
        //  图片填充黑色
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        graphics.setColor(Color.WHITE);
        //  图片填充白色；组成黑色边框的白色图片
        graphics.fillRect(1, 1, WIDTH - 2, HEIGHT - 2);

        int newFontHeight = CHAR_LENGTH > 4 ? FONT_HEIGHT * 4 / CHAR_LENGTH : FONT_HEIGHT;

        //  设置画笔字体
        Font font = new Font("微软雅黑", Font.PLAIN, newFontHeight);
        graphics.setFont(font);
        //  根据系统时间创建随机数对象
        Random random = new Random(System.currentTimeMillis());
        int r = 0;
        int g = 0;
        int b = 0;
        //  验证码字符串
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < CHAR_LENGTH; i++) {
            char ch = charSequence[random.nextInt(charSequence.length)];
            //   随机生成rgb颜色值，并设置画笔颜色
            r = random.nextInt(255);
            g = random.nextInt(255);
            b = random.nextInt(255);
            graphics.setColor(new Color(r, g, b));
            //  根据画笔颜色绘制字符
            graphics.drawString(String.valueOf(ch), i * (newFontHeight), FONT_HEIGHT);
            verificationCode.append(ch);
        }

        //  绘制干扰线
        int x1, y1, x2, y2;
        for (int i = 0; i < INTERFERENCE_LINE; i++) {
            //   随机生成rgb颜色值，并设置画笔颜色
            r = random.nextInt(255);
            g = random.nextInt(255);
            b = random.nextInt(255);
            graphics.setColor(new Color(r, g, b));
            x1 = random.nextInt(WIDTH);
            y1 = random.nextInt(HEIGHT);
            x2 = random.nextInt(WIDTH);
            y2 = random.nextInt(HEIGHT);
            //  绘制线条
            graphics.drawLine(x1, y1, x2, y2);
        }

        //  恢复画笔颜色
        graphics.setColor(color);

        verificationCodeMap = new HashMap<String, Object>();
        verificationCodeMap.put("verificationCodeImage", bufferedImage);
        verificationCodeMap.put("verificationCode", verificationCode);
        return verificationCodeMap;
    }

    //将图片转换为带图片头的base64编码以便于显示
    public static String getImgString(BufferedImage bufferedImage) throws IOException {
        //将验证码图片转换为ByteArrayOutPutStream
        ByteArrayOutputStream out  = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        //对图片的二进制进行base64编码后传入前台页面。
        BASE64Encoder encoder = new BASE64Encoder();
        byte[]  bytes = out.toByteArray();
        String imgStr= "data:image/x-icon;base64,"+encoder.encode(bytes);
        return imgStr;
    }
}