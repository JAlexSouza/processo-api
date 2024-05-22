package br.com.fsbr.processoapi.service;

import br.com.fsbr.processoapi.config.excep.model.ProcessoJaCadastrado;
import br.com.fsbr.processoapi.config.excep.model.ProcessoNaoEncontrado;
import br.com.fsbr.processoapi.config.storage.FileStorageConfig;
import br.com.fsbr.processoapi.domain.dto.ProcessoDTO;
import br.com.fsbr.processoapi.domain.entity.Processo;
import br.com.fsbr.processoapi.domain.mapper.ProcessoMapper;
import br.com.fsbr.processoapi.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    private final Path fileStorageLocation;

    @Autowired
    private ProcessoMapper processoMapper;

    public ProcessoService(FileStorageConfig fileStorageConfig){
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
    }

    public ResponseEntity<List<ProcessoDTO>> listarProcessos(){

        List<Processo> processos = processoRepository.findAll();

        List<ProcessoDTO> processosDTO = processos.stream().map(processoMapper::processoParaProcessoDTO).toList();

        return new ResponseEntity<List<ProcessoDTO>>(processosDTO, HttpStatus.OK);
    }

    public ResponseEntity<ProcessoDTO> buscarProcesso(UUID id) throws Exception{
        try{
            Processo processo = processoRepository.findById(id).orElseThrow(() -> new RuntimeException());

            return new ResponseEntity<ProcessoDTO>(processoMapper.processoParaProcessoDTO(processo), HttpStatus.OK);
        } catch (RuntimeException ex) {
            throw new ProcessoNaoEncontrado("Processo não encontrado");
        }
    }

    public ResponseEntity criarProcesso(ProcessoDTO processoDTO) throws Exception {
        try {
            Processo processo = processoRepository.findByNpu(processoDTO.getNpu());

            if(!Objects.isNull(processo)){
                throw new ProcessoJaCadastrado("Processo Já cadastrado!");
            }

            processo = processoMapper.processoDTOparaProcesso(processoDTO);
            Processo novoProcesso = processoRepository.save(processo);

            salvarDocumento((MultipartFile) processoDTO.getDocumento(), novoProcesso.getNpu());

            return new ResponseEntity<ProcessoDTO>(processoMapper.processoParaProcessoDTO(novoProcesso), HttpStatus.CREATED);
        } catch (ProcessoJaCadastrado ex){
            throw new ProcessoJaCadastrado("Processo Já cadastrado!");
        }
    }

    private void salvarDocumento(MultipartFile documento, String npu) throws IOException {

        String nomeDocumento = StringUtils.cleanPath(npu.replace(".", "-") + "." + documento.getContentType().replace("application/", ""));
        try {
            Path caminho = fileStorageLocation.resolve(nomeDocumento);
            documento.transferTo(caminho);
        } catch (Exception ex){
            throw new IOException();
        }
    }

    private Resource buscarDocumento(String npu) throws IOException {
        Path caminho = fileStorageLocation.resolve(npu);
        try {
            return new UrlResource(caminho.toUri());
        } catch (Exception ex){
            throw new IOException();
        }
    }
}
