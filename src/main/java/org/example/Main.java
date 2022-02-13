package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.io.IOException;

@SpringBootApplication
public class Main {
  public static void main(String[] args) throws IOException {
    final Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
    System.out.println(encoder.encode("secret"));
    SpringApplication.run(Main.class, args);
  }
}
