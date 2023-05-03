package com.file.upload.controller;

import com.file.upload.exception.FileTypeException;
import com.file.upload.model.FileModel;
import com.file.upload.model.response.FileResponse;
import com.file.upload.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@Api(value = "File Api Documentation")
public class FileController {

    @Autowired
    private final FileService service;

    @Autowired
    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping("/save")
    @ApiOperation(value = "File save method")
    public FileResponse saveFile(@RequestParam("file")MultipartFile multipartFile) throws IOException, FileTypeException {
        return service.saveFile(multipartFile);
    }

    @GetMapping("/allFile")
    @ApiOperation(value = "All Files List")
    public List<FileModel> allFile(){
        return service.listFile();
    }

    @GetMapping("/id/{id}")
    @ApiOperation(value = "File Get ById")
    public FileModel getByIdFile(@PathVariable("id") Integer id){
        return service.getFileById(id);
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "File Update metod")
    public void updateFile(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile multipartFile){
        try {
            service.updateFileById(id, multipartFile);
        } catch (Exception exception){
            exception.getMessage();
        }

    }

    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "File Delete All")
    public void deleteAllFiles(){
        service.deleteAllFile();
    }

    @DeleteMapping("/deleteId/{id}")
    @ApiOperation(value = "File Delete ById")
    public void deletById(@PathVariable("id") Integer id){
        service.deleteFileById(id);
    }

}
