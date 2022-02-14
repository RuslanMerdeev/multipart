package org.example.controller;

import com.github.javafaker.service.RandomService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.dto.UploadUsersResponseDTO;
import org.example.repository.UserRepository;
import org.example.dto.UserRequestDTO;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    final Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();

    @PostMapping("/create")
    @Transactional
    public User create(@RequestBody UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAccount(dto.getAccount());
        String pass = new RandomService().hex(12);
        user.setLogin(new RandomService().hex(12));
        user.setPassword(encoder.encode(pass));
        user = userRepository.save(user);
        user.setPassword(pass);
        return user;
    }

    @PostMapping("/upload")
    public UploadUsersResponseDTO upload(@RequestBody byte[] bytes, @RequestHeader("Content-Type") String contentType) {
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            return new UploadUsersResponseDTO("error", "Can't parse", null, null, null);
        }

        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            if (i == 0) {
                i++;
                continue;
            }
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {

                switch (cell.getCellType()) {
                    case STRING:
                        data.get(i).add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        data.get(i).add(String.valueOf((int) cell.getNumericCellValue()));
                        break;
                    default:
                        break;
                }
            }
            i++;
        }

        List<User> ok = new ArrayList<>();
        List<String> failed = new ArrayList<>();
        List<String> doubled = new ArrayList<>();

        for (List<String> record : data.values()) {
            if (record.size() >= 2) {
                UserRequestDTO dto = new UserRequestDTO(record.get(0), record.get(1));
                try {
                    User user = create(dto);
                    ok.add(user);
                } catch (DataIntegrityViolationException e){
                    doubled.add(record.get(0));
                }
                catch (Exception e) {
                    failed.add(record.get(0));
                }
            }
        }

        return new UploadUsersResponseDTO("ok", "", failed, doubled, ok);
    }
}
