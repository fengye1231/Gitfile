package com.bs.bus.service.impl;

import com.bs.bus.entity.Message;
import com.bs.bus.mapper.MessageMapper;
import com.bs.bus.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzb
 * @since 2020-04-21
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
