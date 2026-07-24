package com.peopletech.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("hot_rank")
public class HotRank {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String phoneName;
    private String brand;
    private Integer rankNo;
    private Integer heat;
    private String icon;
    private String period;
    private Boolean enabled;
    private Integer sort;

    private Integer deleted;

    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPhoneName() { return phoneName; }
    public void setPhoneName(String phoneName) { this.phoneName = phoneName; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Integer getRankNo() { return rankNo; }
    public void setRankNo(Integer rankNo) { this.rankNo = rankNo; }
    public Integer getHeat() { return heat; }
    public void setHeat(Integer heat) { this.heat = heat; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public Integer getSort() { return sort; }
    public void setSort(Integer sort) { this.sort = sort; }
    public Integer getDeleted() { return deleted; }
    public void setDeleted(Integer deleted) { this.deleted = deleted; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}