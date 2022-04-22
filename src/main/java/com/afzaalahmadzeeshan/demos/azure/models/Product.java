package com.afzaalahmadzeeshan.demos.azure.models;

import java.util.Date;

public class Product {

    private String PartitionKey;
    private String RowKey;
    private Date Timestamp;

    private int id;
    private String title;
    private String description;
    private double price;
    private boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPartitionKey() {
        return PartitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.PartitionKey = partitionKey;
    }

    public String getRowKey() {
        return RowKey;
    }

    public void setRowKey(String rowKey) {
        this.RowKey = rowKey;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.Timestamp = timestamp;
    }
}
