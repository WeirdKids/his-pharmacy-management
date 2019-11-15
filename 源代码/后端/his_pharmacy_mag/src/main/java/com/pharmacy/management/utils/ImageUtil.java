package com.pharmacy.management.utils;

import java.io.*;

/**
 * @author 徐奥飞
 * @date 2019-11-4 :20:01
 * 图片输入输出流工具类
 */
public class ImageUtil {
    //读取本地图片获取输入流
    public static FileInputStream readImage(String path) throws IOException {
        return new FileInputStream(new File(path));
    }

    //读取表中图片获取输出流
    public static void readBin2Image(InputStream in, String targetPath) {
        File file = new File(targetPath);
        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
