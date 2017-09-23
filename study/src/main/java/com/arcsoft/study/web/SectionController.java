package com.arcsoft.study.web;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arcsoft.study.domain.Section;
import com.arcsoft.study.service.ISectionService;
import com.arcsoft.study.util.QINIUServer;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;

@RestController
public class SectionController {

    @Resource
    private ISectionService sectionService;

    @Resource
    private MultipartConfigElement multipartConfigElement;

    @RequestMapping("/addSection")
    public int addSection(@RequestParam(value = "filename", required = false) MultipartFile file, Section section,
            String courseId) {
        Long sectionId = sectionService.addSection(section);
        if (file != null) {
            // 构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone2());
            String key = "/course/" + courseId + "/" + section.getChapterId() + "/" + sectionId + "/"
                    + file.getOriginalFilename();
            try {
                UploadManager uploadManager = new UploadManager(cfg);
                byte[] uploadBytes = file.getBytes();
                String upToken = QINIUServer.getUpToken();
                // 默认不指定key的情况下，以文件内容的hash值作为文件名
                try {
                    Response response = uploadManager.put(uploadBytes, key, upToken);
                    // 解析上传成功的结果
                    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                    sectionService.updateUrl(sectionId, key);
                } catch (QiniuException ex) {
                    Response r = ex.response;
                    System.err.println(r.toString());
                    try {
                        System.err.println(r.bodyString());
                    } catch (QiniuException ex2) {
                        // ignore
                    }
                }
            } catch (IOException ex) {
                // ignore
            }
        }
        return 1;
    }

    @RequestMapping("/updateSection")
    public int updateSection(@RequestParam(value = "filename", required = false) MultipartFile file, Section section,
            String courseId) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // ...其他参数参考类注释
        BucketManager bucketManager = new BucketManager(QINIUServer.getAuth(), cfg);
        String deleteKey = section.getUrl();
        try {
            bucketManager.delete("arcsoft", deleteKey);
        } catch (QiniuException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

        if (file != null) {
            System.out.println(file.getOriginalFilename());
            try {
                // 构造一个带指定Zone对象的配置类
                String key = "/course/" + courseId + "/" + section.getChapterId() + "/" + section.getId() + "/"
                        + file.getOriginalFilename();
                UploadManager uploadManager = new UploadManager(cfg);
                byte[] uploadBytes = file.getBytes();
                String upToken = QINIUServer.getUpToken();
                uploadManager.put(uploadBytes, key, upToken);
                section.setUrl(key);
                // 默认不指定key的情况下，以文件内容的hash值作为文件名
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        return sectionService.updateSection(section);
    }

    @RequestMapping("/deleteSection")
    public int deleteSection(Section section) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // ...其他参数参考类注释
        BucketManager bucketManager = new BucketManager(QINIUServer.getAuth(), cfg);
        String deleteKey = section.getUrl();
        try {
            bucketManager.delete("arcsoft", deleteKey);
        } catch (QiniuException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return sectionService.deleteSection(section);
    }
}
