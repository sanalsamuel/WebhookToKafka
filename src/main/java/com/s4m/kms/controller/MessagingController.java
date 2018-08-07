package com.s4m.kms.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.s4m.kms.model.Message;
import com.s4m.kms.service.KafkaSender;
import com.s4m.kms.util.ApiConstants;

@RestController
@RequestMapping(ApiConstants.API)
public class MessagingController {

	

	@Autowired
	protected ObjectMapper mapper;
	
	@Autowired
	protected KafkaSender kafkaSender;
	

	@PostMapping(value = ApiConstants.MESSAGE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void s4mUserLogin(@Valid @RequestBody Message message, BindingResult bindingResult,
			HttpSession session) {
		
		kafkaSender.send(message.getMsg());

	}

	

}
