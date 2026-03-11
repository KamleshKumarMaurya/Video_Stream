package com.octal.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@Service
public class VideoProcessingServiceS3 {

//	@Value("${ffmpeg.path}")
//	private String FFMPEG_PATH;
//
//	@Value("${cloudfront.url}")
//	private String CDN_URL;
//
//	@Autowired
//	private S3Service s3Service;
//
//	public String processVideo(MultipartFile file, Long storyId, int episode) throws Exception {
//
//		// Temporary working directory
//		Path tempDir = Files.createTempDirectory("video-processing");
//
//		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//
//		Path inputVideoPath = tempDir.resolve(fileName);
//
//		Files.copy(file.getInputStream(), inputVideoPath, StandardCopyOption.REPLACE_EXISTING);
//
//		Path playlistPath = tempDir.resolve("playlist.m3u8");
//
//		ProcessBuilder builder = new ProcessBuilder(FFMPEG_PATH, "-i", inputVideoPath.toString(), "-profile:v",
//				"baseline", "-level", "3.0", "-start_number", "0", "-hls_time", "10", "-hls_list_size", "0", "-f",
//				"hls", playlistPath.toString());
//
//		builder.redirectErrorStream(true);
//
//		Process process = builder.start();
//
//		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//		while (reader.readLine() != null) {
//		}
//
//		int exitCode = process.waitFor();
//
//		if (exitCode != 0) {
//			throw new RuntimeException("FFmpeg processing failed");
//		}
//
//		// Upload HLS files to S3
//		File folder = tempDir.toFile();
//
//		for (File f : folder.listFiles()) {
//
//			String key = "story_" + storyId + "/episode_" + episode + "/" + f.getName();
//
//			s3Service.uploadFile(f.toPath(), key);
//		}
//
//		// Delete temporary files
//		for (File f : folder.listFiles()) {
//			f.delete();
//		}
//
//		folder.delete();
//
//		return CDN_URL + "/story_" + storyId + "/episode_" + episode + "/playlist.m3u8";
//	}
}