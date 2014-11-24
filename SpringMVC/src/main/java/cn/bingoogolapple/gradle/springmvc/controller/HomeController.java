package cn.bingoogolapple.gradle.springmvc.controller;

import cn.bingoogolapple.gradle.one.One;
import cn.bingoogolapple.gradle.springmvc.dto.AjaxObj;
import cn.bingoogolapple.gradle.springmvc.model.Person;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {

    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public @ResponseBody String json() {
        AjaxObj ajaxObj = new AjaxObj();
        ajaxObj.setSuccess(false);
        ajaxObj.setMsg("你好，Gradle");
        return JSON.toJSONString(ajaxObj);
    }

	@RequestMapping("/test")
	public String loadTestPage(Model m) {
        One one = new One();
		m.addAttribute("name", "dfsdfsdfssdfsdf呵呵哈哈 " + one.getName());
		return "test";
	}

    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:/test";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public @ResponseBody String upload(Person person,HttpServletRequest request) {
        MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;
        CommonsMultipartFile cfile = (CommonsMultipartFile) rm.getFile("pic");
        String originalFilename = cfile.getOriginalFilename();
        System.out.println(originalFilename);
        System.out.println(person.getName());
        System.out.println(person.getBirthday());
        AjaxObj ajaxObj = new AjaxObj();
        ajaxObj.setSuccess(true);
        ajaxObj.setMsg("上传成功");
        return JSON.toJSONString(ajaxObj);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}