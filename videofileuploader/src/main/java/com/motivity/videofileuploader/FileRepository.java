package com.motivity.videofileuploader;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileStorage,Long> {
}
