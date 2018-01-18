package com.billing.model;

import com.billing.helper.constraint.PasswordHashMatch;
import com.billing.helper.hibernate.PostgresEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@PasswordHashMatch(passwordHash = "passwordHash", passwordSalt = "salt", passwordString = "password")
@Entity
@Table(name = "user_details", uniqueConstraints=@UniqueConstraint(columnNames="user_id"))
@TypeDef(name = "pg_sql_enum", typeClass = PostgresEnumType.class)
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    @Size(max = 255, min = 3)
    @NotNull
    private String firstName;

    @Column(name = "lastname")
    @Size(max = 255, min = 1)
    @NotNull
    private String lastName;

    @Column(name = "user_Id")
    @Size(max = 255, min = 3)
    @NotNull
    private String userId;

    @Column
    private String salt;

    @Column(name = "passwd_hash")
    @NotNull
    private String passwordHash;

    @Transient
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "user_role")
    @Type(type = "pg_sql_enum")
    @NotNull
    private UserRole userRole;

    public int getId() {
        return id;
    }
    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }
    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }
    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUserId() {
        return userId;
    }
    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSalt() {
        return salt;
    }
    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public User setPasswordHash(String password) {
        this.passwordHash = password;
        return this;
    }

    public String getPassword() {
        return password;
    }
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public User setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userId, salt, passwordHash, password);
    }
}