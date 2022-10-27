package com.procces.business.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    @Column(length = 300, nullable = false)
    @NotBlank(message = "El apellido no puede estar en blanco")
    private String lastName;

    @Column(length = 20, nullable = false, unique = true)
    @NotBlank(message = "El documento no puede estar en blanco")
    private String document;

    @Column(length = 100)
    private String address;

    private Date dateOfBirth;

    @Column(length = 20)
    private String numberPhone;
}
