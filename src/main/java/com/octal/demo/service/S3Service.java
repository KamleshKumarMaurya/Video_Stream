package com.octal.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class S3Service {

//	@Autowired
//	private S3Client s3Client;
//
//	@Value("${aws.s3.bucket}")
//	private String bucketName;
//
//	public void uploadFile(Path filePath, String key) {
//
//		PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(key).build();
//
//		s3Client.putObject(request, filePath);
//	}
//
//	public String uploadThumbnail(MultipartFile file) throws Exception {
//
//		String fileName = "thumbnails/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
//
//		File tempFile = File.createTempFile("upload", file.getOriginalFilename());
//		file.transferTo(tempFile);
//
//		PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(fileName)
//				.contentType(file.getContentType()).build();
//
//		s3Client.putObject(request, tempFile.toPath());
//
//		tempFile.delete();
//
//		return fileName;
//	}
}