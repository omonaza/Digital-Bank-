package step_definitions;

import Pages.DigitalBankLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

    private static final Logger LOG = LogManager.getLogger(Test.class.getName());
//  private static final Logger LOG = LogManager.getLogger(Test.class);//the same as first
//  private static final Logger LOG = LogManager.getLogger();//will not add class
//    name to the logger


    public static void main(String[] args) {
        LOG.info("It is our first log");
        LOG.error("I got error from such file");
        LOG.debug("I enter login website.");
        LOG.trace("This is main class");
        LOG.fatal("This is fatal message.");
        LOG.warn("Be careful.");

        String title = "Home Page";
        LOG.info("This is my title "+title);

        LOG.info("This is my title {}", title);

        int num = 5;
        LOG.error("This is my {} title {}",num, title);

        new DigitalBankLoginPage().getName();
    }
}
