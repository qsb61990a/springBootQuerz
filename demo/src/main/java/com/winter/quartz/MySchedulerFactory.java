package com.winter.quartz;

import com.winter.model.QuartzConfig;
import com.winter.service.AsyncQuartzConfService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调度工厂类
 * Created by jinyu on 2018/4/14/014.
 */
@Service
@Component
public class MySchedulerFactory {
    private static Logger logger = LoggerFactory.getLogger(MySchedulerFactory.class);
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    // 任务配置读取服务
    @Autowired
    private AsyncQuartzConfService asyncQuartzConfService;

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = getScheduler();
        startJob(scheduler);
    }

    // 获取scheduler
    private Scheduler getScheduler(){
       return schedulerFactoryBean.getScheduler();
    }
    // 项目启动 开启任务
    private void startJob(Scheduler scheduler)  {
        try {
            List<QuartzConfig> jobList = asyncQuartzConfService.getJobList();
            for (QuartzConfig config : jobList) {
                // 1-暂停的任务 0-正常运行任务
                if (1l==config.getStatus()){
                    continue;
                }
                @SuppressWarnings("unchecked")
                Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(config.getQuartzClass());
                JobDetail jobDetail = JobBuilder.newJob(clazz)
                        .withIdentity(config.getName(), config.getGroup()).build();
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(config.getCron());
                CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(config.getName(), config.getGroup())
                        .withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, cronTrigger);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    // 任务暂停
    public void pauseJob(long id) throws SchedulerException {
        Scheduler scheduler = getScheduler();
        QuartzConfig QuartzConfig = asyncQuartzConfService.findById(id);
        JobKey jobKey = JobKey.jobKey(QuartzConfig.getName(), QuartzConfig.getGroup());
        scheduler.deleteJob(jobKey);
    }

    // 任务恢复
    public void resumeJob(long id) throws SchedulerException, ClassNotFoundException {
        Scheduler scheduler = getScheduler();
        QuartzConfig QuartzConfig = asyncQuartzConfService.findById(id);
        JobKey jobKey = JobKey.jobKey(QuartzConfig.getName(), QuartzConfig.getGroup());
        Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(QuartzConfig.getQuartzClass());
        JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
        if (jobDetail1==null){
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(QuartzConfig.getName(), QuartzConfig.getGroup()).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(QuartzConfig.getCron());
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(QuartzConfig.getName(), QuartzConfig.getGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }else {
            scheduler.resumeJob(jobKey);
        }
    }
}
