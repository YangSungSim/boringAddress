package com.example.lazyaddress.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter  @Setter
@NoArgsConstructor
public class Address {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    private String phone;

    public Address(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Address(Long id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}
