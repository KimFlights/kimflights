package com.kimgroup.kimflights.user.internal;

import com.kimgroup.kimflights.user.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
