package com.winter.mapper;

import com.winter.model.QuartzConfig;

import java.util.List;

public interface QuartzConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuartzConfig record);

    QuartzConfig selectByPrimaryKey(Long id);

    List<QuartzConfig> selectAll();

    int updateByPrimaryKey(QuartzConfig record);

    int addJob(QuartzConfig record);
}