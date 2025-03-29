package com.Image1;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageUploadService {
    private final String ML_API_URL = "http://127.0.0.1:5000/predict"; 
    private final String UPLOAD_DIR = "uploads/";

    public String saveImageAndSendToML(MultipartFile file) throws Exception {
        // Ensure upload directory exists
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save file locally
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        File savedFile = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(savedFile)) {
            fos.write(file.getBytes());
        }

        // Check if ML model files exist
        Resource modelResource = new ClassPathResource("rf_brain_tumor.pkl");
        Resource scalerResource = new ClassPathResource("scaler.pkl");

        if (!modelResource.exists() || !scalerResource.exists()) {
            return "Error: ML model files are missing.";
        }

        // Send image path to Flask
        return sendImageToMLModel(savedFile);
    }

    private String sendImageToMLModel(File file) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // ✅ Ensure JSON Content-Type

        // Create JSON payload with file path
        Map<String, String> jsonPayload = new HashMap<>();
        jsonPayload.put("path", file.getAbsolutePath());

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(jsonPayload, headers);
        RestTemplate restTemplate = new RestTemplate();

        // Send JSON to Flask
        ResponseEntity<String> response = restTemplate.exchange(
            ML_API_URL, HttpMethod.POST, requestEntity, String.class
        );

        return response.getBody();
    }
}
