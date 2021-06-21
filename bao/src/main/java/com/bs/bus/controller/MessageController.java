package com.bs.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bus.common.ScoreExcelParse;
import com.bs.bus.entity.Message;
import com.bs.bus.entity.Score;
import com.bs.bus.service.IMessageService;
import com.bs.bus.service.IScoreService;
import com.bs.bus.vo.MessageVo;
import com.bs.bus.vo.ScoreVo;
import com.bs.sys.common.AppFileUtils;
import com.bs.sys.common.DataGridView;
import com.bs.sys.common.ResultObj;
import com.bs.sys.common.WebUtils;
import com.bs.sys.common.excel.StringUtil;
import com.bs.sys.entity.User;
import com.bs.sys.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzb
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/bus/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IUserService userService;

    @RequestMapping("getmessages")
    public Map<String,Object> getMessages(Message message){
        User user = (User) WebUtils.getSession().getAttribute("user");
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Message> messageQueryWrapper=null;
        messageQueryWrapper=new QueryWrapper<>();
        messageQueryWrapper.eq(message.getFromid()!=null,"fromid",message.getFromid());
        messageQueryWrapper.eq("toid",user.getId());
        messageQueryWrapper.orderByDesc("time");
        List<Message> list=messageService.list(messageQueryWrapper);
        map.put("from",list);
        messageQueryWrapper=new QueryWrapper<>();
        messageQueryWrapper.eq(message.getFromid()!=null,"toid",message.getFromid());
        messageQueryWrapper.eq("fromid",user.getId());
        list=messageService.list(messageQueryWrapper);
        map.put("to",list);
        return map;
    }

    @RequestMapping("savemessage")
    public ResultObj saveMessage(Message message){
        User user = (User) WebUtils.getSession().getAttribute("user");
        try {
            message.setTime(new Date());
            message.setFromid(user.getId());
            messageService.save(message);
            return ResultObj.MESSAGE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.MESSAGE_ERROR;
        }
    }

    @RequestMapping("loadgrid")
    public DataGridView loadGrid(MessageVo messageVo){
        User user = (User) WebUtils.getSession().getAttribute("user");
        QueryWrapper<Message> messageQueryWrapper;
        IPage<Message> page = new Page<Message>(messageVo.getPage(),messageVo.getLimit());
        messageQueryWrapper=new QueryWrapper<>();
        messageQueryWrapper.like(StringUtil.isNotEmpty(messageVo.getMessage()),"message",messageVo.getMessage());
        messageQueryWrapper.eq("fromid",user.getId());
        messageService.page(page,messageQueryWrapper);
        List<Message> list=page.getRecords();
        List<MessageVo> messageVos=new ArrayList<>();
        QueryWrapper<User> userQueryWrapper=null;
        for(Message message:list){
            MessageVo messageVo1=new MessageVo();
            BeanUtils.copyProperties(message,messageVo1);
            messageVo1.setFromname(userService.getById(message.getFromid()).getName());
            messageVo1.setToname(userService.getById(message.getToid()).getName());
            messageVos.add(messageVo1);
        }
        return new DataGridView(page.getTotal(),messageVos);

    }

    //删除留言
    @RequestMapping("delectmessage")
    public ResultObj delectScore(Message message){
        try {
            messageService.removeById(message);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除留言
     */
    @RequestMapping("batchdelectmessage")
    public ResultObj batchDeleteScore(MessageVo messageVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : messageVo.getIds()) {
                idList.add(id);
            }
            messageService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*
    返回给我留言的所有人信息
     */
    @RequestMapping("loadallinfo")
    public Map<String,Object> loadAllInfo(){
        Map<String,Object> map=new HashMap<>();
        User user = (User) WebUtils.getSession().getAttribute("user");
        QueryWrapper<Message> messageQueryWrapper=new QueryWrapper<>();
        messageQueryWrapper.eq("toid",user.getId());
        messageQueryWrapper.orderByDesc("time");
        messageQueryWrapper.select("distinct fromid");
        List<Message> messages=messageService.list(messageQueryWrapper);
        for(Message message:messages){
            message.setImgpath(userService.getById(message.getFromid()).getImgpath());
            message.setFname(userService.getById(message.getFromid()).getName());
        }
        map.put("frominfo",messages);
        return map;
    }


}

