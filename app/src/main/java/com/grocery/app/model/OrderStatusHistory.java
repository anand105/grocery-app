package com.grocery.app.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order_status_history")
public class OrderStatusHistory {

    @Id
    private String id;

    private String orderId;

    private String status;

    private LocalDateTime timestamp;
}