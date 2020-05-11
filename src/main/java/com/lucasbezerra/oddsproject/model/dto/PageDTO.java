package com.lucasbezerra.oddsproject.model.dto;

import java.util.List;
import java.util.Objects;

public class PageDTO {
    private int total;
    private List<CountryDTO> items;

    public PageDTO() {
    }

    public PageDTO(int total, List<CountryDTO> items) {
        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CountryDTO> getItems() {
        return items;
    }

    public void setItems(List<CountryDTO> items) {
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
}
