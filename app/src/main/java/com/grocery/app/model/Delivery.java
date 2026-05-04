package com.grocery.app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "deliveries")
public class Delivery {

    @Id
    private String id;

    private String orderId;

    private String address;

    private String status; // SHIPPED, OUT_FOR_DELIVERY, DELIVERED
}
