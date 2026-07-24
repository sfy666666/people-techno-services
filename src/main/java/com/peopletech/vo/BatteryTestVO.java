package com.peopletech.vo;

import java.math.BigDecimal;

public class BatteryTestVO {
    private String scene;
    private String duration;
    private BigDecimal dischargeRate;

    public String getScene() { return scene; } public void setScene(String scene) { this.scene = scene; }
    public String getDuration() { return duration; } public void setDuration(String duration) { this.duration = duration; }
    public BigDecimal getDischargeRate() { return dischargeRate; } public void setDischargeRate(BigDecimal dischargeRate) { this.dischargeRate = dischargeRate; }
}
