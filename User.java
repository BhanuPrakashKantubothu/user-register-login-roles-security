package com.example.springSecurity6.entity;
import com.example.springSecurity6.entity.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private  String username;
    @Column(unique = true)
    private String email;
    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
                                    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setRole(String roleUser) {
        this.roles = getRoles();
    }
}

// 2. joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
// This specifies the foreign key column in the join table that references the User entity.
//
// name = "user_id": the name of the FK column in the join table.
// referencedColumnName = "id": refers to the 'id' field of the User table.


// 3. inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
// Same as above, but for the Role entity.
//
// name = "role_id": the FK column in the join table for Role.
// referencedColumnName = "id": refers to the 'id' field of the Role table.

//Tells JPA to immediately load the roles when a user is loaded from the database.
//LAZY: loads roles only when needed.
//EAGER: loads roles immediately (which can lead to performance issues for large datasets).