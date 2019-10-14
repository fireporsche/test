package com.haishu.common.util;

import com.haishu.common.exception.BusinessException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * FileUtil
 * @author zhb
 * @date 2019/07/31
 */
public class FileUtil {
    /**
     * 创建文件目录
     * @param dir
     */
    public static void mkdir(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 保存文件
     * @param fileName
     * @param inputStream
     */
    public static void saveFile(String fileName, InputStream inputStream) {
        FileOutputStream outputStream = null;
        try {
            // 创建目录
            fileName = fileName.replace("\\", "/");
            String savePath = fileName.substring(0, fileName.lastIndexOf("/"));
            mkdir(savePath);

            // 保存文件
            outputStream = new FileOutputStream(new File(fileName));
            byte[] tmp = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(tmp)) != -1) {
                outputStream.write(tmp, 0, len);
            }
        } catch (Exception e) {
            throw new BusinessException("文件保存失败");
        } finally {
            close(outputStream);
            close(inputStream);
        }
    }

    /**
     * 关闭输入流
     * @param inputStream
     */
    public static void close(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            // TODO
        }
    }

    /**
     * 关闭输出流
     * @param outputStream
     */
    public static void close(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            // TODO
        }
    }

    public static  void main(String[] args) {
        String fileName = "d:\\file/file\\11.jpg";
        fileName = fileName.replace("\\", "/");
        String savePath = fileName.substring(0, fileName.lastIndexOf("/"));
        String name = fileName.substring(fileName.lastIndexOf("/"));
        System.out.println(savePath);
        System.out.println(name);
    }
}