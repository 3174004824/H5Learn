package com.h5education.demo.service.after;

import com.h5education.demo.pojo.Files;

public interface UpdateFileService {

    Boolean insertFile(Files files);

    Boolean deleteFile(int id);

    Files selectById(int id);
}
