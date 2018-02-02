package com.qf.utils;


import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

//封装基于Fastdfs实现文件的上传操作
public class FdfsUtils {
    //创建跟踪服务器的客户端
    private TrackerClient trackerClient;
    //连接跟踪服务器
    private TrackerServer trackerServer;
    //创建存储客户端(StorageClient1是升级版本)
    private StorageClient1 storageClient1;
    //声明存储的服务器
    private StorageServer storageServer;
    //配置文件
    private String conf;
    //有参构造方法
    public FdfsUtils(String conf){
        if(conf.startsWith("classpath")){
            conf = conf.replace("classpath:",FdfsUtils.class.getResource("/").getPath());
        }
        try{
            ClientGlobal.init(conf);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient1 = new StorageClient1(trackerServer,storageServer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //实现文件上传()，通过传入一个文件的路径
    public String uploadFile(String filePath,String extName) {
        try {
            return storageClient1.upload_file1(filePath,extName,null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    //实现文件上传的功能，传入文件的内容
    public String uploadBytes(byte[] contents,String extName){
        if(extName.lastIndexOf(".")>-1){
            extName = extName.substring(extName.lastIndexOf(".")+1);
        }
        try {
          String s =storageClient1.upload_file1(contents,extName,null);
            System.out.println("------------------------------------------");
            System.out.println(s);
          return s;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
