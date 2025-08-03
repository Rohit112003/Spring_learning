package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository repository){
        return args -> {
            Student mariam =     new Student(
                        "Mariam",
                        "Mariam.jamal@gmail.com",
                        LocalDate.of(2000, Month.JANUARY,5)

            );

            Student ALex =     new Student(
                    "Alex",
                    "Alex.jamal@gmail.com",
                    LocalDate.of(2002, Month.JANUARY,5)

            );
            repository.saveAll(
                    List.of(mariam,ALex)
            );
        };

    }
}
