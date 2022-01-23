package com.perfect.microservices.customer.input;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProductRequest {

   private int pid;
   private String productName;
   private int quantity;
   private int price;
}
