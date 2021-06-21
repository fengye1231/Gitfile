package com.bs.bus.common;

import com.bs.bus.entity.Score;
import com.bs.sys.common.excel.ExcelUtil;
import com.bs.sys.service.IDeptService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScoreExcelParse {

    @Autowired
    private IDeptService deptService;
    //Autowired注解,spring不推荐使用静态方法,在非controller中使用的话需要在类加载之前初始化autowired服务，再进行使用

    public static ScoreExcelParse scoreExcelParse;

    @PostConstruct
    public void init() {
        scoreExcelParse = this;
        scoreExcelParse.deptService=this.deptService;
    }

    public static List<Score> ExceltoScore(File userUploadFile){
        List<Score> list=new ArrayList<>();
        Score score=null;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(userUploadFile));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            //获取第一个sheet页
            HSSFSheet sheet = wb.getSheetAt(0);
            if(sheet!=null){
                for(int rowNum =1;rowNum<=sheet.getLastRowNum();rowNum++){
                    HSSFRow row = sheet.getRow(rowNum);
                    if(row==null){
                        continue;
                    }
                    score=new Score();
                    //将单元格里每行数据存入ScoreVo对象中
                    score.setDeptname(ExcelUtil.formatCell(row.getCell(0)).split("\\.")[0]);
                    score.setTestname(ExcelUtil.formatCell(row.getCell(1)));
                    score.setUserid(Integer.parseInt(ExcelUtil.formatCell(row.getCell(2)).split("\\.")[0]));
                    score.setUsername(ExcelUtil.formatCell(row.getCell(3)).split("\\.")[0]);
                    score.setChinesescore(Integer.parseInt(ExcelUtil.formatCell(row.getCell(4)).split("\\.")[0]));
                    score.setMathscore(Integer.parseInt(ExcelUtil.formatCell(row.getCell(5)).split("\\.")[0]));
                    score.setEnglishscore(Integer.parseInt(ExcelUtil.formatCell(row.getCell(6)).split("\\.")[0]));
                    score.setTeacherwords(ExcelUtil.formatCell(row.getCell(7)));
                    score.setTotlescore(score.getChinesescore()+score.getEnglishscore()+score.getMathscore());
                    score.setTestid(Md5Util.generateHash(score.getTestname()).substring(0,6));
                    score.setDeptid(scoreExcelParse.deptService.getDeptByName(score.getDeptname()).getId());
                    list.add(score);
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
