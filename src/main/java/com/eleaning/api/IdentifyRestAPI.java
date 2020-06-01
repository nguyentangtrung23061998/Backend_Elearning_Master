package com.eleaning.api;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eleaning.bean.ResponseBean;
import com.eleaning.entity.LectureEntity;
import com.eleaning.entity.UserEntity;
import com.eleaning.service.ILectureService;
import com.eleaning.service.IUserService;
import com.eleaning.util.Constant;
import com.eleaning.util.Util;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/identify")
public class IdentifyRestAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(IdentifyRestAPI.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILectureService lectureService;
	
	@PostMapping("/upload/{id}")
	private ResponseEntity<ResponseBean> uploadUser(@PathVariable long id, @RequestParam("file") MultipartFile file)
			throws IOException {
		System.out.println("âkasjdaksjd");
		LectureEntity lecture = lectureService.findById(id);
		ResponseBean responseBean = new ResponseBean();

		if (lecture != null) {
			boolean checkUpload = Util.upload(file);
			if (checkUpload) {
				String orginalFile = file.getOriginalFilename();
				String extension= orginalFile.substring(orginalFile.lastIndexOf(".") +1);
				for (String extensionVideo : Constant.extensionVideo) {
					if (extension.equals(extensionVideo)) {
						lecture.setVideo(orginalFile);
					}
				}
				for (String extensionImg : Constant.extensionImg) {
					if (extension.equals(extensionImg)) {
						lecture.setImage(orginalFile);
					}
				}
				for (String extensionAudio : Constant.extensionAudio) {
					if (extension.equals(extensionAudio)) {
						lecture.setAudio(orginalFile);
					}
				}
				for (String extensionDocument : Constant.extensionDocument) {
					if (extension.equals(extensionDocument)) {
						lecture.setDocument(orginalFile);
					}
				}
				LectureEntity lectureSave = lectureService.save(lecture);
				responseBean.setData(lectureSave);
				responseBean.setSuccess();
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
			} else {
				responseBean.setFailUpload();
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
			}
		}
		return null;
	}
}
