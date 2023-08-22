package br.com.jvsm.proposalgen.controllers;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import br.com.jvsm.proposalgen.models.DetalhesProdutoProposta;
import br.com.jvsm.proposalgen.models.Produto;
import br.com.jvsm.proposalgen.models.ProdutosProposta;
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
		
		Proposta proposta = new Proposta();
		//proposta = propostaDTO.toProposta();
		//final Proposta finalProposta = proposta;
		proposta.setIdentificao(propostaDTO.getNome());
		proposta.setData(LocalDateTime.now());
		
		List<ProdutosProposta> listaProdutoProposta = new ArrayList<>();
		

						
		propostaDTO.getProdutos().forEach(id ->{
			Optional<Produto> produto = produtoRepository.findById(Long.parseLong(id));

			ProdutosProposta produtoProposta = new ProdutosProposta();
			produtoProposta.setProduto(produto.get());
			produtoProposta.setProposta(proposta);
			
			List<DetalhesProdutoProposta> listaDetalhesProdutoProposta = new ArrayList<>();
			
			DetalhesProdutoProposta detalhesProdutoProposta = new DetalhesProdutoProposta();
			detalhesProdutoProposta.setDesconto(new BigDecimal(0));
			detalhesProdutoProposta.setQuantidade(1);
			detalhesProdutoProposta.setProdutosProposta(produtoProposta);
			
			listaDetalhesProdutoProposta.add(detalhesProdutoProposta);
			produtoProposta.setDetalhes(listaDetalhesProdutoProposta);
			listaProdutoProposta.add(produtoProposta);
			
		});
		
		
		proposta.setProdutosProposta(listaProdutoProposta);
		model.addAttribute("proposta", proposta);
		propostaRepository.save(proposta);
		return "formgeracao";
	}
	
	@PostMapping("/geracao")
	public String geraProposta(Model model, Proposta proposta) {
		Proposta ultimaProposta = propostaRepository.encontraUltimaProposta();
		
		
		List<ProdutosProposta> listaProdutoPropostas = ultimaProposta.getProdutosProposta();
		List<ProdutosProposta> listaProdutoPropostasTela = proposta.getProdutosProposta();
		
		
		for(int i =0;i < listaProdutoPropostasTela.size();i++) {
			ProdutosProposta produtoProposta = listaProdutoPropostas.get(i);
		    ProdutosProposta produtoPropostaTela = listaProdutoPropostasTela.get(i);
		    
		    List<DetalhesProdutoProposta> detalhesProposta = produtoProposta.getDetalhes();
		    List<DetalhesProdutoProposta> detalhesPropostaTela = produtoPropostaTela.getDetalhes();
		    
		    for(int j =0;j < detalhesPropostaTela.size();j++) {
		    	//detalhesPropostaTela.get(j).setProdutosProposta(detalhesProposta.get(i).getProdutosProposta());
		    	detalhesProposta.get(j).setDesconto(detalhesPropostaTela.get(j).getDesconto());
		    	detalhesProposta.get(j).setQuantidade(detalhesPropostaTela.get(j).getQuantidade());
		    }
		   
		   
		    
		}
		
		
		propostaRepository.save(ultimaProposta);
		
		return "redirect:/proposta/cadastro";
	}
	
	@GetMapping("/report/{format}")
	public String geraRelatorio(@PathVariable String format) throws FileNotFoundException, JRException {
		
		
		reportService.exportReport(format);
		return "redirect:/proposta/cadastro";
	}

}
