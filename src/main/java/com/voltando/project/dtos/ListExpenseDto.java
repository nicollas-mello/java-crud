package com.voltando.project.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class ListExpenseDto {
    private String description;
    private BigDecimal value;
    private Date date;
    private String category;


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

    public ListExpenseDto(String description, BigDecimal value, Date date, String category) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }
}
