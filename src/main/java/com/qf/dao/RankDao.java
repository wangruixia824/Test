package com.qf.dao;

import com.qf.domain.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankDao extends JpaRepository<Rank,Long>,JpaSpecificationExecutor<Rank>{

    //修改条件为组号  修改分数  用的JPQL
    @Modifying
    @Query("update Rank set score = ?1 where groupNo=?2")
    public int update(int score, int groupNo);

    //查询  条件组号   使用原生的SQL语句
    @Query(value = "select * from rank where groupNo = ?1",nativeQuery = true)
    public Rank selectByGroupNo(int groupNo);

    //查询--方法名  的项目模糊查询
    public List<Rank> getByProjectNameLike(String projectName);

    //动态查询--

}
