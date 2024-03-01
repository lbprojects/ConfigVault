package com.app.entites;

import com.app.entites.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 300, message = "Properties name must be between 2 and 300 characters long")
    private String name;

    @NotBlank
    @Size(max = 300, message = "Properties value must be in 300 characters range")
    private String value;

    @Size(max = 100, message = "Properties creator value must be in 100 characters range")
    private String creator;
}
