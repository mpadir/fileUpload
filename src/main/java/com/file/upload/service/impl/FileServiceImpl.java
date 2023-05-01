package com.file.upload.service.impl;

import com.file.upload.constants.FileConstants;
import com.file.upload.exception.FileTypeException;
import com.file.upload.model.FileModel;
import com.file.upload.model.response.FileResponse;
import com.file.upload.repository.FileRepository;
import com.file.upload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository repository;

    @Autowired
    public FileServiceImpl(FileRepository repository) {
        this.repository = repository;
    }


    private Optional<String> getFileType(String fileName){
        return Optional.ofNullable(fileName).filter(f -> f.contains(".")).map(f -> f.substring(fileName.lastIndexOf(".") + 1));
    }


    @Override
    public FileResponse saveFile(MultipartFile multipartFile) throws IOException, FileTypeException {

        FileResponse fileResponse = new FileResponse();
        boolean checkFileExtension = typeControlFile(multipartFile.getOriginalFilename());
        if (!checkFileExtension){
            throw new FileTypeException("png, jpeg, jpg, docx, pdf, xlsx must be in format");
        }

        try {

            FileModel model = new FileModel();
            model.setName(multipartFile.getOriginalFilename());
            model.setContent(multipartFile.getBytes());
            model.setSize((int) multipartFile.getSize());
            model.setTimestamp(new Timestamp(System.currentTimeMillis()));
            repository.save(model);

            fileResponse.setUploadTime(new Timestamp(System.currentTimeMillis()));
            fileResponse.setFileName(multipartFile.getOriginalFilename());
            fileResponse.setStatus(true);
            fileResponse.setMessage("File upload successful");

            return fileResponse;

        } catch (Exception exception) {
            exception.printStackTrace();
            exception.getMessage();
            return null;
        }
    }

    @Override
    public List<FileModel> listFile() {
        return repository.findAll();
    }

    @Override
    public FileModel getFileById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteFileById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllFile() {
        repository.deleteAll();
    }

    @Override
    public void updateFileById(Integer id, MultipartFile multipartFile) throws IOException {
        Optional<FileModel> model = repository.findById(id);

        model.get().setName(multipartFile.getOriginalFilename());
        model.get().setContent(multipartFile.getBytes());
        model.get().setSize((int) multipartFile.getSize());
        model.get().setTimestamp(new Timestamp(System.currentTimeMillis()));
        FileModel fileModel = model.get();
        repository.save(fileModel);

    }

    private boolean typeControlFile(String fileName) {
        Optional<String> type = getFileType(fileName);
        boolean bool = FileConstants.constans.contains(type.get().toLowerCase());
        return bool;
    }
}
