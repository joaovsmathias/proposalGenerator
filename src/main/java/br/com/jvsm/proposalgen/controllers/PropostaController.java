package br.com.jvsm.proposalgen.controllers;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jvsm.proposalgen.dto.RquisicaoPropostaDTO;
import br.com.jvsm.proposalgen.models.Produto;
import br.com.jvsm.proposalgen.models.Proposta;
import br.com.jvsm.proposalgen.repository.ProdutoRepository;
import br.com.jvsm.proposalgen.repository.PropostaRepository;
import br.com.jvsm.proposalgen.service.ReportService;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("proposta")
public class PropostaController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	PropostaRepository propostaRepository;
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/cadastro")
	public String cadastroPropostas(Model model, RquisicaoPropostaDTO proposta) {
		List<Produto> options = new ArrayList<Produto>();
		List<Produto> produtos = produtoRepository.findAll();
		produtos.forEach(produto ->  options.add(produto));
		model.addAttribute("options", options);
		
		return "formpropostas";
	}
	@PostMapping("/nova")
	public String novo(Model model, RquisicaoPropostaDTO propostaDTO) {
		//System.out.println(proposta.getNome());
		//System.out.println(Arrays.toString( proposta.getProdutos().toArray()));
		Proposta proposta = propostaDTO.toProposta();
		List<Produto> produtos = new ArrayList<>();
		//percorrer toda a lista de produtos e adicionar na proposta
		List<Integer> quantidade = new ArrayList<>();
		List<BigDecimal> desconto = new ArrayList<>();
		proposta.setDesconto(desconto);
		proposta.setQuantidade(quantidade);
		
		propostaDTO.getProdutos().forEach(id ->{
			Optional<Produto> produto = produtoRepository.findById(Long.parseLong(id));
			//produto.get().setDesconto(new BigDecimal(0));
			//produto.get().setQuantidade(1);
			quantidade.add(1);
			desconto.add(new BigDecimal(0));
			produtos.add(produto.get());
			
		});
		proposta.setProdutos(produtos);
		model.addAttribute("proposta", proposta);
		//propostaRepository.save(proposta);
		return "formgeracao";
	}
	
	@PostMapping("/geracao")
	public String geraProposta(Model model, Proposta proposta) {
		/*
		Proposta proposta2 = propostaRepository.getById(new Long(54));
		
		System.out.println(proposta2.getIdentificao());
		System.out.println(proposta2.getData());
		proposta2.getProdutos().forEach(produto -> {
			System.out.println(produto.getNome());
			//produtoRepository.saveAndFlush(produto);
		});
		proposta2.getQuantidade().forEach(quantidade ->{
			System.out.println(quantidade);
		});
		proposta2.getDesconto().forEach(desconto ->{
			System.out.println(desconto);
		});*/
		propostaRepository.save(proposta);
		model.addAttribute("id",proposta.getId());
		return "redirect:/proposta/cadastro";
	}
	
	@GetMapping("/report/{format}")
	public String geraRelatorio(@PathVariable String format) throws FileNotFoundException, JRException {
		
		
		reportService.exportReport(format);
		return "redirect:/proposta/cadastro";
	}

}
