package com.kiekeboo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kiekeboo.app.model.TestModel;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    // HTTP test request
	@RequestMapping("/kiekeboo")
	public String printKiekeboo(ModelMap model) {
		model.addAttribute("message", "Kiekeboo initial testcontroller!");
		return "kiekeboo";
	}

    // JSON test service
    @RequestMapping(method = RequestMethod.GET, value = "/services/test/{name}/{id}")
    public @ResponseBody TestModel echoJsonObject(
            @PathVariable String name,
            @PathVariable int id) {
        TestModel testModel = new TestModel();
        testModel.setId(id);
        testModel.setName(name);
        return testModel;
    }

}