package ru.marina.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    @Column(name="name")
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    public Role() {
    }

    public Role(Long role_id) {
        this.role_id = role_id;
    }

    public Role(Long role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
