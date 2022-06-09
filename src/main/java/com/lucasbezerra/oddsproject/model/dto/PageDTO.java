package com.lucasbezerra.oddsproject.model.dto;

import java.util.List;
import java.util.Objects;

public abstract class PageDTO<T, P> {
    private long total;
    private List<T> items;

    public PageDTO() {
    }

    public PageDTO(int total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageDTO pageDTO = (PageDTO) o;
        return total == pageDTO.total &&
                Objects.equals(items, pageDTO.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, items);
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }

    public abstract void mapPageToDTO(P paginator);

    public void mapPageToDTO(List<T> items, int totalItems, int pageSize) {
        return;
    }
}
