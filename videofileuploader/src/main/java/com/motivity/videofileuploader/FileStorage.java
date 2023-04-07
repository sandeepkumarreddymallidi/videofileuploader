package com.motivity.videofileuploader;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "files")
@Data
@RequiredArgsConstructor
public class FileStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    private byte[] data;

}
