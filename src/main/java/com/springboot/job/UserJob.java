package com.springboot.job;

import com.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UserJob {
    @Autowired
    UserService userService;
    @Scheduled(cron ="0 0 0 * * ?")
    //每天零点执行
    public void updeUserStatus(){
        log.info("进入定时任务——————————");
       List<Integer> list= userService.getUserId();
       if (!ObjectUtils.isEmpty(list)){
           Integer id =list.get(0);
           log.info("定时任务获取到id{}",list);
           log.info("定时即将修改的id为"+id);
           try {
               log.info("正在睡眠！");
               TimeUnit.MILLISECONDS.sleep(10000);
               log.info("睡眠结束！");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           if(id !=null){
               userService.updateUserStatus(id);
               log.info("定时任务修改了id为"+id);
           }
       }
    }
}
