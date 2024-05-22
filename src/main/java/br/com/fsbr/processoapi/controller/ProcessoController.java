package br.com.fsbr.processoapi.controller;

import br.com.fsbr.processoapi.domain.dto.ProcessoDTO;
import br.com.fsbr.processoapi.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/processo")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @GetMapping
    public ResponseEntity<List<ProcessoDTO>> listarProcessos(){
        return processoService.listarProcessos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoDTO> buscarProcesso(@PathVariable UUID id) throws Exception {
        return processoService.buscarProcesso(id);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> criarProcesso(@ModelAttribute ProcessoDTO processoDTO) throws Exception {
        return processoService.criarProcesso(processoDTO);
    }




}
