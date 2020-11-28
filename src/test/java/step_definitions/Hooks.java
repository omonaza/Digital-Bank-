package step_definitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.Driver;

public class Hooks {

    private static Logger logger;

    @Before
    public void start() {
        logger = LogManager.getLogger();

//        logger.traceEntry();
        logger.traceEntry("Enter the Application");
//        logger.info("-------Start running scenario--------");
    }

    @After
    public void tearDown(){
        Driver.quitDriver();

//        logger.traceExit();
        logger.traceExit("Exit the Application");
//        logger.info("--------Stop running--------");
    }
    }
