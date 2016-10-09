package net.shop.vo;

import java.io.Serializable;

/**
 * Created by Langley on 30/09/2016.
 */
public class NodeVO implements Serializable{

    private static final long serialVersionUID = -2610024992121431119L;

    private String id;
    private String label;
    private String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeVO nodeVO = (NodeVO) o;

        return label.equals(nodeVO.label);

    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}
