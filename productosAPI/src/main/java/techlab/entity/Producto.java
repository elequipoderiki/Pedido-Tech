package techlab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Titulo es requerido")
    private String title;

    @NotNull(message = "Precio es requerido")
    @Min(value = 0, message = "Precio debe ser mayor o igual a 0")
    private Double price;

    @Min(value = 0, message = "Stock debe ser mayor o igual a 0")
    private Integer stock;

    private String category;

    @Column(columnDefinition="TEXT")
    @NotBlank(message = "Descripcion es requerida")
    private String description;

    private String image;


}

