package core.mvc;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;
import next.model.User;
import next.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AnswerController {
    private static final Logger log = LoggerFactory.getLogger(AnswerController.class);

    @RequestMapping(value = "/api/qna/addAnswer", method = RequestMethod.POST)
    public ModelAndView create(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // create Answer object using data from Http request

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute(UserSessionUtils.USER_SESSION_KEY);
        String questionId = req.getParameter("questionId");
        log.debug("questionId:{}", questionId);

        Answer answer = new Answer(user.getUserId(), req.getParameter("contents"), Long.parseLong(req.getParameter("questionId")));

        log.debug("answer : {}", answer.toString());

        AnswerDao answerDao = new AnswerDao();
        AnswerService answerService = new AnswerService(answerDao);
        Answer savedAnswer = answerService.insert(answer);

        return new ModelAndView(new JsonView()).addObject("answer", savedAnswer);
    }

    @RequestMapping(value = "/api/qna/deleteAnswer", method = RequestMethod.POST)
    public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
        String para = req.getParameter("answerId");
        log.debug("answerId : {}", para);

        Long answerId = Long.parseLong(para);

        AnswerDao answerDao = new AnswerDao();
        AnswerService answerService = new AnswerService(answerDao);
        Result result = answerService.delete(answerId);

        return new ModelAndView(new JsonView()).addObject("result", result);
    }
}
