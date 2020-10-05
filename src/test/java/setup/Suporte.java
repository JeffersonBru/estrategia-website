package setup;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Suporte {

	/**
	 * Aguardo o Elemento ser carregado conforme uma expectativa. Tempo definido por
	 * padrao
	 * 
	 * @param expect - ExpectedCondition<'WebElement'>
	 */
	protected WebElement aguardaElemento(ExpectedCondition<WebElement> expect) {
		return Capabilities.getWait().until(ExpectedConditions.refreshed(expect));
	}
	
	
	/**
	 * Aguardo o Elemento HTML ser carregado na tela podendo ser como entrada um
	 * ExpectedCondition<'Boolean'>. Tempo definido por padrao no CrossBrowser.
	 * 
	 * @param expect - tem que ser diferente de ExpectedCondition<'WebElement'>
	 */
	protected void aguardaElemento(Function<WebDriver, ?> expect) {
		Capabilities.getWait().until(ExpectedConditions.refreshed((ExpectedCondition<?>) expect));
	}
	
	
	/**
	 * Click no elemento
	 * 
	 * @param elemento
	 */
	protected void click(WebElement elemento) {
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).click();
	}
	
	/**
	 * Metodo para clicar atraves de javascript utilizando o motor do navegador
	 * @param elemento
	 */
	protected void clickJs(WebElement elemento) {
		executeJavaScript(elemento, "click()");
	}
	
	/**
	 * Executa javascript
	 * 
	 * @param elemento
	 * @param script
	 */
	protected void executeJavaScript(WebElement elemento, Object script) {
		JavascriptExecutor js = (JavascriptExecutor) Capabilities.getDriver();
		js.executeScript("arguments[0]." + script + ";", elemento);
	}
	
	/**
	 * Click no elemento
	 * 
	 * @param elemento
	 */
	protected void limparCampo(WebElement elemento) {
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).clear();
	}

	/**
	 * Preenche campo
	 * 
	 * @param elemento
	 * @param valor
	 */
	protected void preencheCampo(WebElement elemento, Object valor) {
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).sendKeys((String) valor);
	}
	
	
	/**
	 * Verificacao geral
	 * 
	 */
	protected void verificacao(WebElement elemento, String check) {
		aguardaElemento(ExpectedConditions.visibilityOf(elemento));
		System.out.println("\nVALIDACAO -> ".concat(elemento.getText()).concat(" Igual a ").concat(check));
		assertTrue(elemento.getText().equalsIgnoreCase(check));
	}
	
	protected void verificacao(WebElement elemento, String atributo, String check) {
		aguardaElemento(ExpectedConditions.visibilityOf(elemento));
		System.out.println("\nVALIDACAO -> ".concat(elemento.getAttribute(atributo)).concat(" Igual a ").concat(check));
		assertEquals(elemento.getAttribute(atributo), check);
	}
	
	/**
	 * Randomizar qualquer array de string
	 * @param array
	 * @return
	 */
	protected String random(String array[]) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(array.length);
		return array[randomIndex];
	}
	
	/**
	 * Obter data atual de acordo com o formato
	 * @param format
	 * @return
	 */
	protected String obterData(String format) {
		DateFormat formato = new SimpleDateFormat(format);
		Date data = new Date();
		return formato.format(data).toString();
	}
	
	/**
	 * Obter um objeto do tipo select
	 * @param elemento
	 * @return
	 */
	protected Select selecionarOpcao(WebElement elemento) {
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento));
		return new Select(elemento);
	}
	
	/**
	 * Metodo para simular teclas do teclado fisico
	 * @param keys
	 */
	protected void acoesTeclado(Keys... keys) {
		for (Keys key : keys) {
			new Actions(Capabilities.getDriver()).sendKeys(key).build().perform();
		}
	}
	
	/**
	 * Retirar do locator caracters indesejados para apresentacao
	 * 
	 * @param elemento - locator
	 */
	protected String obterSeletor(WebElement elemento) {
		return elemento.toString().replaceAll("\\S+: \\S+ on \\S+ (\\S+) -> \\S+ \\S+ ", "").replace("]", "");
	}
	
	/**
	 * Teabalhar com elementos no DOM com shadow-root
	 * @param elemento - locator
	 */
	protected void getShadowElement(WebElement el) {
		Capabilities.shadow.scrollTo(el);
	}
	
	/**
	 * Abrir uma nova tab atraves de um click no link
	 * @param elemento - locator
	 */
	protected void openNewTab(WebElement el) {
		aguardaElemento(ExpectedConditions.visibilityOf(el));
		aguardaElemento(ExpectedConditions.elementToBeClickable(el));
		Actions actions = new Actions(Capabilities.getDriver()); 
		actions.moveToElement(el);
		actions.keyDown(Keys.CONTROL).click(el).keyUp(Keys.CONTROL).build().perform();
	}
	
	/**
	 * Metodo para alterar para uma tab do navegador conforme o index.
	 * @param index int - numero da tab
	 */
	protected void switchTab(int index) {
		Capabilities.getDriver().switchTo().window(new ArrayList<String>(Capabilities.getDriver().getWindowHandles()).get(index));
	}
	
	/**
	 * Metodo para fechar a tab atual e retornar para tab anterior. Exemplo: index
	 * -1;
	 * @param index int - numero da tab
	 */
	public void closeTab(int index) {
		switchTab(index);
		Capabilities.getDriver().close();
		switchTab(index - 1);
	}
	
}
