package com.merin.newboot.entity;

import com.fasterxml.jackson.databind.JsonNode;



import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    private String id;

    @Column
    private String name;

    @Convert(converter = JsonNodeConverter.class)
    private JsonNode data;

    public Sale() {
        super();
    }

    public Sale(String id, String name, JsonNode data) {
        super();
        this.id = id;
        this.name = name;
        this.data = data;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Sale [id=" + id + ", name=" + name + ", data=" + data + "]";
    }
}
