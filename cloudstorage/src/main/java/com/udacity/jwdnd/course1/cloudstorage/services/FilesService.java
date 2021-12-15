package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class FilesService {

    private final FilesMapper filesMapper;
    private final UserService userService;

    public FilesService(FilesMapper filesMapper, UserService userService) {
        this.filesMapper = filesMapper;
        this.userService = userService;
    }

    public int addFile(MultipartFile fileUpload, String userName) throws IOException {

        return filesMapper.insert(new Files(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(),
                fileUpload.getSize(), userService.getUser(userName).getUserId(),
                fileUpload.getBytes()));
    }

    public List<Files> getFiles(String userName) {
        return this.filesMapper.getFiles(userService.getUser(userName).getUserId());
    }

    public int deleteFile(Integer fileId)
    {
        return this.filesMapper.delete(fileId);
    }

    public Files getFile(Integer fileId)
    {
        return this.filesMapper.getFile(fileId);
    }

    public Integer checkForFileName(String fileName)
    {
        return filesMapper.checkForFileName(fileName);
    }

}
