package org.ljj.ceshi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ljj.bean.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/postdemo")
@Api(value = "/",description = "所有Post方法")
public class PostMethod {

    private static Cookie cookie;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，获取cookies",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String useName,
                        @RequestParam(value = "password",required = true) String password,
                        @RequestParam(value = "code",required = false) String code){
        if(useName.equals("zhangsan") && password.equals("123")){
            cookie = new Cookie("login","post_success");
            response.addCookie(cookie);

            return "登录成功";
        }
        return "用户密码错误";
    }


    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){
        Cookie[] cookies = request.getCookies();
        User user = new User();
        for(Cookie c : cookies){
            if (c.getName().equals("login")
                    && c.getValue().equals("success")
                    && u.getUserName().equals("zhangsan")
                    && u.getPassword().equals("123")){

                user.setName("lisi");
                user.setSex("男");
                user.setAge(12);

                return user.toString();
            }
        }
        return "参数不合法";

    }
}
