package com.ling.hr.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.ling.hr.service.WaterPrintService;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.IOException;

@Service
public class WaterPrintServiceImpl implements WaterPrintService {

    @Override
    public void setWatermark(BufferedOutputStream bos, String input, String waterMarkContent) {
        // 使用"||"将内容进行分割
        String[] waterMarkContents = waterMarkContent.split("\\|\\|");

        PdfReader reader = null;
        try {
            reader = new PdfReader(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 获取总页数 +1, 下面从1开始遍历
        int total = reader.getNumberOfPages() + 1;

        PdfStamper stamper = null;
        BaseFont base = null;
        try {
            stamper = new PdfStamper(reader, bos);
            base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 间隔
        int interval = 20;
        // 获取水印文字的最大高度和宽度
        int textH = 0, textW = 0;
        for (int j = 0; j < waterMarkContents.length; j++) {
            JLabel label = new JLabel();
            label.setText(waterMarkContents[j]);
            FontMetrics metrics = label.getFontMetrics(label.getFont());
            if (textH < metrics.getHeight()) {
                textH = metrics.getHeight();
            }
            if (textW < metrics.stringWidth(label.getText())) {
                textW = metrics.stringWidth(label.getText());
            }

            // 设置水印透明度
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.4f);
            gs.setStrokeOpacity(0.4f);

            Rectangle pageSizeWithRotation = null;
            PdfContentByte content = null;
            for (int i = 1; i < total; i++) {
                content = stamper.getOverContent(i);
                content.saveState();
                content.setGState(gs);

                // 设置字体和字体大小
                content.beginText();
                content.setFontAndSize(base, 20);

                // 设置颜色
                content.setColorFill(BaseColor.RED);

                // 获取每一页的高度、宽度
                pageSizeWithRotation = reader.getPageSizeWithRotation(i);
                float pageHeight = pageSizeWithRotation.getHeight();
                float pageWidth = pageSizeWithRotation.getWidth();

                // 根据纸张大小多次添加， 水印文字成30度角倾斜
                for (int height = interval + textH; height < pageHeight; height = height + textH * 12) {
                    for (int width = interval + textW; width < pageWidth + textW; width = width + textW * 3) {
                        // 将分段的字段进行输出编写
                        for (int z = 0; z < waterMarkContents.length; z++) {
                            content.showTextAligned(Element.ALIGN_LEFT, waterMarkContents[z], (width - textW) + 70, height - (textH + 20) * (z + 3), 30);
                        }
                    }
                }
                content.endText();
            }

            // 关闭流
            try {
                stamper.close();
                reader.close();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
