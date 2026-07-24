package com.peopletech.vo;

public class KeyValueVO {
    private String label;
    private String value;
    private Boolean highlight;

    public KeyValueVO() {}
    public KeyValueVO(String label, String value) { this.label = label; this.value = value; }
    public KeyValueVO(String label, String value, Boolean highlight) { this.label = label; this.value = value; this.highlight = highlight; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public Boolean getHighlight() { return highlight; }
    public void setHighlight(Boolean highlight) { this.highlight = highlight; }
}
