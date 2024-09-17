package com.userimran.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
//    @Valid(massage="first name should not be empty")
    private String firstName;
    private String lastName;
//    @Email(message = "email cannot be empty")
    private String email;
    private String mobile;
//    @Valid
    private String password;
    private String addr;
    private String city;
    private String state;
    private String pincode;
    private String filePath;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles_map",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "roleId"
            )
    )
    private List<Role> roles;

}
