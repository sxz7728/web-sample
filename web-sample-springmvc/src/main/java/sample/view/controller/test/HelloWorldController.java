package sample.view.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/*")
public class HelloWorldController {

	@RequestMapping("helloWorld")
	public void helloWorld(Model model) {
	}
}
