package com.beingjavaguys.controllers;

import java.util.List;

import com.beingjavaguys.aspect.Procedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beingjavaguys.models.User;
import com.beingjavaguys.services.DataService;

/**
 * @author Nagesh.Chauhan
 *
 */

@Controller

@RequestMapping("/api/users")
public class RestController {
	private static Logger logger = LoggerFactory.getLogger(RestController.class);
	@Autowired
	DataService dataService;
	@Procedure
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<User> list() {
		logger.info("- start list");
		List<User> list=dataService.getUserList();
		logger.info("- end list");
		return list;
	}
}
