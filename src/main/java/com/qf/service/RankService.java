package com.qf.service;

import com.qf.domain.Rank;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RankService {
    //新增
    public boolean save(Rank rank);
    //修改
    public boolean update(Rank rank);
    //修改
    public boolean update(int score, int groupNo);
    //查询(通过分页查询)
    public Page<Rank> getByPage(int page, int size);
    //查询单个(通过id查询详情)
    public Rank getById(Long id);
//查询多个id
    public List<Rank> getByIds(Long[] ids);
    //查询条件为主键大于指定值
    public List<Rank> selectById(Long id);
}
