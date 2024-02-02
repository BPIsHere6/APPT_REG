package com.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;


public class ImageBase64Util {

    public static void main(String[] args) {
        String imageFile = "E:\\图片\\hospital\\重庆市江北区精神卫生中心.jpg";// 待处理的图片
        System.out.println(getImageString(imageFile));
    }

    public static String getImageString(String imageFile) {
        InputStream is = null;
        try {
            byte[] data = null;
            is = Files.newInputStream(new File(imageFile).toPath());
            data = new byte[is.available()];
            is.read(data);
            return new String(Base64.encodeBase64(data));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                    is = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
