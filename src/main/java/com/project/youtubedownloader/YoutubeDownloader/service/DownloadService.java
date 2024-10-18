package com.project.youtubedownloader.YoutubeDownloader.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DownloadService {

    public InputStreamResource download(String url, String format) {
        String outputFileName = format.equals("bestaudio") ? "output.webm" : "output.mp4.webm";
        format = format.equals("bestaudio") ? "bestaudio" : "bestvideo+bestaudio";

        // Usar ProcessBuilder para ejecutar el comando
        ProcessBuilder processBuilder = new ProcessBuilder("yt-dlp", "-f", format , "-o", outputFileName, url);

        try {
            Process process = processBuilder.start();

            // Captura las salidas de error y de salida
            try (BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = outputReader.readLine()) != null) {
                    System.out.println("OUTPUT: " + line);
                }
                while ((line = errorReader.readLine()) != null) {
                    System.err.println("ERROR: " + line);
                }
            }

            // Esperar a que el proceso termine
            process.waitFor();
            if (process.exitValue() != 0) {
                throw new RuntimeException("yt-dlp failed with exit code: " + process.exitValue());
            }

            // Comprobar si el archivo de salida existe
            Path outputFile = Path.of(outputFileName);
            if (Files.exists(outputFile)) {
                return new InputStreamResource(Files.newInputStream(outputFile));
            } else {
                throw new RuntimeException("Failed to download content: Output file does not exist.");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to download content: " + e.getMessage());
        }
    }
}