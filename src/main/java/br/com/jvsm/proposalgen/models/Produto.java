package br.com.jvsm.proposalgen.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;


	public List<Proposta> getProposta() {
		return proposta;
	}
	public void setProposta(List<Proposta> proposta) {
		this.proposta = proposta;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToMany(mappedBy = "produtos",cascade={CascadeType.MERGE})
	private List<Proposta> proposta;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}




}
