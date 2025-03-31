package com.example.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class JavaFileController {

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (file.isEmpty() || !file.getOriginalFilename().endsWith(".java")) {
                response.put("error", "仅支持上传 .java 文件");
                return ResponseEntity.badRequest().body(response);
            }

            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            response.put("codeContent", content);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("error", "文件处理失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
