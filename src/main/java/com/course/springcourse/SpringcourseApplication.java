package com.course.springcourse;

import com.course.springcourse.services.FileStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringcourseApplication  {
// implements CommandLineRunner

    @Resource
    FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(SpringcourseApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        storageService.init();
//    }
}
