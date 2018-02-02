package com.qf.domain;

import javax.persistence.*;

@Entity
@Table(name = "rank")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;//分数
    @Column(length = 20,unique = true)
    private String projectName;//项目名
    private int groupNo;//组号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", score=" + score +
                ", projectName='" + projectName + '\'' +
                ", groupNo=" + groupNo +
                '}';
    }
}
