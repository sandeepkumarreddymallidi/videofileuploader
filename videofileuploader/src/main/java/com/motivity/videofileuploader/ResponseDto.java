package com.motivity.videofileuploader;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseDto {
    String name;
    byte[] datas;
}
