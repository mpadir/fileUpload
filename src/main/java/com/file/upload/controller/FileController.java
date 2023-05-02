package com.file.upload.controller;

import com.file.upload.exception.FileTypeException;
import com.file.upload.model.FileModel;
import com.file.upload.model.response.FileResponse;
import com.file.upload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private final FileService service;

    @Autowired
    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public FileResponse saveFile(@RequestParam("file")MultipartFile multipartFile) throws IOException, FileTypeException {
        return service.saveFile(multipartFile);
    }

    @GetMapping("/allFile")
    public List<FileModel> allFile(){
        return service.listFile();
    }

    @GetMapping("/id/{id}")
    public FileModel getByIdFile(@PathVariable("id") Integer id){
        return service.getFileById(id);
    }

    @PostMapping("/update/{id}")
    public void updateFile(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile multipartFile){
        try {
            service.updateFileById(id, multipartFile);
        } catch (Exception exception){
            exception.getMessage();
        }

    }

    @DeleteMapping("/deleteAll")
    public void deleteAllFiles(){
        service.deleteAllFile();
    }

    @DeleteMapping("/deleteId/{id}")
    public void deletById(@PathVariable("id") Integer id){
        service.deleteFileById(id);
    }

}
