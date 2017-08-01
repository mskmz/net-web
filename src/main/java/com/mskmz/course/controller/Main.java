package com.mskmz.course.controller;

import com.mskmz.course.bean.Content;
import com.mskmz.course.bean.Trx;
import com.mskmz.course.bean.User;
import com.mskmz.course.dao.ContentDao;
import com.mskmz.course.dao.TrxDao;
import com.mskmz.course.dao.UserDao;
import com.mskmz.course.utils.SpringContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mskmz on 2017/7/30.
 */
@Controller
@SessionAttributes("User") //①将ModelMap中属性名为currUser的属性
//放到Session属性列表中，以便这个属性可以跨请求访问
public class Main {
    @RequestMapping("/")
    public String reqIndex(ModelMap map)
            throws IOException {
        if(!checkUser(map)){
           System.out.print(map.get("User"));
           return "login";
        }
        ContentDao contentDao = (ContentDao) SpringContextUtils.getBean("contentDao",ContentDao.class);
        List<Content> productList=contentDao.list(getUser(map).getId()+"");
        System.out.print(productList);
        map.addAttribute("productList",productList);
        return "index";
    }
    @RequestMapping("/logout")
    public String reqLogout(ModelMap map)
            throws IOException {
        if(!checkUser(map)){
            return "login";
        }
        map.remove("User");
        return "login";
    }
    @RequestMapping("/login")
    public String reqLogin(ModelMap map)
            throws IOException {
        if(!checkUser(map)){
            return "login";
        }
        map.remove("User");
        return "login";
    }
    @RequestMapping("/show")
    public String reqShow(ModelMap map,@RequestParam("id") String id)
            throws IOException {
        if(!checkUser(map)){
            return "login";
        }
        ContentDao contentDao = (ContentDao) SpringContextUtils.getBean("contentDao",ContentDao.class);
        Content content=contentDao.findForId(id);
        map.addAttribute("product",content);
//        TrxDao trxDao = (TrxDao) SpringContextUtils.getBean("trxDao",TrxDao.class);
//        int countBuy=trxDao.getTotalForIdUser(id,((User)map.get("User")).getId()+"");
//        int countSell=trxDao.getTotalForId(id);
//        content.setBuy(countBuy>0);
//        content.setSell(countSell>0);
//        map.addAttribute("product",content);
        return "show";
    }
    @RequestMapping("/account")
    public String reqAccount(ModelMap map)
            throws IOException {
        if(!checkUser(map)){
            return "login";
        }
        TrxDao trxDao = (TrxDao) SpringContextUtils.getBean("trxDao",TrxDao.class);
        List<Trx> trxList=trxDao.list(getUser(map).getId()+"");
        map.addAttribute("buyList",trxList);
        return "account";
    }
    @RequestMapping("/public")
    public String reqPublic(ModelMap map)
            throws IOException {
        if(!checkUser(map)){
            return "login";
        }
        return "public";
    }
    @RequestMapping("/publicSubmit")
    public String reqPublicSubmit(ModelMap map,
                                  @RequestParam("title") String title,
                                  @RequestParam("image") String image,
                                  @RequestParam("detail") String detail,
                                  @RequestParam("price") String price,
                                  @RequestParam("summary") String summary)
            throws IOException {
        if(!checkUser(map)){
            return "login";
        }
        System.out.print(title);
        System.out.print(image);
        System.out.print(detail);
        System.out.print(price);
        System.out.print(summary);
        ContentDao contentDao = (ContentDao) SpringContextUtils.getBean("contentDao",ContentDao.class);
        int id=contentDao.insert(title,image,detail,price,summary);
        Content content=contentDao.findForId(id+"");
        map.addAttribute("product",content);
        return "publicSubmit";
    }
    @RequestMapping("/edit")
    public String reqEdit(ModelMap map,@RequestParam("id") String id)
            throws IOException {
        if(!checkUser(map)){
            return "login";
        }
        ContentDao contentDao = (ContentDao) SpringContextUtils.getBean("contentDao",ContentDao.class);
        Content content=contentDao.findForId(id);
        map.addAttribute("product",content);
        return "edit";
    }
    @RequestMapping("/editSubmit")
    public String reqEditSubmit(ModelMap map,
                                @RequestParam("id") String id,
                                @RequestParam("title") String title,
                                @RequestParam("image") String image,
                                @RequestParam("detail") String detail,
                                @RequestParam("price") String price,
                                @RequestParam("summary") String summary)
            throws IOException {
        if(!checkUser(map)){
            return "login";
        }
        ContentDao contentDao = (ContentDao) SpringContextUtils.getBean("contentDao",ContentDao.class);
        int count=contentDao.update(id,title,image,detail,price,summary);
        Content content = null;
        if(count!=0){
            content=contentDao.findForId(id+"");
        }
        map.addAttribute("product",content);
        return "editSubmit";
    }
    public  boolean checkUser(ModelMap map){
        User user=(User)map.get("User");
        if(user!=null){
            map.addAttribute("type",user.getUserType());
            map.addAttribute("user",user);
        }
        return map.get("User")!=null;
    }
    public User getUser(ModelMap map){
        return ((User)map.get("User"));
    }
}
