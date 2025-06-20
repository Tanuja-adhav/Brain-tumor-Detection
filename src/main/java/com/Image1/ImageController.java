
package com.Image1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

    private final ImageUploadService imageUploadService;

    public ImageController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("error", "No image uploaded");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            System.out.println("📤 Uploading file: " + file.getOriginalFilename());

            String filePath = imageUploadService.saveImageLocally(file);
            Map<String, Object> mlResponse = imageUploadService.sendImageToMLModel(filePath);

            response.put("status", "success");
            response.put("filePath", filePath);
            response.putAll(mlResponse);  // includes structured prediction

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Processing failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}










//package com.Image1;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")  // Allow frontend access
//public class ImageController {
//
//  private final ImageUploadService imageUploadService;
//
//  public ImageController(ImageUploadService imageUploadService) {
//      this.imageUploadService = imageUploadService;
//  }
//
//  @PostMapping("/upload")
//  public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
//      Map<String, Object> response = new HashMap<>();
//
//      if (file.isEmpty()) {
//          response.put("error", "No image uploaded");
//          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//      }
//
//      try {
//          System.out.println("📤 Uploading file: " + file.getOriginalFilename());
//
//          // Process image & send it to ML model
//          String filePath = imageUploadService.saveImageLocally(file);
//          String mlResponse = imageUploadService.sendImageToMLModel(filePath);
//
//          response.put("message", "File processed successfully");
//          response.put("filePath", filePath);  // ✅ Include file path
//          response.put("prediction", mlResponse);
//
//          return ResponseEntity.ok(response);
//      } catch (Exception e) {
//          e.printStackTrace();
//          response.put("error", "Processing failed: " + e.getMessage());
//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//      }
//  }
//}
