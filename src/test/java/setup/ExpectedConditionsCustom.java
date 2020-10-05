package setup;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Classe com metodos customizados para expectativas nao trabalhadas pelo
 * selenium.
 * 
 * @author Jefferson.Farias
 */
public class ExpectedConditionsCustom {

	/**
	 * Aguarde ate que um elemento nao esteja mais anexado ao DOM.
	 *
	 * @param element O elemento para aguardar.
	 * @return false se o elemento estiver presente no DOM e true se nao tiver.
	 */
	public static ExpectedCondition<Boolean> stalenessOf(final WebElement element) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver ignored) {
				try {
					// Chamando metodo forcado para  verificar se o elemento esta staleness ou nosuch
					element.isEnabled();
					return false;
				} catch (StaleElementReferenceException | NoSuchElementException expected) {
					return true;
				}
			}

			@Override
			public String toString() {
				return String.format("elemento (%s) se tornar stale ou nosuch", element);
			}
		};
	}

}
