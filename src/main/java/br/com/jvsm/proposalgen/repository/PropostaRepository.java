package br.com.jvsm.proposalgen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jvsm.proposalgen.dto.PropostaRelatorioDTO;
import br.com.jvsm.proposalgen.models.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta,Long> {

	
	//@Query("select p from Proposta p join ProdutosProposta pp on p.id = pp.id order by p.id desc limit 1")
	@Query("select p from Proposta p order by p.id DESC limit 1")
	Proposta encontraUltimaProposta(); 	
	
	/*@Query("SELECT new br.com.jvsm.proposalgen.dto.PropostaRelatorioDTO(p.id, pp.id, pr.nome, pr.descricao, dp.quantidade, dp.desconto) "
	         + "FROM Proposta p "
	         + "JOIN ( SELECT MAX(id) AS max_id FROM Proposta ) max_proposta ON p.id = max_proposta.max_id "
	         + "JOIN ProdutosProposta pp ON p.id = pp.proposta.id "
	         + "JOIN Produto pr ON pp.proposta.id = pr.id "
	         + "JOIN DetalhesProdutoProposta dp ON pp.id = dp.produtosProposta.id")*/
	
	@Query("SELECT new br.com.jvsm.proposalgen.dto.PropostaRelatorioDTO(p.id, pp.id, pr.nome, pr.descricao, dp.quantidade, dp.desconto) "
			+ "FROM Proposta p "
			+ "JOIN (SELECT MAX(id) AS max_id FROM Proposta ) max_proposta ON p.id = max_proposta.max_id "
			+ "JOIN ProdutosProposta pp ON p.id = pp.proposta.id "
			+ "JOIN Produto pr ON pp.proposta.id = pr.id "
			+ "JOIN DetalhesProdutoProposta dp ON pp.id = dp.produtosProposta.id")
	List<PropostaRelatorioDTO> gerarRelatorio();
}
