package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@Table(name = "TB_CALCULO")
@Entity
public class Calculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "n")
    private String n;
    @Column(name = "k")
    private String k;
    @Column(name = "resultado")
    private int resultado;
    @JoinColumn(name = "id_usuario")
    @ManyToOne
    @JsonIgnore
    private Usuario usuario;
    public Calculo (String n,String k,int resultado,Usuario usuario){
        this.k = k;
        this.n = n;
        this.resultado = resultado;
        this.usuario = usuario;
    }
}
