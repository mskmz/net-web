package com.mskmz.course.controller;

import com.mskmz.course.dao.ContentDao;
import com.mskmz.course.dao.TrxDao;
import com.mskmz.course.dao.UserDao;
import com.mskmz.course.bean.User;
import com.mskmz.course.pojo.Msg;
import com.mskmz.course.utils.SpringContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.IOException;

/**
 * Created by mskmz on 2017/7/30.
 */
@Controller
@SessionAttributes("User")
public class api {

    @RequestMapping("/api/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, ModelMap map)
            throws IOException {
        //transferMoneyJDBCimpl dao = context.getBean("transferMoneyJDBCimpl", transferMoneyJDBCimpl.class);
        UserDao userDao = (UserDao)SpringContextUtils.getBean("userDao",UserDao.class);
        System.out.print("userName"+userName+"password"+password);
        User user=userDao.getUser(userName,password);
        if(user==null){
            sendErr(map,"没有该用户");
        }else{
            sendSucc(map);
            map.addAttribute("User",user);
        }
        return "retMsg";
    }
    @RequestMapping("/api/delete")
    public String delete(ModelMap map,@RequestParam("id") String id)
            throws IOException {
        ContentDao contentDao = (ContentDao)SpringContextUtils.getBean("contentDao",ContentDao.class);
        contentDao.delForId(id);
        sendSucc(map);
        return "retMsg";
    }
    @RequestMapping("/api/buy")
    public String buy(ModelMap map,@RequestParam("id") String id,@RequestParam("number") String number)
            throws IOException {
        TrxDao trxDao = (TrxDao)SpringContextUtils.getBean("trxDao",TrxDao.class);
        int count=trxDao.insert(id,number,getUser(map).getId()+"");
        if(count>0){
            sendSucc(map);
        }else{
            sendErr(map,"购买失败");
        }
        return "retMsg";
    }
    @RequestMapping("/api/upload")
    public String upload(ModelMap map,@RequestParam("id") String id,@RequestParam("url") String url)
            throws IOException {

        ContentDao contentDao = (ContentDao)SpringContextUtils.getBean("contentDao",ContentDao.class);
        int count=contentDao.updateImage(id,url);
        if(count>0){
            sendSucc(map);
        }else{
            sendErr(map,"没有数据被修改");
        }
        return "retMsg";
    }

    public void sendSucc(ModelMap map){
        Msg msg= new Msg();
        msg.setCode("200");
        msg.setMessage("成功");
        msg.setResult("true");
        map.addAttribute("msg", msg);
    }
    public void sendErr(ModelMap map,String errMsg){
        Msg msg= new Msg();
        msg.setCode("500");
        msg.setMessage(errMsg);
        msg.setResult("false");
        map.addAttribute("msg", msg);
    }
    public User getUser(ModelMap map){
        return ((User)map.get("User"));
    }
}
