package uml.sc.potencial.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Cita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty
    @Column(name = "fecha_solicitud")
    private Date fechaSolicitud;

    private String nombres;
    private String apellidos;
    private String dni;
    private String dependencia;

    @Column(name = "num_documento")
    private String numDocumento;

    @Column(name = "fecha_cita")
    private Date fechaCita;

    @Column(name = "fecha_atencion")
    private Date fechaAtencion;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_inicio")
    private LocalTime hora_inicio;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_fin")
    private LocalTime hora_fin;

    @Column(name = "elapsed_time")
    public Date elapsedTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_cita")
    private LocalTime hora_cita;

    @Column(name = "fecha_peritaje") //nullable true
    private Date fechaPeritaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajador_id", nullable = true)
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Trabajador trabajador;

    @ManyToOne()
    @JoinColumn(name= "estadoId")
    private EstadoCita estado;

//    @JsonIgnore
//    @OneToMany(mappedBy = "cita", fetch = FetchType.LAZY)
//    private Set<Reprogramacion> reprogramaciones;

    @ManyToOne()
    @JoinColumn(name= "userId")
    private Usuario usuario;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}
