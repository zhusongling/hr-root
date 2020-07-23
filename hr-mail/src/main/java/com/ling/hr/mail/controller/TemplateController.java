package com.ling.hr.mail.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("template")
public class TemplateController {

    /**
     * 读取模板
     *
     * @return
     */
    private Template readWord() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "/ftl/");
        Template tempWord = null;
        try {
            tempWord = cfg.getTemplate("template.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempWord;
    }

    /**
     * 生成word
     */
    @PostMapping(value = "create")
    public void create() {
        // 组装数据处理
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("orderNo", "2004230003");
        dataMap.put("nickName", "张大胖");
        dataMap.put("subjectName", "理科综合");
        dataMap.put("score", "600");
        dataMap.put("batchName", "本科一批");

        List wordList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Map<String, Object> temp = new HashMap();
            temp.put("title", "志愿" + (i + 1));
            temp.put("enrollId", "600" + i);
            temp.put("enrollName", "郑州大学");

            for (int j = 0; j < 6; j++) {

                temp.put("majorName" + (j + 1), "[" + (j + 1) + 1 + "] " + "软件工程");
            }
            String risk = "4" + i;
            Double riskValue = Double.valueOf(risk);
            temp.put("percent", "4" + i);
            if (riskValue > 80) {
                temp.put("level", "高风险");
            } else if (riskValue > 50) {
                temp.put("level", "较高风险");
            } else if (riskValue > 30) {
                temp.put("level", "较低风险");
            } else {
                temp.put("level", "极低风险");
            }
            wordList.add(temp);

        }
        dataMap.put("wordList", wordList);

        File file = new File("/opt/");
        if (!file.exists()) {
            file.mkdirs();
        }

//            File outFile = new File("本科一批理科" + ".doc");
        File outFile = new File(file.getPath() + "/" + "2004230003" + ".doc");

        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.readWord().process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 重命名
     */
    @PostMapping(value = "rename")
    public void rename() {
        File file = new File("E:/opt/");
        File[] files = file.listFiles(); // 读取文件夹
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            String tempName = fileName.substring(0, fileName.indexOf("."));
            files[i].renameTo(new File("E:/opt/" + tempName + ".doc"));
        }
    }
}
