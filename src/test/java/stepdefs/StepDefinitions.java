package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import funcionalidades.consulta.help.HelpConsultaProfessor;
import setup.Capabilities;

public class StepDefinitions {
	
	HelpConsultaProfessor hlp = new HelpConsultaProfessor();
	
    @Given("^Desejo acessar a url \"([^\"]*)\"$")
    public void acessarPagina(String url) throws Throwable {
    	Capabilities.getDriver().get(url);
    }
    
    @Then("^Devo ver as opcoes no navbar$")
    public void aguardarOpcoes() throws Throwable {
    	hlp.aguardarNav();
    }
    
    @When("^Eu aciono a opcao de consulta por \"([^\"]*)\"$")
    public void acessarPesquisaDeCursosPor(String tipo) throws Throwable {
    	if(tipo.equalsIgnoreCase("professor")) {
    		hlp.pressionarBuscaPorProfessor();
    	}else {
    		hlp.pressionarBuscaPorMateria();
    	}
    }
    
    @And("^procuro pelo nome \"([^\"]*)\"$")
    public void procurarPorNome(String nome) throws Throwable {
    	hlp.selecionarDetalhesPeloNome(nome);
    }
    
    @Then("^valido \"([^\"]*)\"$")
    public void validarQuantidadeDeCursos(String tipoValidacao) throws Throwable {
    	if(tipoValidacao.contains("parcelas")) {
    		hlp.validarParcelasListaCursos();
    	}else if(tipoValidacao.contains("quantidade")) {
    		hlp.validarQtdCursos();
    	}
    }
    

}