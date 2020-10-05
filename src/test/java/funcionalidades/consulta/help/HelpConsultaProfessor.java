package funcionalidades.consulta.help;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import funcionalidades.consulta.model.Curso;
import funcionalidades.consulta.pgo.PgoConsultaProfessor;
import setup.ExpectedConditionsCustom;
import setup.Suporte;

public class HelpConsultaProfessor extends Suporte {

	PgoConsultaProfessor pg;
	String qtdCursos;
	static Boolean notificacao = true;
	String urlPageProf;

	public HelpConsultaProfessor() {
		pg = new PgoConsultaProfessor();
	}

	public void aguardarNav() {
		aguardaElemento(ExpectedConditions.visibilityOf(pg.COMPONENTE_NAV));
	}

	public void pressionarBuscaPorProfessor() {
		notificacaoIsPresent();
		alertaIsPresent();
		click(pg.BOTAO_POR_PROFESSOR);
	}
	
	public void pressionarBuscaPorMateria() {
		notificacaoIsPresent();
		alertaIsPresent();
		click(pg.BOTAO_POR_MATERIA);
	}

	public void selecionarDetalhesPeloNome(String nome) {
		aguardaElemento(ExpectedConditions.visibilityOfAllElements(pg.LISTA_REGISTROS));
		WebElement el = pg.LISTA_REGISTROS.stream().filter(prof -> prof.findElement(By.cssSelector(obterSeletor(pg.LABEL_NOME_PROFESSOR))).getText()
						.equalsIgnoreCase(nome)).findFirst().get();
		obterQtdCursos(el);
		click(el.findElement(By.cssSelector(obterSeletor(pg.LINK_DETALHES_PROFESSOR))));
	}

	private void obterQtdCursos(WebElement el) {
		qtdCursos = el.findElement(By.cssSelector(obterSeletor(pg.LABEL_QTD_CURSOS))).getText();
	}

	public void validarQtdCursos() {
		aguardaElemento(ExpectedConditions.visibilityOf(pg.DETALHE_LABEL_QTD_CURSOS));
		verificacao(pg.DETALHE_LABEL_QTD_CURSOS, qtdCursos);
	}

	public void validarParcelasListaCursos() {
		aguardaElemento(ExpectedConditions.visibilityOfAllElements(pg.LISTA_CURSOS));
		int limiteVerificacao = 5;
		for (WebElement detalheCurso : pg.LISTA_CURSOS) {
			aguardaElemento(ExpectedConditions.elementToBeClickable(detalheCurso.findElement(By.cssSelector(obterSeletor(pg.LINK_DETALHES_CURSO)))));
			String nomeCurso = detalheCurso.findElement(By.cssSelector(obterSeletor(pg.LABEL_TITULO_CURSO))).getText();
			String detalheParcela = detalheCurso.findElement(By.cssSelector(obterSeletor(pg.LABEL_DETALHE_PARCELA))).getText();
			String[] valoresParcela = obterValorDeParcela(detalheParcela);
			openNewTab(detalheCurso.findElement(By.cssSelector(obterSeletor(pg.LINK_DETALHES_CURSO))));
			Curso curso = new Curso(nomeCurso, valoresParcela);
			validarDetalhesCurso(curso);
			if (limiteVerificacao < 1)
				return;
			limiteVerificacao--;
		}
	}

	private void validarDetalhesCurso(Curso curso) {
		switchTab(1);
		verificacao(pg.DETALHE_LABEL_NOME_CURSO, curso.nome);
		verificacao(pg.DETALHE_LABEL_VALOR_CURSO, curso.getValorFinal());
		closeTab(1);
	}

	private String[] obterValorDeParcela(String texto) {
		String detalheParcela = texto.replaceAll("[^0-9,.]+", ";").replaceFirst(";", "");
		return detalheParcela.split(";");
	}

	public void notificacaoIsPresent() {
		if (notificacao) {
			aguardarNav();
			aguardaElemento(ExpectedConditions.elementToBeClickable(pg.NOTIFICACAO_ALERTA));
			click(pg.BOTAO_NEGAR_NOTIFICACAO);
			aguardaElemento(ExpectedConditionsCustom.stalenessOf(pg.NOTIFICACAO_ALERTA));
			notificacao = false;
		}
	}

	public void alertaIsPresent() {
		try {
			aguardarNav();
			clickJs(pg.ALERTA_DIALOGO);
			acoesTeclado(Keys.TAB, Keys.TAB, Keys.TAB);
			acoesTeclado(Keys.ENTER);
		} catch (Exception e) {
		}
	}

}
