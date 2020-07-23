package com.ling.hr.base.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.ling.hr.base.properties.AliYunOssProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

@Component
public class OssUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AliYunOssProperties aliYunOssProperties;

    public String uploadImage(String filepath, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        logger.debug(fileName);
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        filepath += String.format("%s.%s", RandomUtil.uuid32(), suffix);
        logger.debug("文件名：" + filepath);
        InputStream inputStream = null;
        OSSClient ossClient = new OSSClient(aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret());
        try {
            inputStream = file.getInputStream();
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(aliYunOssProperties.getBucketName(), filepath, inputStream);
            // 上传结果
            String ret = putResult.getETag();
            if (StringUtil.isNotBlank(ret)) {
                return "oss/" + filepath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭client
            ossClient.shutdown();
        }
        return null;
    }

    public String uploadImage(String filepath, File file) {
        String fileName = file.getName();
        logger.debug(fileName);
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        filepath += String.format("%s.%s", RandomUtil.uuid32(), suffix);
        logger.debug("文件名：" + filepath);
        InputStream inputStream = null;
        OSSClient ossClient = new OSSClient(aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret());
        try {
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(aliYunOssProperties.getBucketName(), filepath, file);
            // 上传结果
            String ret = putResult.getETag();
            if (StringUtil.isNotBlank(ret)) {
                return filepath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭client
            ossClient.shutdown();
        }
        return null;
    }

    public String uploadImage(String filepath, File file, String collegeId) {
        String fileName = file.getName();
        logger.debug(fileName);
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        filepath += String.format("%s.%s", collegeId, suffix);
        logger.debug("文件名：" + filepath);
        InputStream inputStream = null;
        OSSClient ossClient = new OSSClient(aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret());
        try {
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(aliYunOssProperties.getBucketName(), filepath, file);
            // 上传结果
            String ret = putResult.getETag();
            if (StringUtil.isNotBlank(ret)) {
                return filepath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭client
            ossClient.shutdown();
        }
        return null;
    }

    public String uploadInputStream(String filepath, InputStream inputStream) {
        filepath += RandomUtil.uuid32();
        OSSClient ossClient = new OSSClient(aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret());
        try {
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(aliYunOssProperties.getBucketName(), filepath, inputStream);
            // 上传结果
            String ret = putResult.getETag();
            if (StringUtil.isNotBlank(ret)) {
                return "oss/" + filepath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭client
            ossClient.shutdown();
        }
        return null;
    }

    public String getUrl(OSSClient ossClient, String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl("yxs-upload", key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    public void deleteFile(String bucketName, String key) {
        OSSClient ossClient = new OSSClient(aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret());
        ossClient.deleteObject(bucketName, key); // key：文件路径+文件名
    }

    /**
     * 下载图片到本地
     *
     * @param urlString
     * @param filename
     * @param savePath
     * @throws Exception
     */
    public String download(String urlString, String filename, String savePath) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();

        return sf.getPath() + "/" + filename;
    }

}
