package com.winter.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 定时任务实现类
 * Created by jinyu on 2018/4/12/012.
 */
@Configuration
@Component
@EnableScheduling
public class ScheduleTask2 implements Job {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTask2.class);

    // 可用的代理状态
    private static final int EFFECT_PROXY_STAT = 0;
    // 失败计数
    private static int failCount = 0;

    public void executeJob() {

        logger.info("==== 定时任务实现类（获取黄页客户）ScheduleTask ====> 开启!");
        int spliderCounter = 0;
        try {

        } catch (Exception e) {
            logger.error("splider-save-customer--> 异常 spliderCounter:"+spliderCounter, e);
        } finally {
            logger.info("splidert-hread-->  " + Thread.currentThread().getName() + "<--");
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            executeJob();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
