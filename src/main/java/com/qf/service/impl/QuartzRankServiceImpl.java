package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.domain.Rank;
import com.qf.service.QuartzRankService;
import com.qf.service.RankService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartzRankServiceImpl implements QuartzRankService {
    //存储到list类型。需要从数据库查询，进行排序，按照主键升序
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RankService rankService;

    public void barkRank() {
        List<Rank> list;
        long id = 0;
        //1，从redis查询上次备份的最大主键值
        if(redisUtils.exists("ranks")){
            String json = redisUtils.getList("ranks");
            if(json.length()>0){
                Rank rank = JSON.parseObject(json,Rank.class);
                id=rank.getId();
            }
        }
        //2.查询数据库，从上一次的备份的最大值开始查询
        list = rankService.selectById(id);
        if(list.size()>0){
            //将查询到的数据保存到Redis中
            for(Rank rank : list){
                redisUtils.ladd("ranks",JSON.toJSONString(rank));
            }
        }
    }
}
