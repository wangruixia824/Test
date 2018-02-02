package com.qf.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.service.RankService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RankJob implements Job{

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        RankService service = (RankService) dataMap.get("service");
        try {
            System.out.println(new ObjectMapper().writeValueAsString(service.getByPage(1,10)));
            System.out.println("时间："+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
