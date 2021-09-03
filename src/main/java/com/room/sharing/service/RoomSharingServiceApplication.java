package com.room.sharing.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RoomSharingServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(RoomSharingServiceApplication.class, args);
  }

}
