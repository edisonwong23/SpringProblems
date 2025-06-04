package Assesment_Secure.demo.controller;

import Assesment_Secure.demo.services.FileProcessingService;
import Assesment_Secure.demo.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class FIleController {

    private final FileProcessingService fileProcessingService;

    @Autowired
    public FIleController(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @GetMapping("/file")
    public String file_form(Model model) {
        return "file";
    }

    @PostMapping("/file")
    public String fileProcessor(@RequestParam("file") MultipartFile file, Model model) {
        try {
            //Checking to see if file is a .txt
            String filename = file.getOriginalFilename();

            if (filename == null || !filename.toLowerCase().endsWith(".txt")) {
                model.addAttribute("error", "Only .txt files are allowed.");
                return "file";
            }

            if (!file.getContentType().equals("text/plain")) {
                model.addAttribute("error", "Invalid content type.");
                return "file";
            }

            //Reads the entire file content into a byte[] array.
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            String result = fileProcessingService.transform(content);
            model.addAttribute("original", content);
            model.addAttribute("result", result);

        } catch (IOException e) {
            model.addAttribute("error", "Error reading file: " + e.getMessage());
        }
        return "file";
    }
}
