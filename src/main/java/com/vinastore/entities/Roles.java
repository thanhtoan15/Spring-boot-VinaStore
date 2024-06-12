package com.vinastore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[roles]")
public class Roles implements Serializable {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "role_id")
    @JsonIgnore
    private List<Accounts> accounts;
}
