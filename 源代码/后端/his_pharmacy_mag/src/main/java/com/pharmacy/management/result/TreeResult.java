package com.pharmacy.management.result;

import java.util.List;

public class TreeResult {
    private String label; // 节点标签
    private List<String> children; // 子节点标签

    public TreeResult(String label, List<String> children) {
        this.label = label;
        this.children = children;
    }

    public TreeResult(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }
}
