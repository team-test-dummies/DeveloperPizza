package com.revature.model;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Order {
    @Getter @Setter private int id;
    @Getter @Setter private String skillset;
    @Getter @Setter private String location;
    @Getter @Setter private String availability;
    @Getter @Setter private String salary;
    @Getter @Setter private String experience;
    @Getter @Setter private String education;
    @Getter @Setter private String certifications;
    @Getter @Setter private String languages;
    @Getter @Setter private String frameworks;
    @Getter @Setter private String databases;
    @Getter @Setter private String operatingsystems;
    @Getter @Setter private String tools;
    @Getter @Setter private int orderID;
}
