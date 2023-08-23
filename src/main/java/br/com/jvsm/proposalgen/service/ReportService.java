package br.com.jvsm.proposalgen.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.com.jvsm.proposalgen.dto.PropostaRelatorioDTO;
import br.com.jvsm.proposalgen.repository.PropostaRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
	PropostaRepository propostaRepository;

	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		List<PropostaRelatorioDTO> propostas = propostaRepository.gerarRelatorio();
		
		File file = ResourceUtils.getFile("classpath:proposta.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new  JRBeanCollectionDataSource(propostas);
		Map<String, Object> map = new HashMap<>();
		map.put("createdBy", "jvsm");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,map, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "/home/joaovictor/"+"proposta.html");
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/joaovictor/"+"proposta.pdf");
		}
		
		return"Relatório gerado com sucesso!!";
	}
}
