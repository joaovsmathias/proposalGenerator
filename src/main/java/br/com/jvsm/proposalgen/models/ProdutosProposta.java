package br.com.jvsm.proposalgen.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ProdutosProposta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    @OneToMany(mappedBy = "produtosProposta", cascade = CascadeType.ALL)
    private List<DetalhesProdutoProposta> detalhes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<DetalhesProdutoProposta> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<DetalhesProdutoProposta> detalhes) {
		this.detalhes = detalhes;
	}
    
    

}
