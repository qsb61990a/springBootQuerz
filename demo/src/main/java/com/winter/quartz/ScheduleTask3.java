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

import java.io.Serializable;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 定时任务实现类
 *
 * @author jingu
 * Created by jinyu on 2018/4/12/012.
 */
@Configuration
@Component
@EnableScheduling
public class ScheduleTask3 implements Job, Serializable {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTask1.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("====== 定时任务实现类（代理验证）ScheduleTask ====> 开启!");
        try {

        } catch (Exception e) {
            logger.error("==== 定时任务实现类（代理验证）ScheduleTask ====>异常!", e.getMessage());
            return;
        } finally {
            logger.info("==== 定时任务实现类（代理验证）ScheduleTask ====> 结束!");
        }
    }
}
