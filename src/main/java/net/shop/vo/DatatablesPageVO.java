package net.shop.vo;

import java.util.List;

/**
 * Created by Langley on 9/18/16.
 */
public class DatatablesPageVO<T> {

    private List<T> aaData;
    private int iTotalDisplayRecords;
    private int iTotalRecords;

    public DatatablesPageVO() {
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }
}
