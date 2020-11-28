package step_definitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestTwo {

    private static final Logger LOG = LogManager.getLogger(TestTwo.class.getName());

    public static void main(String[] args) {
        LOG.info("It is our first log");
        LOG.error("I got error from such file");
        LOG.debug("I enter login website.");
        LOG.trace("This is main class");
        LOG.fatal("This is fatal message.");
        LOG.warn("Be careful.");
    }
}
