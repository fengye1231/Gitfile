package com.ysj.core.dao;

import com.ysj.core.po.Student;
import com.ysj.core.po.Teacher;
import com.ysj.core.po.Title;
import com.ysj.core.po.Title1;
import com.ysj.core.po.TitleExample;
import com.ysj.core.po.Distribution;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface DistriTeacherMapper {



    int insert(String sId,String sName,String tId,String tName);
    
    
    
    int selectByPrimaryKey(String sId);

  
}


