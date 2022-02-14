package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.User;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadUsersResponseDTO {
  private String status;
  private String message;
  private List<String> failed;
  private List<String> doubled;
  private List<User> ok;
}
