package com.winter.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class ScheduleTask4  implements Job, Serializable {
    private static Logger logger = LoggerFactory.getLogger(ScheduleTask1.class);
    // 在运行态
    private static boolean lockeD = false;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("====== 定时任务实现类（测试新增任务）ScheduleTask ====> 开启!");
        try {
            test4();
        } catch (Exception e) {
            logger.error("==== 定时任务实现类（测试新增任务）ScheduleTask ====>异常!", e.getMessage());
        } finally {
            logger.info("==== 定时任务实现类（测试新增任务）ScheduleTask ====> 结束!");
        }
    }

    /**
     * 西刺代理
     *
     * @throws JobExecutionException
     */
    public void test4() throws JobExecutionException {
        logger.info("==== 定时任务实现类（测试新增任务）ScheduleTask ====> 开启!");
        try {
            //处理中
            if (lockeD) {
                return;
            }
            lockeD = true;
            logger.info("================================执行中==============================");
            lockeD = false;
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            lockeD = false;
        }
    }


}
