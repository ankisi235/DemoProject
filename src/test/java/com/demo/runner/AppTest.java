package com.demo.runner;

import static com.demo.utilities.ExtentReportNG.*;
import static com.demo.utilities.PageUtils.*;

import com.demo.testScripts.UseCases;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * this class is for test execution
 */
public class AppTest {

    //region - variables to validate test results

    //endregion

    UseCases useCases = new UseCases();

    /**
     * initializes driver and report
     */
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("Chrome") String browser, @Optional String agent) {
        initDriver(browser, agent);
        initReport("Report");
    }

    /**
     * Sends a message to customer service and verifies the success response
     */
    @Test(priority = 1)
    public void customerSignUp() {
        try {
            startTest("Sign Up", "To verify if user is able to sign up successfully");
            useCases.signUp();
            //Assert.assertEquals(useCases.signUp();,successMessage);
            logTest(LogStatus.PASS, "Message sent Successfully", "success_msg");
            endTest();
        } catch (AssertionError e) {
            logTest(LogStatus.FAIL, "Error in sending Message", "sending_error");
            endTest();
        }
    }


    /**
     * writes report, close driver, kills any instance of driver, opens report in browser
     */
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        writeReport();
        closeDriver();
        killDriver();
        openReport();
    }
}
