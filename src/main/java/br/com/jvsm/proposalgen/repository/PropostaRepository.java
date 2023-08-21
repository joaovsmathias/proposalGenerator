package br.com.jvsm.proposalgen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jvsm.proposalgen.models.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta,Long> {

}
