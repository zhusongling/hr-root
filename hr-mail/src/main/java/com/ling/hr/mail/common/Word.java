package com.ling.hr.mail.common;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Word {
    public static Template readWord() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "/tpl/word");
        Template tempWord = null;
        try {
            tempWord = cfg.getTemplate("专家一对一.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempWord;
    }

    public static File createWord() {
        Map<String, Object> dataMap = new HashMap();
        dataMap.put("orderNo", "19202360");
        dataMap.put("name", "朱松领");
        dataMap.put("subjectName", "文科综合");
        dataMap.put("score", 600);
        dataMap.put("provinceName", "河南省");
        dataMap.put("phone", "18025429771");
        dataMap.put("intentSubject", "计算机类、软件工程");
        dataMap.put("intentCollege", "郑州大学、深圳大学");
        dataMap.put("intentCity", "河南省郑州市、广东省深圳市");
        dataMap.put("excludeSubject", "哲学、医学");
        dataMap.put("excludeCity", "浙江省杭州市、浙江省温州市");
        dataMap.put("excludeCollege", "浙江大学、温州大学");
        dataMap.put("educationTypeName", "是");
        dataMap.put("accountId", "b21244993d6311e8829a00163e0a4f75");
        dataMap.put("orderId", "b21244993d6311e8829a00163e0a4f75");
        dataMap.put("remark", "备注说明");

        File file = new File("E:/test/专家一对一/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File outFile = new File(file.getPath() + "/" + "朱松领" + "_" + "19202360" + ".doc");
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            readWord().process(dataMap, out);
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
        return outFile;
    }

}
