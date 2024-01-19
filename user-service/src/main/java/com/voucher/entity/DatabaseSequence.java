package com.voucher.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;

    private int seq;

    //getters and setters omitted
}