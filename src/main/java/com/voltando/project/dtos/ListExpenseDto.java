package com.voltando.project.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class ListExpenseDto {
    private Integer id;
    private String description;
    private BigDecimal value;
    private Date date;
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ListExpenseDto(Integer id, String description, BigDecimal value, Date date, String category) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }
}
