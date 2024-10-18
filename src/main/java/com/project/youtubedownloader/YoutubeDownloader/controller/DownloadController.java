package com.project.youtubedownloader.YoutubeDownloader.controller;

import com.project.youtubedownloader.YoutubeDownloader.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping("/video")
    public ResponseEntity<InputStreamResource> downloadVideo(@RequestParam String url) {
        InputStreamResource videoStream = downloadService.download(url, "bestvideo+bestaudio");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=video.mp4");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(videoStream);
    }

    @GetMapping("/audio")
    public ResponseEntity<InputStreamResource> downloadAudio(@RequestParam String url) {
        InputStreamResource audioStream = downloadService.download(url, "bestaudio");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=audio.webm");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(audioStream);
    }
}
