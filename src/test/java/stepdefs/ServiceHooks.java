package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks {
	
    @Before
    public void initializeTest(){
    	//Vou verificar a necessidade de implementação
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        //Vou verificar a necessidade de implementação 
    }
}
