package uml.sc.potencial.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@ToString
@Entity
@Table(name = "trabajadores")
public class Trabajador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty
    private String nombres;
//    @NotEmpty
    private String apellidos;
//    @NotEmpty
    private String dni;
    private String dfiscal;
    private String dependencia;
    private String despacho;
    private String siglasdes;

    @ManyToOne()
    @JoinColumn(name = "cargo_id", nullable = true)
    private Cargo cargo;

//    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaing;

    private Date fechanac;

    private String reglab;

//    @Email
    private String correoper;
//    @Email
    private String correoins;
    private String celular;
    private int estado;

    private String presupuesto;
    private String colegiatura;
    private String observacion;
    private int fotocheck;

    private String imagen;

    @ManyToOne()
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private Tipotrabajador tipotrabajador;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}
