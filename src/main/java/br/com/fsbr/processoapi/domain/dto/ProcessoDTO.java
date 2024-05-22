package br.com.fsbr.processoapi.domain.dto;

import br.com.fsbr.processoapi.domain.enums.UF;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ProcessoDTO {
    private UUID id;
    private String npu;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataVisualizacao;
    private UF uf;
    private String municipio;
    private InputStreamSource documento;
}
