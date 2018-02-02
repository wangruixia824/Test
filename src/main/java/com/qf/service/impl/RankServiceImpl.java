package com.qf.service.impl;

import com.qf.dao.RankDao;
import com.qf.domain.Rank;
import com.qf.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankDao rankDao;


    public boolean save(Rank rank) {
        return rankDao.save(rank)!=null;
    }

    public boolean update(Rank rank) {
        return rankDao.save(rank)!=null;
    }

    public boolean update(int score, int groupNo) {
        return rankDao.update(score,groupNo)>0;
    }

    public Page<Rank> getByPage(int page, int size) {
        Pageable pageable = new PageRequest(page-1,size);
        return rankDao.findAll(pageable);
    }

    public Rank getById(Long id) {
        return rankDao.getOne(id);
    }


    public List<Rank> getByIds(Long[] ids) {
        return null;
    }


  public List<Rank> selectById(Long id) {
        Specification<Rank> specification = new Specification<Rank>() {

            public Predicate toPredicate(Root<Rank> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.gt(root.get("id").as(Long.class),id);
            }
        };
        return rankDao.findAll(specification);
    }

    public List<Rank> getByProjectNameLike(String projectName){
        return rankDao.getByProjectNameLike(projectName);
    }
}
