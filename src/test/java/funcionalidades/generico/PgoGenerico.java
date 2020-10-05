package funcionalidades.generico;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import setup.Capabilities;

public class PgoGenerico {
	
	public PgoGenerico() {
		PageFactory.initElements(Capabilities.getDriver(), this);
	}
	
	@FindBy(css = ".nav-header-links")
	public WebElement COMPONENTE_NAV;
	
	@FindBy(xpath = "//nav//a[text() = 'Por concurso']")
	public WebElement BOTAO_POR_CONCURSO;
	
	@FindBy(xpath = "//nav//a[text() = 'Por professor']")
	public WebElement BOTAO_POR_PROFESSOR;
	
	@FindBy(xpath = "//nav//a[text() = 'Por matéria']")
	public WebElement BOTAO_POR_MATERIA;
	
	@FindBy(xpath = "//nav//a[text() = 'Por região']")
	public WebElement BOTAO_POR_REGIAO;
	
	@FindBy(xpath = "//nav//a[text() = 'Ver todos']")
	public WebElement BOTAO_VER_TODOS;
	
	@FindBy(xpath = "//nav//a[text() = 'Sistema de Questões']")
	public WebElement BOTAO_QUESTOES;
	
	/**
	 * Notificacao
	 */
	
	@FindBy(id = "onesignal-slidedown-container")
	public WebElement NOTIFICACAO_ALERTA;
	
	@FindBy(id = "onesignal-slidedown-allow-button")
	public WebElement BOTAO_ACEITAR_NOTIFICACAO;
	
	@FindBy(id = "onesignal-slidedown-cancel-button")
	public WebElement BOTAO_NEGAR_NOTIFICACAO;
	
	/**
	 * Dialogo alert
	 */
	
	@FindBy(css = "#getsitecontrol")
	public WebElement ALERTA_DIALOGO;
	
	@FindBy(css = ".getsitecontrol-close")
	public WebElement BOTAO_NEGAR_ALERTA;
	

}
