package core.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import core.mvc.JspView;
import core.mvc.ModelAndView;
import core.annotation.*;

@Controller
public class HomeController implements LegacyControllerInterface{

	@Override
	@RequestMapping("/fuckyou")
	public ModelAndView run(HttpServletRequest req) {
		System.out.print("someone has awakened my logic.");
		return new ModelAndView(new JspView("index.jsp"));
	}
}
