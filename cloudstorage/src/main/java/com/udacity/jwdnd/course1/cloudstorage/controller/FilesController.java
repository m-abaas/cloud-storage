package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@Controller
@RequestMapping("/files")
public class FilesController {


    private final FilesService filesService;
    private int checkSuccess;

    public FilesController(FilesService filesService)
    {
        this.filesService = filesService;
    }

    @GetMapping
    public String getFiles(@RequestParam("fileUpload") MultipartFile fileUpload, Model model)
    {
        return "home";
    }

    @PostMapping
    public String postCredentials(Authentication authentication, @RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {

        if(filesService.checkForFileName(fileUpload.getOriginalFilename()) >= 1)
        {
            // This means that there is a file in the database with the same name
            return "duplicate-file-name";
        }
        this.checkSuccess = filesService.addFile(fileUpload, authentication.getName());
        if (checkSuccess >= 1 ) { model.addAttribute("result", "success"); }

        else { model.addAttribute("result", "failure"); }

        return "result";
    }

    @GetMapping("/delete_file")
    public String deleteFile(Model model, @RequestParam(name="fileId") Integer fileId)
    {
        this.checkSuccess = filesService.deleteFile(fileId);
        if (checkSuccess >= 1 ) { model.addAttribute("result", "success"); }
        else { model.addAttribute("result", "failure"); }

        model.addAttribute("result", "success");

        return "result";
    }

    @GetMapping("/download_file")
    public ResponseEntity<InputStreamResource>  viewFile(@RequestParam(name="fileId") Integer fileId) {
        FileDialog fileService;
        Files file = filesService.getFile(fileId);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(file.getFileData()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_LANGUAGE, "attachment:filename=" + file.getFileName())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(resource);
        }
}


