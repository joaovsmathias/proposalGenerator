package br.com.jvsm.proposalgen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jvsm.proposalgen.models.Produto;
import br.com.jvsm.proposalgen.repository.ProdutoRepository;

@RestController
public class HelloController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	@GetMapping("/api")
	public String index() {
		
		//Produto produto = new Produto();
		//produto.setNome("Primeiro Produto");
		//produto.setDescricao("Teste de descricao primeiro Produto");
		//produto.setValor(new BigDecimal(100));
		//produto.setDesconto(new BigDecimal(10));
		//produtoRepository.save(produto);
		return "Greetings from Spring Boot!";
	}
	@GetMapping("/api/produtos")
	public List<Produto> listaProdutos() {
		List<Produto> produtos = produtoRepository.findAll();
		
		return produtos;
	}

}