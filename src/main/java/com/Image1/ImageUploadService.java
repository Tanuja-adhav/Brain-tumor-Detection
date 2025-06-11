
package com.Image1;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageUploadService {

    private static final String ML_API_URL = "http://127.0.0.1:5000/predict";
    private static final String UPLOAD_DIR = "uploads/";

    public String saveImageLocally(MultipartFile file) throws Exception {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        String uniqueName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = UPLOAD_DIR + uniqueName;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }
        return filePath;
    }

    public Map<String, Object> sendImageToMLModel(String filePath) throws Exception {
        File imageFile = new File(filePath);
        if (!imageFile.exists()) throw new Exception("File not found: " + filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(imageFile));

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response = restTemplate.postForEntity(ML_API_URL, request, Map.class);
        Map<String, Object> result = response.getBody();

        System.out.println("🔍 Parsed FastAPI response: " + result);
        return result;
    }
}







//package com.Image1;
//
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.nio.file.Files;
//import java.util.Map;
//import java.util.UUID;
//
//@Service
//public class ImageUploadService {
//
//  private static final String ML_API_URL = "http://127.0.0.1:5000/predict";
//  private static final String UPLOAD_DIR = "uploads/";
//
//  /* ---------- Save file locally ---------- */
//  public String saveImageLocally(MultipartFile file) throws Exception {
//      File dir = new File(UPLOAD_DIR);
//      if (!dir.exists()) dir.mkdirs();
//
//      String uniqueName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//      String filePath = UPLOAD_DIR + uniqueName;
//
//      try (FileOutputStream fos = new FileOutputStream(filePath)) {
//          fos.write(file.getBytes());
//      }
//      return filePath;
//  }
//
//  /* ---------- Send image to FastAPI ---------- */
//  public String sendImageToMLModel(String filePath) throws Exception {
//      File imageFile = new File(filePath);
//      if (!imageFile.exists()) throw new Exception("File not found: " + filePath);
//
//      HttpHeaders headers = new HttpHeaders();
//      headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//      MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//      body.add("file", new FileSystemResource(imageFile));
//
//      HttpEntity<MultiValueMap<String, Object>> request =
//              new HttpEntity<>(body, headers);
//
//      RestTemplate restTemplate = new RestTemplate();
//
//      /* ---- 1️⃣  try JSON map ---- */
//      try {
//          ResponseEntity<Map> jsonResp =
//                  restTemplate.postForEntity(ML_API_URL, request, Map.class);
//
//          Map<?,?> map = jsonResp.getBody();
//          if (map != null && map.get("prediction") != null) {
//              return map.get("prediction").toString();
//          }
//      } catch (Exception ignore) {
//          // Fall through to plain-text parse
//      }
//
//      /* ---- 2️⃣  plain-string fallback ---- */
//      ResponseEntity<String> txtResp =
//              restTemplate.postForEntity(ML_API_URL, request, String.class);
//
//      System.out.println("🔍 Raw FastAPI response: " + txtResp.getBody());
//      return txtResp.getBody() != null ? txtResp.getBody() : "No prediction";
//  }
//}
