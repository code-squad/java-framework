package next.controller;

import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.View;
import next.dao.AnswerDao;
import next.model.Response;
import next.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteAnswerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse res) {
        log.debug("hi");
        AnswerDao answerDao = new AnswerDao();
        String para = req.getParameter("answerId");
        log.debug("answerId : {}", para);

        Long answerId = Long.parseLong(para);

        Result result = answerDao.delete(answerId);
        req.setAttribute("result", result);

        return new JsonView(req);
    }
}
