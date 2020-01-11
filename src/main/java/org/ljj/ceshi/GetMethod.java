package org.ljj.ceshi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/getdemo")
@Api(value = "/",description = "所有Get方法")
public class GetMethod {

    /**
     *获取cookies
     */
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "这是获取cookies方法",httpMethod = "GET")
    public String getCookies(HttpServletRequest request,HttpServletResponse response){

        Cookie cookie = new Cookie("login","success");
        response.addCookie(cookie);

        return "获取cookies成功";
    }

    /**
     *携带cookies访问
     */
    @RequestMapping(value = "getWithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "这是携带cookies访问的方法",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "需要携带cookies访问该方法";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login") &&
                    cookie.getValue().equals("success")){
                return "携带cookies访问成功";
            }
        }
        return "finish";
    }

    /**
     * 需要携带参数访问
     * k:v
     */
    @RequestMapping(value = "getWithParam",method = RequestMethod.GET)
    @ApiOperation(value = "这是携带参数访问的方法，kv",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> list = new HashMap<>();
        list.put("麦当劳",1);
        list.put("肯德基",2);

        return list;
    }

    /**
     * 需要携带参数访问
     * url/getWithParam/{start}/{end}
     */
    @RequestMapping(value = "getWithParam/{start}/{end}")
    @ApiOperation(value = "这是携带参数访问的方法，path",httpMethod = "GET")
    public Map getListTwo(@PathVariable Integer start,
                          @PathVariable Integer end){
        Map<String,Integer> list = new HashMap<>();
        list.put("必胜客",3);
        list.put("华莱士",4);

        return list;
    }
}
