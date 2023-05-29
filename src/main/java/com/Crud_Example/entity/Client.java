package com.Crud_Example.entity;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Getter
@Setter
@Builder
@Document("clients")
    public class Client {

        @ArangoId
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String arangoId;
        private String id;
        @Column(name= "first_name")
        private String firstName;
        @Column(name= "last_name")
        private String lastName;
        @Column(name= "city")
        private String city;
        @Column(name= "email")
        private String email;
        @Column(name= "mobile")
        private String mobile;
    }

