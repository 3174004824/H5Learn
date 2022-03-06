package com.h5education.demo.service.after;

import com.h5education.demo.dao.VideoDao;
import com.h5education.demo.Util.FileUtil;
import com.h5education.demo.pojo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateVideoServiceImpl implements UpdateVideoService {

    @Autowired
    VideoDao videoDao;

    @Value("../Video/")
    String fileSavePath;

    @Override
    public Boolean saveVideo(String name,String url,String introduce) {
        videoDao.insertVideo(name,url,introduce);
        return null;
    }

    @Override
    public Boolean deleteVideo(int id) {
        Video video = videoDao.selectById(id);
        if (video != null){
            String url = video.getUrl();
            boolean b = FileUtil.deleteFile(url);
            if (b){
                boolean b1 = videoDao.deleteById(id);

                if (!b1){
                    return false;
                }
            }
            return b;
        }
        return false;
    }
}
