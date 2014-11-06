package cn.bingoogolapple.gradle.springmvc.controller;

import cn.bingoogolapple.gradle.one.One;
import cn.bingoogolapple.gradle.springmvc.dto.AjaxObj;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public @ResponseBody String json() {
        AjaxObj ajaxObj = new AjaxObj();
        ajaxObj.setSuccess(false);
        ajaxObj.setMsg("你好，Gradle");
        return JSON.toJSONString(ajaxObj);
    }

	@RequestMapping("test")
	public String loadTestPage(Model m) {
        One one = new One();
		m.addAttribute("name", "dfsdfsdfssdfsdf呵呵哈哈 " + one.getName());
		return "test";
	}

}