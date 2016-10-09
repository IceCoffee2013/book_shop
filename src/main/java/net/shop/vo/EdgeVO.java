package net.shop.vo;

import java.io.Serializable;

/**
 * Created by Langley on 30/09/2016.
 */
public class EdgeVO implements Serializable{

    private static final long serialVersionUID = 1585124761314873407L;
    private String from;
    private String to;
    private String arrow;
    private String label;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getArrow() {
        return arrow;
    }

    public void setArrow(String arrow) {
        this.arrow = arrow;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // TODO
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EdgeVO edgeVO = (EdgeVO) o;

        if (from != null ? !from.equals(edgeVO.from) : edgeVO.from != null) return false;
        if (to != null ? !to.equals(edgeVO.to) : edgeVO.to != null) return false;
        if (arrow != null ? !arrow.equals(edgeVO.arrow) : edgeVO.arrow != null) return false;
        return label != null ? label.equals(edgeVO.label) : edgeVO.label == null;

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (arrow != null ? arrow.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
