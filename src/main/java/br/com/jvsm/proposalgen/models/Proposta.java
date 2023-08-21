package br.com.jvsm.proposalgen.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Proposta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime data;
	private String identificao;
	
	@ElementCollection
	@CollectionTable(name = "produtos_quantidade", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "quantidade")
	private List<Integer> quantidade = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "produtos_desconto", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "desconto")
	private List<BigDecimal> desconto = new ArrayList<>();
	
	@ManyToMany(cascade={CascadeType.MERGE})
    @JoinTable(name = "proposta_produto",
               joinColumns = @JoinColumn(name = "proposta_id"),
               inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos  = new ArrayList<>();
	
	
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
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Integer> getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(List<Integer> quantidade) {
		this.quantidade = quantidade;
	}
	public List<BigDecimal> getDesconto() {
		return desconto;
	}
	public void setDesconto(List<BigDecimal> desconto) {
		this.desconto = desconto;
	}
	
	

}
