package com.eleaning.api;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

import com.eleaning.service.impl.CourseService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test")
public class TestAPI {
	@Autowired
	private CourseService courseService;
	
//	@Autowired
//	ServletContext servletContextl;
//	
//	@ResponseBody
//	@RequestMapping(value = "/image-resource", method = RequestMethod.GET)
//	public Resource getImageAsResource() {
//	   return (Resource) new ServletContextResource(servletContextl, "/WEB-INF/images/2.jpg");
//	}
	
//	@RequestMapping(value = "/image-resource2", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<Resource> getImageAsResource2() {
//	    HttpHeaders headers = new HttpHeaders();
//	    
//	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//	}
}
