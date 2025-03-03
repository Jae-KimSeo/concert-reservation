package com.project.concert_reservation;

import com.project.concert_reservation.application.queue.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@EnableScheduling
@SpringBootApplication
public class ConcertReservationApplication {
    private static final Logger logger = LoggerFactory.getLogger(ConcertReservationApplication.class);

    public static void main(String[] args) {
        startDockerCompose();
        SpringApplication.run(ConcertReservationApplication.class, args);
    }

    private static void startDockerCompose() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("docker-compose", "-f", "docker-compose.yml", "up", "-d");

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                logger.error("Docker Compose 실행 중 오류 발생");
            } else {
                logger.info("Docker Compose 실행 완료");
            }

        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}
