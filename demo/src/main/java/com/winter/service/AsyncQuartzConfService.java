package com.winter.service;

import com.winter.model.QuartzConfig;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20/020.
 */
public interface AsyncQuartzConfService {

    List<QuartzConfig> getJobList();

    QuartzConfig findById(long id);

    int updateJob(long id, String cron, String msg);

    int updateJobStatus(long id, int status);

    int addJob(QuartzConfig quartzConfig);
}
