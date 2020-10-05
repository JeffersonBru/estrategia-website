package funcionalidades.consulta.pgo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import funcionalidades.generico.PgoGenerico;

public class PgoConsultaProfessor extends PgoGenerico{
	
	/**
	 * Pagina lista de professores
	 */
	
	@FindBy(css = ".container section:nth-child(2) .page-result-list section")
	public List<WebElement> LISTA_REGISTROS;
	
	@FindBy(css = ".card-prod-title")
	public WebElement LABEL_NOME_PROFESSOR;
	
	@FindBy(css = ".card-prod-available")
	public WebElement LABEL_QTD_CURSOS;
	
	@FindBy(css = ".card-prod-details")
	public WebElement LINK_DETALHES_PROFESSOR;
	
	
	/**
	 * Pagina detalhe professor
	 */
	
	@FindBy(css = ".section-header .section-header-title")
	public WebElement LABEL_DETALHE_NOME_PROFESSOR;
	
	@FindBy(css = ".cur-details-info-features")
	public WebElement DETALHE_LABEL_QTD_CURSOS;
	
	@FindBy(css = ".search .js-filter")
	public WebElement CAMPO_PESQUISA_CURSO;
	
	@FindBy(css = "[data-type='todos'] section:not(._none)")
	public List<WebElement> LISTA_CURSOS;
	
	@FindBy(css = ".card-prod-title a")
	public WebElement LABEL_TITULO_CURSO;
	
	@FindBy(css = ".card-prod-details")
	public WebElement LINK_DETALHES_CURSO;

	@FindBy(css = ".card-prod-price")
	public WebElement LABEL_DETALHE_PARCELA;
	
	
	/**
	 * Pagina detalhe curso
	 */
	
	@FindBy(css = ".cur-details-info-title")
	public WebElement DETALHE_LABEL_NOME_CURSO;
	
	@FindBy(css = ".cur-details-shopping-price .value")
	public WebElement DETALHE_LABEL_VALOR_CURSO;
	

}
