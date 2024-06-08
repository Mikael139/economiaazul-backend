package br.com.globalsolution.economiaazul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "denuncias")
@Getter
@Setter
public class DenunciaAnonimaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;    
    private String datadadenuncia;
    private String horarioDenuncia;
    private String observacao;

    @Lob
    private byte[] imagem;

}
