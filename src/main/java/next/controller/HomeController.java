package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.QuestionDao;

public class HomeController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		log.debug("home controller");
		QuestionDao questionDao = QuestionDao.getInstance();
		req.setAttribute("questions", questionDao.findAll());
		return "home";
	}

}