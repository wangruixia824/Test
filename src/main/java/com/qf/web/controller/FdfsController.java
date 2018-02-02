package com.qf.web.controller;

import com.qf.utils.FdfsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FdfsController {
    private String url = "http://39.107.110.188/";
    @Autowired
    private FdfsUtils fdfsUtils;
    @RequestMapping(value = "fdfsupload.op",method = RequestMethod.POST)
    public  String upload(MultipartFile file, Model model){
        String path="";
        if(!file.isEmpty()){
            System.out.println("上传文件："+file.getOriginalFilename());
            try {
                path = fdfsUtils.uploadBytes(file.getBytes(),file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(url+path);
        model.addAttribute("path",path);
        return "file.jsp";
    }
}
