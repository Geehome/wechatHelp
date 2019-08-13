package com.Controller;

import com.IService.UserService;
import com.alibaba.fastjson.JSONObject;
import com.mapper.UserMapper;
import com.pojo.User;
import com.util.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    String appid = "wxab3838f7f3971c42";
    String secret = "784c2d000e225c3cb3065b2064759efb";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    @GetMapping("user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userMapper.getUserById(id);
    }

    @GetMapping("register/submit")
    public String insertUser(User user) {
        return "";
    }

    @GetMapping("/user")
    public String user() {
        return "小烈的服务器";
    }


    //注册界面打入tel和age
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String GetTelAndAge(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        Integer age = Integer.parseInt(jsonObject.getString("age"));
        String tel = jsonObject.getString("tel");
        Integer id = Integer.parseInt(jsonObject.getString("id"));
        User user = new User();
        user.setId(id);
        user.setTel(tel);
        user.setAge(age);
        userMapper.insertAgeAndTelById(user);
        return "success Register";
    }


    /*
    * @method:用于注册第一次登陆的人，以及老用户跳转
    * */
    @RequestMapping(value = "/getUserOpenId", method = RequestMethod.POST)
    @ResponseBody
    public Map getTest(@RequestBody JSONObject normalInfo) {
        Map<String, Integer> response = new HashMap<>();
        System.out.println("Quest data is :" + normalInfo);
        String rescode = (String) normalInfo.get("rescode");
        //获取OpenId的过程
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appid + "&secret=" + secret + "&js_code=" + rescode +
                "&grant_type=authorization_code";
        JSONObject privateInfo = HttpRequest.sendRequest(url, null, "GET");

        //查看是否注册过，注册了就跳过，没注册就注册
        String openId = privateInfo.getString("openid");
        User user0 = userMapper.getUserByOpenId(openId);
        try {
            //新用户就进行注册
            if (user0 == null) {
                String name = normalInfo.getString("name");
                String gender = normalInfo.getString("gender");
                String city = normalInfo.getString("city");
                //默认给500积分
                int id = userService.insertUser(openId, name, gender, city);

                response.put("insertSuccess", id);

                return response;
            }
        } catch (Exception e) {
            System.out.println("SQL execute wrong");
            response.put("SQL execute wrong", new Integer(-1));
            return response;
        }

        response.put("isOldUser", user0.getId());
        return response;
    }

}
