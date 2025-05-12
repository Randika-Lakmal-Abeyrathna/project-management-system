package me.randika.backend_service.base;

import java.util.List;

public class PaginatedResponse <T>{
    private List<T> data;
    private int size;
    private long totalElements;
    private int totalPage;

    public PaginatedResponse(List<T> data, int size, long totalElements, int totalPage) {
        this.data = data;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
