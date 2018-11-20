package com.eslink.jxlsdemo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @ClassName DelSheet
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/11/20 16:38
 * @Version 1.0
 **/
public class DelSheet {
    /**
     * 删除指定的Sheet
     *
     * @param targetFile 目标文件
     * @param sheetName  Sheet名称
     */
    public static void deleteSheet(String targetFile, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(targetFile);
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            fileWrite(targetFile, wb);
            //删除Sheet
            wb.removeSheetAt(wb.getSheetIndex(sheetName));
            fileWrite(targetFile, wb);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写隐藏/删除后的Excel文件
     *
     * @param targetFile 目标文件
     * @param wb         Excel对象
     * @throws Exception
     */
    public static void fileWrite(String targetFile, HSSFWorkbook wb) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(targetFile);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}
