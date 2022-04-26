package com.softuni.springdataintro.controllers;

import com.softuni.springdataintro.constants.GlobalConstants;
import com.softuni.springdataintro.utils.FileUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {
    private final FileUtil fileUtil;

    public AppController(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);
    }
}
