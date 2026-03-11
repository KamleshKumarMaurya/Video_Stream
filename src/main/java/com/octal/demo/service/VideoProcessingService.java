package com.octal.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.UUID;

@Service
public class VideoProcessingService {

	private static final String VIDEO_ROOT = "D:/videos";
	private static final String FFMPEG_PATH = "C:/ffmpeg/bin/ffmpeg.exe";

	public String processVideo(MultipartFile file, Long storyId, int episode) {

		try {

			if (file.isEmpty()) {
				throw new RuntimeException("Video file is empty");
			}

			Path episodeDir = Paths.get(VIDEO_ROOT, "story_" + storyId, "episode_" + episode);
			Files.createDirectories(episodeDir);

			String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
			Path inputVideoPath = episodeDir.resolve(fileName);

			Files.copy(file.getInputStream(), inputVideoPath, StandardCopyOption.REPLACE_EXISTING);

			Path playlistPath = episodeDir.resolve("playlist.m3u8");

//			ProcessBuilder builder = new ProcessBuilder(FFMPEG_PATH, "-i", inputVideoPath.toString(), "-profile:v",
//					"baseline", "-level", "3.0", "-start_number", "0", "-hls_time", "10", "-hls_list_size", "0", "-f",
//					"hls", playlistPath.toString()); // -hls_time 10 => Each chunk = 10 seconds

			ProcessBuilder builder = new ProcessBuilder(FFMPEG_PATH, "-i", inputVideoPath.toString(),

					"-filter_complex",
					"[0:v]split=3[v1][v2][v3];" + "[v1]scale=640:360[v1out];" + "[v2]scale=1280:720[v2out];"
							+ "[v3]scale=1920:1080[v3out]",

					"-map", "[v1out]", "-c:v:0", "libx264", "-b:v:0", "400k", "-map", "[v2out]", "-c:v:1", "libx264",
					"-b:v:1", "1500k", "-map", "[v3out]", "-c:v:2", "libx264", "-b:v:2", "3000k",

					"-f", "hls", "-hls_time", "10", "-hls_playlist_type", "vod", "-master_pl_name", "master.m3u8",
					"-var_stream_map", "v:0 v:1 v:2",

					episodeDir.resolve("playlist_%v.m3u8").toString());

			builder.redirectErrorStream(true);// FFmpeg writes logs to stderr

			Process process = builder.start();// This executes the FFmpeg command.

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

				String line;

				while ((line = reader.readLine()) != null) {
					// System.out.println("[FFMPEG] " + line);
				}
			} // This reads FFmpeg logs

			int exitCode = process.waitFor();// Backend waits until FFmpeg finishes processing => 30MB video → ~5–10
												// seconds

			if (exitCode != 0) {// check if processing failed
				throw new RuntimeException("FFmpeg failed. Exit code: " + exitCode);
			}

			Files.deleteIfExists(inputVideoPath);// Original file removed.

//			return "/story_" + storyId + "/episode_" + episode + "/playlist.m3u8";// return url
			return "/story_" + storyId + "/episode_" + episode + "/master.m3u8";

		} catch (Exception e) {
			throw new RuntimeException("Video processing failed: " + e.getMessage());
		}
	}
}