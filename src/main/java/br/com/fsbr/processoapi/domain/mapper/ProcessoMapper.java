package br.com.fsbr.processoapi.domain.mapper;

import br.com.fsbr.processoapi.domain.dto.ProcessoDTO;
import br.com.fsbr.processoapi.domain.entity.Processo;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProcessoMapper {

    public ProcessoDTO processoParaProcessoDTO(Processo processo){
        return ProcessoDTO.builder()
                .id(processo.getId())
                .npu(processo.getNpu())
                .dataCadastro(processo.getDataCadastro())
                .dataVisualizacao(processo.getDataVisualizacao())
                .uf(processo.getUf())
                .municipio(processo.getMunicipio())
                .build();
    }

    public Processo processoDTOparaProcesso(ProcessoDTO processoDTO) throws IOException {
        return Processo.builder()
                .id(processoDTO.getId())
                .npu(processoDTO.getNpu())
                .dataCadastro(processoDTO.getDataCadastro())
                .dataVisualizacao(processoDTO.getDataVisualizacao())
                .uf(processoDTO.getUf())
                .municipio(processoDTO.getMunicipio())
                .build();
    }
}
