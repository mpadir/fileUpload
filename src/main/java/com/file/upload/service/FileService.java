package com.file.upload.service;

import com.file.upload.exception.FileTypeException;
import com.file.upload.model.FileModel;
import com.file.upload.model.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    FileResponse  saveFile(MultipartFile multipartFile) throws IOException, FileTypeException;

    List<FileModel> listFile();

    FileModel getFileById(Integer id);

    void deleteFileById(Integer id);

    void deleteAllFile();

    void updateFileById(Integer id, MultipartFile multipartFile) throws IOException;


}
