package com.file.upload.model.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class FileResponse {

    private String message;

    private boolean status;

    private Timestamp uploadTime;

    private String fileName;

}
