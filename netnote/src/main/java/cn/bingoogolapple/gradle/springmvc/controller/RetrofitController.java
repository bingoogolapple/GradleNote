package cn.bingoogolapple.gradle.springmvc.controller;

import cn.bingoogolapple.gradle.springmvc.dto.JsonResp;
import cn.bingoogolapple.gradle.springmvc.model.Person;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/retrofit")
public class RetrofitController {

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    private
    @ResponseBody
    String loginGet(@RequestParam("username") String username, @RequestParam("password") String password) {
        log("get登陆");
        return processLogin(username, password, "get");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    private
    @ResponseBody
    String loginPost(@RequestParam("username") String username, @RequestParam("password") String password) {
        log("post登陆");
        return processLogin(username, password, "post");
    }

    private String processLogin(String username, String password, String requestMethod) {
        JsonResp jsonResp = new JsonResp();
        if ("hello".equals(username) && "world".equals(password)) {
            jsonResp.code = 0;
            jsonResp.msg = "登陆成功 " + requestMethod;
        } else {
            jsonResp.code = 1;
            jsonResp.msg = "用户名或密码错误 " + requestMethod;
        }
        return JSON.toJSONString(jsonResp);
    }

    @RequestMapping(value = "/header1", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    private
    @ResponseBody
    String header1(@RequestHeader("headerParam1") String headerParam1, @RequestHeader("globalHeader1") String globalHeader1, @RequestHeader("globalHeader2") String globalHeader2, @RequestHeader("globalHeader3") String globalHeader3, @RequestHeader("globalHeader4") String globalHeader4) {
        log(headerParam1);
        logGlobalHeader(globalHeader1, globalHeader2, globalHeader3, globalHeader4);
        return JSON.toJSONString(new JsonResp(headerParam1));
    }

    @RequestMapping(value = "/header2", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    private
    @ResponseBody
    String header2(@RequestHeader("headerParam1") String headerParam1, @RequestHeader("headerParam2") String headerParam2, @RequestHeader("globalHeader1") String globalHeader1, @RequestHeader("globalHeader2") String globalHeader2, @RequestHeader("globalHeader3") String globalHeader3, @RequestHeader("globalHeader4") String globalHeader4) {
        log(headerParam1 + " " + headerParam2);
        logGlobalHeader(globalHeader1, globalHeader2, globalHeader3, globalHeader4);
        return JSON.toJSONString(new JsonResp(headerParam1 + " " + headerParam2));
    }

    @RequestMapping(value = "/createPerson", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public
    @ResponseBody
    String createperson(Person person) {
        log(person.toString());
        return JSON.toJSONString(new JsonResp("createperson 成功 " + person.toString()));
    }

    @RequestMapping(value = "/updateAvatar", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public
    @ResponseBody
    String updateAvatarAndDesc(@RequestParam("desc") String desc, HttpServletRequest request) {

        MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;
        CommonsMultipartFile cfile = (CommonsMultipartFile) rm.getFile("avatar");
        String originalFilename = cfile.getOriginalFilename();

        JsonResp jsonResp = new JsonResp();
        jsonResp.msg = "上传成功 " + originalFilename + " " + desc;

        log(jsonResp.msg);

        return JSON.toJSONString(jsonResp);
    }


    private void logGlobalHeader(String globalHeader1, String globalHeader2, String globalHeader3, String globalHeader4) {
        log("globalHeader1 = " + globalHeader1 + " globalHeader2 = " + globalHeader2 + " globalHeader3 = " + globalHeader3 + " globalHeader4 = " + globalHeader4);
    }

    private void log(String msg) {
        System.out.println(msg);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}