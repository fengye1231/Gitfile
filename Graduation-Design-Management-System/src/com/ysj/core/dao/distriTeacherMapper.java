package com.ysj.core.dao;

import com.ysj.core.po.Student;
import com.ysj.core.po.Teacher;
import com.ysj.core.po.Title;
import com.ysj.core.po.Title1;
import com.ysj.core.po.TitleExample;
import com.ysj.core.po.Distribution;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface distriTeacherMapper {
    int insert(Distribution distribution);
}


