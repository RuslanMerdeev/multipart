package org.example.controller;

import org.example.dto.UploadMediaFormRequestDTO;
import org.example.dto.UploadMultipleMediaResponseDTO;
import org.example.dto.UploadSingleMediaResponseDTO;
import org.example.exception.UnimplementedOperationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {

  @PostMapping("/bytes")
  public UploadSingleMediaResponseDTO upload(@RequestBody byte[] bytes, @RequestHeader("Content-Type") String contentType) {
    // TODO: ваш код
    throw new UnimplementedOperationException();
  }

  @PostMapping("/raw")
  public ResponseEntity<?> upload(@RequestBody byte[] bytes, @RequestHeader("X-Part-Number") int partNumber) {
    // TODO: ваш код
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        // дальше можно подставлять через builder любые заголовки
        .body(bytes);
  }

  @PostMapping("/multipart")
  public UploadSingleMediaResponseDTO upload(@RequestPart MultipartFile file) {
    // TODO: ваш код
    throw new UnimplementedOperationException();
  }

  @PostMapping("/multi-multipart")
  public UploadMultipleMediaResponseDTO upload(@RequestPart List<MultipartFile> files) {
    // TODO: ваш код
    throw new UnimplementedOperationException();
  }

  @PostMapping("/multipart-model")
  public void upload(@ModelAttribute UploadMediaFormRequestDTO model) {
    // TODO: ваш код
    throw new UnimplementedOperationException();
  }
}
