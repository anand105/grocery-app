package com.grocery.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pincodes")
public class Pincode {

    @Id
    private String code; // ✅ Use pincode itself as ID

    public Pincode() {}

    public Pincode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}