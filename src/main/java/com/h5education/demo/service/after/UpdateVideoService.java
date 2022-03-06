package com.h5education.demo.service.after;

public interface UpdateVideoService {
    Boolean saveVideo(String name,String url,String introduce);

    Boolean deleteVideo(int id);

}
