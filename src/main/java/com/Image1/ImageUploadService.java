package com.Image1;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class ImageUploadService {
    private final String ML_API_URL = "http://127.0.0.1:5000/predict"; // Flask API URL
    private final String UPLOAD_DIR = "uploads/";

    public String saveImageAndSendToML(MultipartFile file) throws Exception {
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

        return sendImageToMLModel(savedFile);
    }

    public String sendImageToMLModel(File file) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA); // ✅ Correct Content-Type

        // ✅ Correctly wrap the file
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file)); 

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        // ✅ Make sure the URL is correct
        ResponseEntity<String> response = restTemplate.exchange(
            ML_API_URL, HttpMethod.POST, requestEntity, String.class
        );

        return response.getBody();
    }
}