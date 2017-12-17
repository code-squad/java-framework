package core.web;

import java.util.HashMap;
import java.util.Map;

import next.controller.Controller;
import next.controller.json.AddAnswerController;
import next.controller.json.DeleteAnswerController;
import next.controller.jsp.CreateQuestionController;
import next.controller.jsp.CreateUserController;
import next.controller.jsp.ForwardController;
import next.controller.jsp.HomeController;
import next.controller.jsp.ListUserController;
import next.controller.jsp.LoginController;
import next.controller.jsp.ProfileController;
import next.controller.jsp.ShowQuestionController;
import next.controller.jsp.UpdateUserController;
import next.controller.jsp.UpdateUserFormController;

public class RequestMapping {
	private Map<String, Controller> controllers;

	private RequestMapping() {
		controllers = initControllers();
	}

	public static RequestMapping createRequestMapping() {
		return new RequestMapping();
	}

	private static Map<String, Controller> initControllers() {
		Map<String, Controller> controllerMap = new HashMap<>();
		controllerMap.put("/users/create", new CreateUserController());
		controllerMap.put("/users/form", new ForwardController("/user/form"));
		controllerMap.put("/", new HomeController());
		controllerMap.put("/users", new ListUserController());
		controllerMap.put("/users/login", new LoginController());
		controllerMap.put("/users/loginForm", new ForwardController("/user/login"));
		controllerMap.put("/users/profile", new ProfileController());
		controllerMap.put("/users/update", new UpdateUserController());
		controllerMap.put("/users/updateForm", new UpdateUserFormController());
		controllerMap.put("/qna/show", new ShowQuestionController());
		controllerMap.put("/qna/create", new CreateQuestionController());
		controllerMap.put("/api/qna/addanswer", new AddAnswerController());
		controllerMap.put("/api/qna/deleteanswer", new DeleteAnswerController());
		return controllerMap;
	}

	public Controller findController(String path) {
		Controller controller = controllers.get(path);
		if (controller == null) {
			return new ForwardController(path);
		}
		return controllers.get(path);
	}

}
