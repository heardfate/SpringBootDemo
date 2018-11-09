package com.heardfate.springboot.demo.demo04.service.impl;

import com.heardfate.springboot.demo.demo04.entity.User;
import com.heardfate.springboot.demo.demo04.dao.UserDao;
import com.heardfate.springboot.demo.demo04.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Heardfate
 * @since 2018-11-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
