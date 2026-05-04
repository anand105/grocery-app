package com.grocery.app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private String id;
    private String orderId;
    private String userId;
    private String name;
    private String phone;
    private String street;
    private String city;
    private String pincode;

}