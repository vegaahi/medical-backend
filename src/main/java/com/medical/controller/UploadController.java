package com.medical.controller;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;



    @RestController
    @RequestMapping("/uploads")
 public class UploadController {

        private final Path uploadDir = Paths.get("src/main/resources/static/uploads");

        @GetMapping("/{filename}")
        public Resource getImage(@PathVariable String filename) throws Exception {
            Path filePath = uploadDir.resolve(filename).normalize();
            return new UrlResource(filePath.toUri());
        }
    }


