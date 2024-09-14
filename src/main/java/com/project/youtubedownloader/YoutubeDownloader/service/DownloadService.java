package com.project.youtubedownloader.YoutubeDownloader.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DownloadService {

    public InputStreamResource download(String url, String format) {
        String outputFileName = format.equals("bestaudio") ? "output.webm" : "output.mp4";
        try {
            // Define the command to execute yt-dlp
            String command = "yt-dlp -f " + format + " -o " + outputFileName + " " + url;

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);

            // Capture the output and error streams
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Print the output and error streams
            String line;
            while ((line = outputReader.readLine()) != null) {
                System.out.println("OUTPUT: " + line);
            }
            while ((line = errorReader.readLine()) != null) {
                System.err.println("ERROR: " + line);
            }

            // Wait for the process to complete
            process.waitFor();

            // Check if the output file exists
            Path outputFile = Path.of(outputFileName);
            if (Files.exists(outputFile)) {
                // Return the file as an InputStreamResource
                InputStreamResource resource = new InputStreamResource(Files.newInputStream(outputFile));
                return resource;
            } else {
                throw new RuntimeException("Failed to download content: Output file does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to download content: " + e.getMessage());
        }
    }
}

