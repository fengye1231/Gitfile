package com.bs.bus.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bus.common.ScoreExcelParse;
import com.bs.bus.entity.Score;
import com.bs.bus.service.IScoreService;
import com.bs.bus.vo.ScoreVo;
import com.bs.sys.common.*;
import com.bs.sys.entity.Dept;
import com.bs.sys.entity.Notice;
import com.bs.sys.entity.Role;
import com.bs.sys.entity.User;
import com.bs.sys.service.IDeptService;
import com.bs.sys.service.IRoleService;
import com.bs.sys.vo.DeptVo;
import com.bs.sys.vo.NoticeVo;
import com.bs.sys.vo.UsersExcelVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzb
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/bus/score")
public class ScoreController {

    @Autowired
    private IScoreService scoreService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IRoleService roleService;


    @RequestMapping("exceltoScore")
    public ResultObj excelToScore(String excelpath){
        try {
            List<Score> list= new ScoreExcelParse().ExceltoScore((new File(AppFileUtils.UPLOAD_PATH+"/"+excelpath)));
            for(Score score:list){
                scoreService.save(score);
            }
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("loadallscore")
    public DataGridView loadAllScore(ScoreVo scoreVo){
        //从缓存获取登录用户
        User user = (User) WebUtils.getSession().getAttribute("user");
        //写不同登录用户角色能查看的数据
       List<Integer> roleids=roleService.queryUserRoleIdsByUid(user.getId());
        IPage<Score> page = new Page<Score>(scoreVo.getPage(),scoreVo.getLimit());
        QueryWrapper<Score> queryWrapper = new QueryWrapper<Score>();
        String Maxrole="";
       for(Integer roleid:roleids){
            if(roleService.getById(roleid).getName().equals("超级管理员")){
                Maxrole="超级管理员";
            }
            if(!Maxrole.equals("超级管理员")&&roleService.getById(roleid).getName().equals("老师")){
                Maxrole="老师";
            }
            if(!Maxrole.equals("超级管理员")&&!Maxrole.equals("老师")&&roleService.getById(roleid).getName().equals("学生")){
                Maxrole="学生";
            }
       }
       if(Maxrole.equals("超级管理员")||Maxrole.equals("老师")){
           //进行模糊查询
           queryWrapper.like(StringUtils.isNotBlank(scoreVo.getTestname()),"testname",scoreVo.getTestname());
           queryWrapper.like(StringUtils.isNotBlank(scoreVo.getUsername()),"username",scoreVo.getUsername());
           if(scoreVo.getDeptid()==null||scoreVo.getDeptid()==1){
               queryWrapper.like("deptid","");
           }
           else {
               queryWrapper.like(StringUtils.isNotBlank(deptService.getById(scoreVo.getDeptid()).getName()),"deptname",deptService.getById(scoreVo.getDeptid()).getName());
           }

           queryWrapper.orderByDesc("totlescore");
           scoreService.page(page,queryWrapper);
       }
       if(Maxrole.equals("学生")){
           queryWrapper.eq("userid",user.getLoginname());
           scoreService.page(page,queryWrapper);
       }
        return new DataGridView(page.getTotal(),page.getRecords());

    }

    //删除成绩
    @RequestMapping("delectscore")
    public ResultObj delectScore(ScoreVo scoreVo){
        try {
            scoreService.removeById(scoreVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除成绩
     * @param scoreVo
     * @return
     */
    @RequestMapping("batchdelectscore")
    public ResultObj batchDeleteScore(ScoreVo scoreVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : scoreVo.getIds()) {
                idList.add(id);
            }
            scoreService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*
    返回成绩数据，分组，数据,返回的数据类型为map,key为对应的部门名，值为相关的数据，部门名+科目名为Key，对应的为数据数组
     */
    @RequestMapping("getScoreMap")
    public Map<String,Object> getScoreMap(Score score){
        Map<String,Object> map=new HashMap<>();
        List<Dept> deptList=deptService.list();
        String deptnames[]=new String[deptList.size()];
        int i=0;
        for (Dept dept:deptList){
                deptnames[i]=dept.getName();
            i++;
        }
        map.put("deptnames",deptnames);

        QueryWrapper<Score> scoreQueryWrapper=null;
        List<Score> scoreList;
        for(String deptname:deptnames){
            scoreQueryWrapper=new QueryWrapper<>();
            scoreQueryWrapper.like("deptname",deptname.equals("家校通")?"":deptname);
            scoreList=scoreService.list(scoreQueryWrapper);
            map.put(deptname,scoreList);
            Integer Mathdata[]=new Integer[scoreList.size()];
            Integer Chinesedata[]=new Integer[scoreList.size()];
            Integer Englishdata[]=new Integer[scoreList.size()];
            Integer Totledata[]=new Integer[scoreList.size()];
            int j=0;
            for(Score score1:scoreList){
                Mathdata[j]=score1.getChinesescore();
                Chinesedata[j]=score1.getChinesescore();
                Englishdata[j]=score1.getEnglishscore();
                Totledata[j]=score1.getTotlescore();
                j++;
            }
            map.put(deptname+"math",Mathdata);
            map.put(deptname+"chinese",Chinesedata);
            map.put(deptname+"english",Englishdata);
            map.put(deptname+"totle",Totledata);
        }
        return map;
    }

    @RequestMapping("getbysearchview")
    public Map<String,Object> getBySearchView(Score score){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Score> scoreQueryWrapper=new QueryWrapper<>();
        scoreQueryWrapper.like("deptname",score.getDeptname().equals("家校通")?"":score.getDeptname());
        scoreQueryWrapper.like("testname",score.getTestname());
        List<Score> list=scoreService.list(scoreQueryWrapper);
        map.put("result",list);
        return map;
    }

    @RequestMapping("getdeptnodes")
    public DataGridView getDeptNodes(){
        //查询出所有的部门，存放进list中
        QueryWrapper<Score> scoreQueryWrapper=new QueryWrapper<>();
        scoreQueryWrapper.select("distinct deptid,deptname");
        List<Score> list = scoreService.list(scoreQueryWrapper);

        List<TreeNode> treeNodes = new ArrayList<>();
        //将部门放入treeNodes中，组装成json
        for (Score score : list) {
            treeNodes.add(new TreeNode(score.getDeptid(),score.getDeptname(),0));
        }
        return new DataGridView(treeNodes);
    }

    @RequestMapping("gettestnodes")
    public DataGridView gettestNodes(){
        //查询出所有的testname，存放进list中
        QueryWrapper<Score> scoreQueryWrapper=new QueryWrapper<>();
        scoreQueryWrapper.select("distinct testname");
        List<Score> list = scoreService.list(scoreQueryWrapper);

        List<TreeNode> treeNodes = new ArrayList<>();
        //将部门放入treeNodes中，组装成json
        int i=1;
        for (Score score : list) {
            treeNodes.add(new TreeNode(i++,score.getTestname(),0));
        }

        return new DataGridView(treeNodes);
    }

    @RequestMapping("getstuscore")
    public Map<String,Object> getStuScore(Score score){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Score> scoreQueryWrapper=new QueryWrapper<>();
        scoreQueryWrapper.eq(StringUtils.isNotBlank(score.getUsername()),"username",score.getUsername());
        scoreQueryWrapper.eq(score.getUserid()!=null,"userid",score.getUserid());
        List<Score> list=scoreService.list(scoreQueryWrapper);
        map.put("scores",list);
        scoreQueryWrapper.select("distinct testname");
        List<Score> testnamelist=scoreService.list(scoreQueryWrapper);
        map.put("testnames",testnamelist);
        return map;
    }
}

