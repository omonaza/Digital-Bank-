package step_definitions;

import cucumber.api.java.After;
import utility.Driver;

public class Hooks {

    @After
    public void tearDown(){
        Driver.closeDriver();
    }
}
