package br.com.jvsm.proposalgen.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Proposta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime data;
	private String identificao;

	/*
	@ElementCollection
	@CollectionTable(name = "produtos_quantidade", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "quantidade")
	private List<Integer> quantidade = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "produtos_desconto", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "desconto")
	private List<BigDecimal> desconto = new ArrayList<>();*/

	@OneToMany(mappedBy = "proposta", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ProdutosProposta> produtosProposta  = new ArrayList<>();


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getIdentificao() {
		return identificao;
	}
	public void setIdentificao(String identificao) {
		this.identificao = identificao;
	}
	public List<ProdutosProposta> getProdutosProposta() {
		return produtosProposta;
	}
	public void setProdutosProposta(List<ProdutosProposta> produtosProposta) {
		this.produtosProposta = produtosProposta;
	}


}
