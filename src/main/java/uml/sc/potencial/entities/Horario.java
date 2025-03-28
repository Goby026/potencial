package uml.sc.potencial.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalTime;
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
public class Horario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Temporal(TemporalType.TIME)
    @Column(name = "ingreso")
    private LocalTime ingreso;

    @Temporal(TemporalType.TIME)
    @Column(name = "salida")
    private LocalTime salida;

    private String programacion;
    private String cargamasiva;

    private int estado;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}
