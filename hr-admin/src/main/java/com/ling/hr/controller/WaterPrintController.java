package com.ling.hr.controller;

import com.ling.hr.service.WaterPrintService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("common")
public class WaterPrintController {

    @Autowired
    WaterPrintService waterPrintService;

    @PostMapping("waterPrint")
    public void waterPrint(MultipartFile multipartFile, String waterMarkContent) {
        File file = new File("/opt/招生计划/" + multipartFile.getOriginalFilename());
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = file.getPath();


        String originalFilename = multipartFile.getOriginalFilename();
        BufferedOutputStream bos = null;

        // 输出的pdf文件
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File("E:/opt/转换/" + originalFilename + ".pdf")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        waterPrintService.setWatermark(bos, path, waterMarkContent);

    }
}
