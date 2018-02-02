package com.qf.web.controller;

import com.qf.common.JsonResult;
import com.qf.domain.Rank;
import com.qf.job.RankJob;
import com.qf.service.QuartzRankService;
import com.qf.service.RankService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//控制器
@Controller
public class RankController {
    @Autowired
    private RankService service;
    @Autowired
    private QuartzRankService quartzRankService;
    @RequestMapping("/index.op")
    public String quartz() throws SchedulerException {
        System.out.println("定时器");
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        System.out.println("-----11111111111-----");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("rank1","grouprank").
                withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
        System.out.println("----------222222222222222---------");
        JobDetail jobDetail = JobBuilder.newJob(RankJob.class).build();
        System.out.println("------------33333333333--------------");
        jobDetail.getJobDataMap().put("quartzRankService",quartzRankService);
        System.out.println("----------44444444444444----------------------");
        scheduler.scheduleJob(jobDetail,cronTrigger);
        System.out.println("-------------5555555555---------------");
        scheduler.start();
        return "rank.jsp";

    }

    //新增
    @RequestMapping(value = "rankadd.op",method = {RequestMethod.POST},params = {"score"})
    @ResponseBody
    public JsonResult add(Rank rank){
        if(service.save(rank)){
            return JsonResult.setOk(null);
        }else {
            return JsonResult.setError(null);
        }
    }

    //查询
    //分页查询
    @RequestMapping(value = "rankpage.op",method = {RequestMethod.GET},params = {"page","size"})
    @ResponseBody
    public JsonResult get(int page,int size){
        Page<Rank> rankPage = service.getByPage(page,size);
        return JsonResult.setOk(rankPage);
    }


}
