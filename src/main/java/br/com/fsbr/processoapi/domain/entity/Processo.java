package br.com.fsbr.processoapi.domain.entity;

import br.com.fsbr.processoapi.domain.enums.UF;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String npu;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataVisualizacao;
    @Enumerated(EnumType.STRING)
    private UF uf;
    private String municipio;
}
