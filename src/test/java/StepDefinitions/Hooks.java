package StepDefinitions;

import Utils.BrowserUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    //We have before and after annotations
    //Those are coming from Cucumber
    //They will run before and after each scenario
    //We have one Scenario interface to get the details from every Scenario
    @Before
    public void setUp(Scenario scenario){
        System.out.println("This will run before each scenario");
        System.out.println(scenario.getName());
    }
    @After
    public void tearDown(Scenario scenario){
        System.out.println("This will run after each Scenario");
        //after annotation will execute after each scenario even though they are failed, undefined
        if(scenario.isFailed()){
            scenario.log(scenario.getName()+" is failed");
            BrowserUtils.takeScreenShot();
        }
    }
    @Before("@tt")
    public void conditionalAnnotation(){
        //I want to run this annotation only before @conditional tag
        System.out.println("Conditional annotation");
    }
}
