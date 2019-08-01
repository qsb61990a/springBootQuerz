package com.winter.service.Impl;

import com.winter.mapper.QuartzConfigMapper;
import com.winter.model.QuartzConfig;
import com.winter.service.AsyncQuartzConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Created by jinyu on 2018/4/13/013.
 */
@Service
@Transactional
public class AsyncQuartzConfServiceImpl implements AsyncQuartzConfService{
    private TransactionTemplate transactionTemplate;

    @Autowired
    QuartzConfigMapper quartzConfigMapper;


    @Override
    public List<QuartzConfig> getJobList() {
        List<QuartzConfig> quartzConfigList = quartzConfigMapper.selectAll();
        return quartzConfigList;
    }

    @Override
    public QuartzConfig findById(long id) {
        return quartzConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateJob(long id, String cron, String msg) {
        QuartzConfig quartzConfig = quartzConfigMapper.selectByPrimaryKey(id);
        quartzConfig.setCron(cron);
        quartzConfig.setMsg(msg);
        return quartzConfigMapper.updateByPrimaryKey(quartzConfig);
    }

    @Override
    public int updateJobStatus(long id, int status) {
        QuartzConfig quartzConfig = quartzConfigMapper.selectByPrimaryKey(id);
        quartzConfig.setStatus(status);
        return quartzConfigMapper.updateByPrimaryKey(quartzConfig);
    }

    @Override
    public int addJob(QuartzConfig quartzConfig) {
        return quartzConfigMapper.addJob(quartzConfig);
    }
}
