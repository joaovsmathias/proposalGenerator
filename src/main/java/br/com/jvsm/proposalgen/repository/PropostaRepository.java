package br.com.jvsm.proposalgen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jvsm.proposalgen.models.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta,Long> {
	
	@Query("select p from Proposta p join ProdutosProposta pp on p.id = pp.id order by p.id desc limit 1")
	Proposta encontraUltimaProposta(); 	

}
