package com.voltando.project.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "expenses")
@Entity
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private BigDecimal value;
    private Date date;
    private String category;
    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

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

    public ExpenseEntity(Integer id, String description, BigDecimal value, Date date, String category, UserEntity userEntity) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
        this.userEntity = userEntity;
    }

    public ExpenseEntity() {
    }
}
