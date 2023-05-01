package com.file.upload.repository;


import com.file.upload.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Integer> {

}
