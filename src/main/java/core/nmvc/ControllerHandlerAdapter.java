package core.nmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.ModelAndView;
import next.controller.LegacyController;

public class ControllerHandlerAdapter implements HandlerAdapter  {

	@Override
	public boolean supports(Object handler) {
		return handler instanceof LegacyController;
	}

	@Override
	public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception{
		return ((LegacyController)handler).execute(req, resp);
	}

}
