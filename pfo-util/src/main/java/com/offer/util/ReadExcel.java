package com.offer.util;

import com.offer.pojo.PfoQuestion;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows() {
        return totalRows;
    }
    //获取总列数
    public int getTotalCells() {
        return totalCells;
    }
    //获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }
    /** * 读EXCEL文件，获取信息集合
     * * @param fielName
     * * @return
     * */
    public List<PfoQuestion> getExcelInfo(MultipartFile mFile) {
        List<PfoQuestion> questionList = new ArrayList<>();
        String fileName = mFile.getOriginalFilename();
        //获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            questionList = createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        } return questionList;
    }
    /**
     *  * 根据excel里面的内容读取客户信息
     *  * @param is 输入流
     *  * @param isExcel2003 excel是2003还是2007版本
     *  * @return
     *  * @throws IOException
     *  */
    public List<PfoQuestion> createExcel(InputStream is, boolean isExcel2003) {
        List<PfoQuestion> questionList = new ArrayList<>();
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            questionList = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }
    /** * 读取Excel里面客户的信息
     * * @param wb
     * * @return
     * */
    private List<PfoQuestion> readExcelValue(Workbook wb) {
        List<PfoQuestion> questionList = new ArrayList<>();
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            PfoQuestion question = new PfoQuestion();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            question.setQuestionContent(cell.getStringCellValue());//内容
                        }
                    } else if (c == 1) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                            question.setQuestionType(cell.getStringCellValue());//类型
                        }
                    } else if (c == 2) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                            question.setQuestionSubject(cell.getStringCellValue());//科目
                        }
                    } else if (c == 3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String level = String.valueOf(cell.getNumericCellValue());
                            question.setQuestionLevel(level.substring(0, level.length()-2>0?level.length()-2:1));//难度
                        }else{
                            question.setQuestionLevel(cell.getStringCellValue());//难度
                        }
                    } else if (c == 4){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String timeStr = String.valueOf(cell.getNumericCellValue());
                            timeStr = timeStr.substring(0, timeStr.length()-2>0?timeStr.length()-2:1);
                            int time = Integer.parseInt(timeStr);
                            question.setStandardTime(time);//标准时间
                        }
                    } else if (c == 5) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                            question.setAnswer(cell.getStringCellValue());//标准答案
                        }
                    } else if (c == 6) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                            question.setAnswerAnalysis(cell.getStringCellValue());//答案分析
                        }
                    } else if (c == 7) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                            question.setAnswerNote(cell.getStringCellValue());//答案备注
                        }
                    } else if (c == 8){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String weighStr = String.valueOf(cell.getNumericCellValue());
                            weighStr = weighStr.substring(0, weighStr.length()-2>0?weighStr.length()-2:1);
                            int weigh = Integer.parseInt(weighStr);
                            question.setStandardTime(weigh);//分值
                        }
                    }
                }
            }
            // 添加到list
            questionList.add(question);
        }
        return questionList;
    }
    /**
     * * 验证EXCEL文件
     * * * @param filePath
     * * @return
     * */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
