package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.TestBulkInsert;
import com.example.sweater.domain.User;
import com.example.sweater.repos.TestBulkInsertRepository;
import com.example.sweater.repos.UserRepo;
import com.example.sweater.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TestService testService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

    @GetMapping(value = "/upload")
    public String upload() throws IOException {
      /*  try(BufferedReader reader = Files.newBufferedReader(Paths.get("D:/66/fileTest.txt"))) {
            List<String> collect = reader.lines().collect(Collectors.toList());
        } ;
        return "";*/
        long time = System.currentTimeMillis();
        List<TestBulkInsert> longList = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream("D:/66/fileTest.txt"); Scanner sc = new Scanner(inputStream, "UTF-8")) {

            while (sc.hasNextLine()) {
                TestBulkInsert testBulkInsert = new TestBulkInsert();
                testBulkInsert.setValue(Long.parseLong(sc.nextLine()));
                longList.add(testBulkInsert);

                if (longList.size() == 100000) {
                    testService.saveData(longList);
                    longList.clear();
                   // break;
                }

                // System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }
        System.out.println((System.currentTimeMillis() - time) / 1000);
        return "";
    }
/*
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload2(@RequestPart("someVal") FilePart filePart) throws IOException {

        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);

        InputStream inputStream = new FileInputStream(filePart.)
        long time = System.currentTimeMillis();
        int batchSize = 1000;
        List<TestBulkInsert> longList = new ArrayList<>();
        int counter = 0;
        try (FileInputStream inputStream = new FileInputStream("D:/66/fileTest.txt"); Scanner sc = new Scanner(inputStream, "UTF-8")) {

            while (sc.hasNextLine()) {
                counter++;
                TestBulkInsert testBulkInsert = new TestBulkInsert();
                testBulkInsert.setValue(Long.parseLong(sc.nextLine()));
                longList.add(testBulkInsert);

                if (longList.size() == 100000) {
                    testService.saveData(longList);
                    longList.clear();
                    // break;
                }

                // System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }
        System.out.println(System.currentTimeMillis() - time);
        return "";
    }*/
}
