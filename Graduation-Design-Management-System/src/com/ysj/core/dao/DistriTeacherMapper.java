package com.ysj.core.dao;


import com.ysj.core.po.Distribution;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface DistriTeacherMapper {



    int insert(Distribution record);
    
    List<Distribution> selectByPrimaryKey(String sId);

  
}


