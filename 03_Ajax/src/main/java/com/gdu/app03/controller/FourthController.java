package com.gdu.app03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app03.service.IFourthService;

@Controller
public class FourthController {

	// field
	@Autowired
	private IFourthService fourthService;
	
	@GetMapping("/image/display")
	public ResponseEntity<byte[]> display(@RequestParam("path") String path, @RequestParam("filename") String filename) {
		return fourthService.display(path, filename);
	}
	
}