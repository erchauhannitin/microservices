package com.perfect.microservices.customer.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
public class CatalogItem {

   @Id
   @GeneratedValue
   private int id;
   private String catalogName;
   private String companyName;
   private String catalogDescription;
   private int price;
}
