package com.example.petofyReplica.model.Recycler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecReqestParams {
    @SerializedName("PageNumber")
    @Expose
    private int pageNo;

    @SerializedName("pageSize")
    @Expose
    private int pageSize;

    public RecReqestParams(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
