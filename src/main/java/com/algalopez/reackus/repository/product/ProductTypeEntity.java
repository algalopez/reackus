package com.algalopez.reackus.repository.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Notes:
 * Using repository pattern
 * To use active record pattern, then extend class to PanacheEntity
 */
@Entity(name = "product_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
