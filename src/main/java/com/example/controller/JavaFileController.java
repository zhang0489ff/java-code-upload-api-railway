package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class JavaFileController {

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestBody Map param) {

        Map<String, Object> response = new HashMap<>();

        try {
            if (file != null && !file.isEmpty()) {
                String content = new String(file.getBytes(), StandardCharsets.UTF_8);
                response.put("codeContent", content);
                return ResponseEntity.ok(response);
            }
            String fileUrl = param.get("fileUrl").toString();
            if (fileUrl != null && fileUrl.startsWith("http")) {
                URL url = new URL(fileUrl);
                URLConnection conn = url.openConnection();
                try (InputStream is = conn.getInputStream()) {
                    String content = readFromInputStream(is);
                    response.put("codeContent", content);
                    return ResponseEntity.ok(response);
                }
            }

            response.put("error", "未上传文件，也未提供有效 URL");
            return ResponseEntity.badRequest().body(response);

        } catch (IOException e) {
            response.put("error", "文件处理失败：" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private String readFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int nRead;
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    }
}
