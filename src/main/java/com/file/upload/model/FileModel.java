package com.file.upload.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "files", schema = "file_project")
public class FileModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private byte[] content;
    @Column(name = "name")
    private String name;
    @Column(name = "size")
    private int size;
    @Column(name = "timestamp")
    private Timestamp timestamp;

    public FileModel() {

    }
}
