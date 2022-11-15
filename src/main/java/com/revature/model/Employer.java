package com.revature.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Employer {
    @Getter @Setter  private int id;
    @Getter @Setter private String accountType;
    @Getter @Setter private String accountName;
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private String phoneNumber;
    @Getter @Setter private String email;
    @Getter @Setter private String location;
}

