package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import next.model.User;

public class ProfileController implements Controller {
	private UserDao userDao = UserDao.getInstance();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		User user = userDao.findByUserId(userId);
		if (user == null) {
			throw new NullPointerException("사용자를 찾을 수 없습니다.");
		}
		request.setAttribute("user", user);
		return new ModelAndView(new JspView("/user/profile.jsp"));
	}
}
