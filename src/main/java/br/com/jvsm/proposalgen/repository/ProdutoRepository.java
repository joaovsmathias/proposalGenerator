package br.com.jvsm.proposalgen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jvsm.proposalgen.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{

		
	//List<Produto> findById(Long id);
}
