package org.example.advancedrealestate_be.controller.api.auth;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/user")
public class FileController {

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("/var/data/uploads/user/images").resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Không thể đọc file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Lỗi khi tải file: " + fileName, e);
        }
    }

    @GetMapping("/building/{fileName:.+}")
    public ResponseEntity<Resource> getFileBuiding(@PathVariable String fileName) {
        try {
            String uploadDir = "/var/data/uploads/building/images";
            System.out.println("Thư mục upload Render: " + uploadDir);

            Path filePath = Paths.get(uploadDir).resolve(fileName);
            System.out.println("Full path: " + filePath.toAbsolutePath());
            System.out.println("Exists: " + Files.exists(filePath));
            System.out.println("Readable: " + Files.isReadable(filePath));

            Resource resource = new UrlResource(filePath.toUri());
            System.out.println("Resource exists: " + resource.exists());
            System.out.println("Resource readable: " + resource.isReadable());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Không thể đọc file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Lỗi khi tải file: " + fileName, e);
        }
    }


    @GetMapping("/auction-contract/{fileName:.+}")
    public ResponseEntity<Resource> getFileAuctionContract(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("/var/data/uploads/auction-contract/images").resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Không thể đọc file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Lỗi khi tải file: " + fileName, e);
        }
    }

    private final Path rootLocation = Paths.get("/var/data/uploads/contracts");
    @GetMapping("/contract/{fileName:.+}")
    public ResponseEntity<Resource> getFileContract(@PathVariable String fileName) {
        try {
            // Xác định đường dẫn tới file
            Path filePath = rootLocation.resolve(fileName).normalize();

            // Kiểm tra xem file có tồn tại không
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy file
            }

            // Tạo một resource từ file
            Resource resource = new UrlResource(filePath.toUri());

            // Kiểm tra xem file có phải là một resource hợp lệ không
            if (resource.exists() || resource.isReadable()) {
                // Trả về file với header "Content-Disposition" để trình duyệt biết là file để tải xuống
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(500).build(); // Trả về lỗi server nếu không thể đọc file
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build(); // Trả về lỗi nếu gặp ngoại lệ
        }
    }
}

