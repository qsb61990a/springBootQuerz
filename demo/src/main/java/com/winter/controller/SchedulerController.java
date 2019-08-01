package com.winter.controller;

import com.winter.model.BasicResult;
import com.winter.model.QuartzConfig;
import com.winter.quartz.MySchedulerFactory;
import com.winter.service.AsyncQuartzConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/3/20/020.
 */

@RestController
@CrossOrigin
@RequestMapping("/quartz")
public class SchedulerController{

    //激活状态码
    private static final int ACTIVE_CODE=0;
    //停止状态码
    private static final int PAUSE_CODE=1;

    //事务模版
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    AsyncQuartzConfService asyncQuartzConfService;

    @Autowired
    MySchedulerFactory mySchedulerFactory;

    @GetMapping("list")
    public BasicResult list(@RequestParam(value = "queryObj", required = false) String queryObj,
                            @RequestParam(value = "currentPage", required = true) String currentPage,
                            @RequestParam(value = "pageSize", required = true) String pageSize) {
        try {
            return BasicResult.isOk().data(asyncQuartzConfService.getJobList());
        } catch (Exception e) {
            return BasicResult.isFail(e);
        }
    }

    @PostMapping("edit")
    public BasicResult editJob(@RequestParam(value = "id", required = true) long id,
                               @RequestParam(value = "cron", required = true) String cron,
                               @RequestParam(value = "msg", required = true) String msg) {
        try {
            int i = asyncQuartzConfService.updateJob(id, cron, msg);
            return BasicResult.isOk().data(i);

        } catch (Exception e) {
            return BasicResult.isFail(e);
        }
    }

    @PostMapping("pausejob")
    public BasicResult pausejob(@RequestParam(value = "id", required = true) long id) {

        return transactionTemplate.execute(new TransactionCallback<BasicResult>() {
            @Override
            public BasicResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    mySchedulerFactory.pauseJob(id);
                    asyncQuartzConfService.updateJobStatus(id,PAUSE_CODE);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    return BasicResult.isFail(e);
                }
                return BasicResult.isOk();
            }
        });
    }


    @PostMapping("resumejob")
    public BasicResult resumeJob(@RequestParam(value = "id", required = true) long id) {
        return transactionTemplate.execute(new TransactionCallback<BasicResult>() {
            @Override
            public BasicResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    asyncQuartzConfService.updateJobStatus(id,ACTIVE_CODE);
                    mySchedulerFactory.resumeJob(id);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    return BasicResult.isFail(e);
                }
                return BasicResult.isOk();
            }
        });

    }

    @PostMapping("addjob")
    public BasicResult addjob(@RequestParam(value = "group", required = true) String group,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "cron", required = true) String cron,
                              @RequestParam(value = "status", required = true) long status,
                              @RequestParam(value = "msg", required = true) String msg,
                              @RequestParam(value = "quartzClass", required = true) String quartzClass,
                              @RequestParam(value = "id", required = true) long id
    ) {
        return transactionTemplate.execute(new TransactionCallback<BasicResult>() {
            @Override
            public BasicResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    QuartzConfig quartzConfig = new QuartzConfig();
                    quartzConfig.setCron(cron);
                    quartzConfig.setId(id);
                    quartzConfig.setMsg(msg);
                    quartzConfig.setStatus(1);
                    quartzConfig.setGroup(group);
                    quartzConfig.setName(name);
                    quartzConfig.setQuartzClass(quartzClass);
                    asyncQuartzConfService.addJob(quartzConfig);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    return BasicResult.isFail(e);
                }
                return BasicResult.isOk();
            }
        });

    }
}
