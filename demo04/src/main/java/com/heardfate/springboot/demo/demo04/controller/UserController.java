package com.heardfate.springboot.demo.demo04.controller;

import com.heardfate.springboot.demo.demo04.entity.User;
import com.heardfate.springboot.demo.demo04.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Heardfate
 * @since 2018-10-23
 */
@RestController
@RequestMapping("/demo04/user")
@Api(value = "UserController|前端用户模块控制器",tags={"用户模块操作接口"})
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public BigInteger saveUser(@RequestBody User user) {
        System.out.println(user);
        boolean isSave = userService.save(user);
        if (isSave) {
            return user.getId();
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public boolean deleteUser(@PathVariable BigInteger id) {
        boolean remove = userService.removeById(id);
        System.out.println(remove);
        return remove;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "修改用户信息", notes = "根据用户id修改信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", dataType = "BigInteger", example = "1"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    public User updateUser(@PathVariable BigInteger id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return userService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "根据用户编号获取用户信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户编号", required = true, dataType = "BigInteger", example =
            "1")
    public User getUser(@PathVariable BigInteger id) {
        User user = userService.getById(id);
        return user;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUserList() {
        List<User> list = userService.list(null);
        return list;
    }
}
