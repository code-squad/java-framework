package core.nmvc;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.JspView;
import core.mvc.ModelAndView;

public class HandlerExecution {
	HandlerKey handerKey;
	Method m;
	Object instance;

	public HandlerExecution(Method m, Object instance) {
		this.m = m;
		this.instance = instance;
	}

	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return (ModelAndView) m.invoke(instance, request, response);
	}
}
