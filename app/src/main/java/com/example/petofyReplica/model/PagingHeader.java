
package com.example.petofyReplica.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PagingHeader {

    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    @SerializedName("pageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("hasPreviousPage")
    @Expose
    private Boolean hasPreviousPage;
    @SerializedName("previousPage")
    @Expose
    private Integer previousPage;
    @SerializedName("hasNextPage")
    @Expose
    private Boolean hasNextPage;
    @SerializedName("nextPage")
    @Expose
    private Integer nextPage;

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

}