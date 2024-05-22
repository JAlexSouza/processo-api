package br.com.fsbr.processoapi.repository;

import br.com.fsbr.processoapi.domain.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, UUID> {
    Processo findByNpu(String npu);
}
