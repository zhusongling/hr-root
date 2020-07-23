package com.ling.hr.service;

import java.io.BufferedOutputStream;

public interface WaterPrintService {
    /**
     * 添加水印
     *
     * @param bos              输出位置
     * @param input            输入文件
     * @param waterMarkContent 水印内容
     */
    void setWatermark(BufferedOutputStream bos, String input, String waterMarkContent);
}
