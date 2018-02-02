package com.qf.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Hello implements Job{
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("醒醒，，快起来敲代码---"+System.currentTimeMillis()/1000);
    }
}
