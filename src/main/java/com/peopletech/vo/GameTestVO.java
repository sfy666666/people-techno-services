package com.peopletech.vo;

import java.math.BigDecimal;

public class GameTestVO {
    private String gameName;
    private BigDecimal avgFps;
    private String settings;
    private BigDecimal power;
    private String remark;

    public String getGameName() { return gameName; } public void setGameName(String gameName) { this.gameName = gameName; }
    public BigDecimal getAvgFps() { return avgFps; } public void setAvgFps(BigDecimal avgFps) { this.avgFps = avgFps; }
    public String getSettings() { return settings; } public void setSettings(String settings) { this.settings = settings; }
    public BigDecimal getPower() { return power; } public void setPower(BigDecimal power) { this.power = power; }
    public String getRemark() { return remark; } public void setRemark(String remark) { this.remark = remark; }
}
