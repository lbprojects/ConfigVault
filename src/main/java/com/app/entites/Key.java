package com.app.entites;

import com.app.entites.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "config_keys")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Key extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 300, message = "Keys name must be between 2 and 300 characters long")
    private String name;

    @NotBlank
    @Size(max = 300, message = "Keys value must be in 300 characters range")
    private String value;

    @Size(max = 100, message = "Keys creator value must be in 100 characters range")
    private String creator;

}
