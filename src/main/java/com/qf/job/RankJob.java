package com.qf.job;

import com.qf.service.QuartzRankService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RankJob implements Job{


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        QuartzRankService rankService = (QuartzRankService) jobExecutionContext.getJobDetail().
                getJobDataMap().get("quartzRankService");
        System.out.println(rankService);
        System.out.println("定时备份："+System.currentTimeMillis());
        rankService.barkRank();
        System.out.println("定时备份结束"+System.currentTimeMillis());
    }
}
