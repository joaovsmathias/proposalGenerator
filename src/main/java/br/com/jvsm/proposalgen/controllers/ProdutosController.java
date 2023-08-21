package br.com.jvsm.proposalgen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jvsm.proposalgen.dto.RequisicaoProdutoDTO;
import br.com.jvsm.proposalgen.models.Produto;
import br.com.jvsm.proposalgen.repository.ProdutoRepository;

@Controller
@RequestMapping("produto")
public class ProdutosController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/cadastro")
	public String cadastroPedidos(RequisicaoProdutoDTO requisicao) {
		return "formprodutos";
	}
	
	
	@PostMapping("/novo")
	public String novo(RequisicaoProdutoDTO requisicao) {
		//System.out.println(requisicao.getNome());
		//System.out.println(requisicao.getDescricao());
		//System.out.println(requisicao.getValor());
		Produto produto = requisicao.toProduto();
		produtoRepository.save(produto);
		return "redirect:/produto/cadastro";
	}
	
}
