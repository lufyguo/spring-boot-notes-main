package com.ziorye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziorye.bean.User;
import com.ziorye.mapper.UserMapper;
import com.ziorye.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ziorye
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
