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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ScheduleTask1 implements Job, Serializable {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTask1.class);
    // 在运行态
    private static boolean lockeD = false;

    /**
     * 蘑菇代理
     *
     * @throws JobExecutionException
     */
    public void moguProxyJob() throws JobExecutionException {
        logger.info("==== 定时任务实现类（代理获取）ScheduleTask ====> 开启!");
        try {
            //处理中
            if (lockeD) {
                return;
            }
            logger.info("======================000000000==============================");

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            lockeD = false;
        }
    }

    /**
     * 西刺代理
     *
     * @throws JobExecutionException
     */
    public void xiciProxyJob() throws JobExecutionException {
        logger.info("==== 定时任务实现类（代理xici获取）ScheduleTask ====> 开启!");
        try {
            //处理中
            if (lockeD) {
                return;
            }
            lockeD = true;
            //访问地址
            String kuaiProxy = "http://www.xicidaili.com/nn/";
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9");
            headers.put("Connection", "keep-alive");
            headers.put("Host", "www.xicidaili.com");
            headers.put("Referer", "http://www.xicidaili.com/");
            headers.put("Cookie", "_free_proxy_session=BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJTg3NmYzYWEyNWUxMGQ0NTRiN2Y2Y2FiODEyYWY2OGY1BjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMVM0UldRUXVVOFBRSWQ0SmhucTlWUlFGNjVTdFFsbjRPKzhENGZUbnF0S2c9BjsARg%3D%3D--d3ecb93828cbcd1a29df587606dd087b3930d77b; Hm_lvt_0cf76c77469e965d2957f0553e6ecf59=1530420455; Hm_lpvt_0cf76c77469e965d2957f0553e6ecf59=1530422250");
            headers.put("Upgrade-Insecure-Requests", "1");
            headers.put("If-None-Match", "W/\"7659c0b44110cbf4df9a774f24f7ce77\"");
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");

            lockeD = false;
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            lockeD = false;
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("====== 定时任务实现类（获取代理ip池）ScheduleTask ====> 开启!");
        try {
            moguProxyJob();
            //xiciProxyJob();
        } catch (Exception e) {
            logger.error("==== 定时任务实现类（获取代理ip池）ScheduleTask ====>异常!", e.getMessage());
        } finally {
            logger.info("==== 定时任务实现类（获取代理ip池）ScheduleTask ====> 结束!");
        }
    }


    class Mogu {
        private String code;
        private List<Map<String, String>> msg;

        public Mogu(String code, List<Map<String, String>> msg) {
            this.code = code;
            this.msg = msg;
        }

        public Mogu() {
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<Map<String, String>> getMsg() {
            return msg;
        }

        public void setMsg(List<Map<String, String>> msg) {
            this.msg = msg;
        }
    }
}