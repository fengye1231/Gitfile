package com.bs.bus.vo;

import com.bs.bus.entity.Score;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ScoreVo extends Score {

    private Integer page=1;

    private Integer limit=10;

    //接受多个ID
    private Integer[] ids;

}
