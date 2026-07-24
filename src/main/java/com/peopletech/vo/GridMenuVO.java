package com.peopletech.vo;

public class GridMenuVO {
    private String id;
    private String label;
    private String icon;
    private String iconBg;
    private String path;

    public GridMenuVO() {}
    public GridMenuVO(String id, String label, String icon, String iconBg, String path) {
        this.id = id; this.label = label; this.icon = icon; this.iconBg = iconBg; this.path = path;
    }

    public String getId() { return id; } public void setId(String id) { this.id = id; }
    public String getLabel() { return label; } public void setLabel(String label) { this.label = label; }
    public String getIcon() { return icon; } public void setIcon(String icon) { this.icon = icon; }
    public String getIconBg() { return iconBg; } public void setIconBg(String iconBg) { this.iconBg = iconBg; }
    public String getPath() { return path; } public void setPath(String path) { this.path = path; }
}
