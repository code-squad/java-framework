package core.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.ModelAndView;
import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.HandlerExecution;
import next.controller.Controller;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
	private LegacyHandlerMapping lhm;
	private AnnotationHandlerMapping ahm;
	private List<HandlerMapping> handlers;

	@Override
	public void init() throws ServletException {
		lhm = LegacyHandlerMapping.getInstance();
		ahm = new AnnotationHandlerMapping("core.nmvc");
		ahm.initialize();
		handlers = new ArrayList<>();
		handlers.add(lhm);
		handlers.add(ahm);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object handler = getHandler(req);
		try {
			if (handler instanceof Controller) {
				render(req, resp, ((Controller) handler).execute(req, resp));
			} else if (handler instanceof HandlerExecution) {
				HandlerExecution he = ahm.getHandler(req);
				render(req, resp, ((HandlerExecution) handler).handle(req, resp));
			}
		} catch (Exception e) {
			log.error("mav render 부분에서 에러 발생");
		}
	}

	private Object getHandler(HttpServletRequest req) {
		for (HandlerMapping handlerMapping : handlers) {
			Object handler = handlerMapping.getHandler(req);
			if (handler != null) {
				return handler;
			}
		}
		return null;
	}

	private void render(HttpServletRequest req, HttpServletResponse resp, ModelAndView mav) {
		try {
			mav.render(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
