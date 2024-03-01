package com.app.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "environments")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200, message = "Environment name must be in 200 characters range")
    private String name;

    @Size(max = 20, message = "Environment ip must be in 20 characters range")
    private String ip;
}
