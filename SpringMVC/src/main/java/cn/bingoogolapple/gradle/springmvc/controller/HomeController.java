package cn.bingoogolapple.gradle.springmvc.controller;

import cn.bingoogolapple.gradle.one.One;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

	@RequestMapping("test")
	public String loadTestPage(Model m) {
        One one = new One();
		m.addAttribute("name", "sdfsdf呵呵哈哈 " + one.getName());
		return "test";
	}


}