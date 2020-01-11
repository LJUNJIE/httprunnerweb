package org.ljj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.ljj.model.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value="v1",description = "第一个版本的demo")
@RequestMapping("v1")
public class Demo {

    //获取一个执行sql的对象
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数",httpMethod = "GET")
    public int getUserCount() {
        return sqlSessionTemplate.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    public int addUser(@RequestBody org.ljj.model.User user){
        return sqlSessionTemplate.insert("addUser",user);

    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return sqlSessionTemplate.update("updateUser",user);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ApiOperation(value = "删除用户",httpMethod = "POST")
    public int delUser(@RequestParam int id){
        return sqlSessionTemplate.update("delUser",id);
    }
}
