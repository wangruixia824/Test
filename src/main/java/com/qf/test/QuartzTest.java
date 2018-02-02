package com.qf.test;

import com.qf.service.RankService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import   java.util.Calendar;


/*执行定时任务*/
public class QuartzTest {
    public static void main(String[] args) throws SchedulerException {
        //helloQuartz();
        //java();
       // simple();
        //cron();
        //test04();
        test3();
    }

    //Quartz的初体验
    private static void helloQuartz() throws SchedulerException {
        //获取计划对象
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //触发条件  ，从现在开始，每秒重复一次
        Trigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity("tri1","group1").
                withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        //作业详情
        JobDetail jobDetail = JobBuilder.newJob(Hello.class).build();
        //将触发条件和作业添加到计划中
        scheduler.scheduleJob(jobDetail,trigger);
        //开启计划
        scheduler.start();
    }

    //java中自带的定时器
    private static  void java(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("别迷糊了--"+ System.currentTimeMillis()/1000);
            }
        },0,2000);
    }

    //SimpleTrigger的使用
    private static void simple() throws SchedulerException {
        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //Lambda表达式实现Job接口
        Job job = (JobExecutionContext jx)->{
            System.out.println("OK");
        };

        //触发器
        SimpleTriggerImpl trigger = new SimpleTriggerImpl();
        trigger.setName("ha");
        trigger.setRepeatCount(6);
        trigger.setStartTime(new Date());

        Calendar calendar =Calendar.getInstance();
        calendar.add(Calendar.MINUTE,30);
        trigger.setEndTime(calendar.getTime());
        trigger.setRepeatInterval(10);
        //添加调度器
        scheduler.scheduleJob(JobBuilder.newJob(Job.class).build(),trigger);
        scheduler.start();
    }

    //SimpleTigger的使用
    public static void test3() throws SchedulerException {
        //1.获取计划对象
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2.获取作业详情
        JobDetail jobDetail = JobBuilder.newJob(Hello.class).build();
        //3.添加触发条件
        SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
        //设置trigger的name,新版本必须有name,group可以省略
        simpleTrigger.setKey(TriggerKey.triggerKey("tri2"));
        //重复次数
        simpleTrigger.setRepeatCount(10);
        //起始时间
        simpleTrigger.setStartTime(new Date());
        Calendar calendar = Calendar.getInstance();
        //结束时间
        calendar.add(Calendar.MINUTE,3);
        simpleTrigger.setEndTime(calendar.getTime());
        //每次时间间隔--单位是毫秒
        simpleTrigger.setRepeatInterval(1000);
        //4.将作业详情和触发条件添加到计划对象
        scheduler.scheduleJob(jobDetail,simpleTrigger);
        scheduler.start();
    }

   //Cron表达式
    public static void cron() throws SchedulerException {
        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
       //触发器---基于cron表达式
        //每天下午的4-5点每个5s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cron1","group1").
                withSchedule(CronScheduleBuilder.cronSchedule("0/5 * 10-11 * * ?")).build();
        //将作业添加到调度器中
        scheduler.scheduleJob(JobBuilder.newJob(JsJob.class).build(),cronTrigger);
        //开始计划
        scheduler.start();
        System.out.println("计划已经启动");
    }

    //传输对象
    private static void test04() throws SchedulerException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dao.xml");
        RankService service = context.getBean(RankService.class);
        JobDetail jobDetail = JobBuilder.newJob(RankJob.class).build();
        //传递
        JobDataMap dataMap = jobDetail.getJobDataMap();
        dataMap.put("service",service);
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("20 * * * * ?")).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
    }
}
