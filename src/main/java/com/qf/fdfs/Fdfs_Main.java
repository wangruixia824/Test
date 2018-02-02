package com.qf.fdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.Arrays;

//基于fdfs实现文件上传
public class Fdfs_Main {
    public static void main(String[] args) throws IOException, MyException {
        //初始化配置文件，加载
        ClientGlobal.init("SSS-Quarzt/src/main/resources/fdfs_client.conf");
        //创建跟踪服务器的客户端
        TrackerClient trackerClient = new TrackerClient();
        //连接跟踪服务器
        TrackerServer trackerServer = trackerClient.getConnection();
        //声明存储服务器
        StorageServer storageServer = null;
        //创建存储客户端
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        String[] strings = storageClient.upload_file("SSS-Quarzt/课堂笔记.txt", "txt", null);
        System.out.println(Arrays.toString(strings));
    }

}
