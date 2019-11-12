package com.demo.runner;

import static com.demo.utilities.ExtentReportNG.*;
import static com.demo.utilities.ExtentReportNG.initReport;
import static com.demo.utilities.PageUtils.*;
import static com.demo.utilities.PageUtils.initMobileDriver;

import com.demo.testScripts.UseCases;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * this class is for test execution
 */
public class AppTest {

    //region - variables to validate test results
    String nexus5 = "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Anokhi 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";
    String android9 = "Mozilla/5.0 (Linux; Android 9; SM-G950F Build/PPR1.180610.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.157 Mobile Safari/537.36";

    //endregion

    UseCases useCases = new UseCases();

    @BeforeSuite(alwaysRun = true)
    public void initialiseReport() {
        initReport("Report");
    }

    /**
     * initializes driver and report
     */
    @BeforeClass(groups = {"userJoining", "betting"})
    public void setUp() {
        initDriver();
    }

    /**
     * A person registering
     */
    @Test(priority = 1, enabled = true, groups = "userJoining")
    public void customerSignUp() {
        try {
            startTest("Sign Up", "To verify if user is able to sign up successfully");
            useCases.signUp();
            Assert.assertFalse(useCases.getError());
            logTest(LogStatus.PASS, "Signed up Successfully", "success_msg");
            endTest();
        } catch (AssertionError e) {
            logTest(LogStatus.FAIL, "An error has occurred. Please try again later.", "signup_error");
            endTest();
        }
    }

    /**
     * A person logging-in and off
     */
    @Test(priority = 2, enabled = true, groups = "userJoining")
    public void customerSignInAndOff() {
        try {
            startTest("Sign In and Out", "To verify if user is able to sign in and out successfully");
            Assert.assertTrue(useCases.signInAndOut());
            logTest(LogStatus.PASS, "Signed in and out Successfully", "signedOut");
            endTest();
        } catch (AssertionError e) {
            logTest(LogStatus.FAIL, "Error Signing in or out", "signIn-fail");
            endTest();
        }
    }

    /**
     * A person single betting
     */
    @Test(priority = 3, enabled = true, groups = "betting")
    public void singleBetting() {
        try {
            startTest("Single Betting", "To verify if user is able to place a bet successfully");
            Assert.assertFalse(useCases.placeLivePoolBet());
            logTest(LogStatus.PASS, "able to place bet successfully", "singleBet");
            endTest();
        } catch (AssertionError e) {
            logTest(LogStatus.FAIL, "You have insufficient funds to place your bet. Please top up or amend stake values.", "singleBet_fail");
            endTest();
        }
        closeDriver();
    }

    /**
     * nexus login
     */
    @Test(priority = 4, enabled = true, groups = "mobile")
    public void testNexus() {
        try {
            startTest("Login Nexus5", "To verify if user is able to login in Nexus5");
            initMobileDriver(nexus5, 420, 600);
            useCases.signInAndBrowse();
            logTest(LogStatus.PASS, "able to login and open Betslip", "nexus_pass");
        } catch (AssertionError e) {
            logTest(LogStatus.FAIL, "", "nexus_fail");
            endTest();
        }
        closeDriver();
    }

    /**
     * android 9 login
     */
    @Test(priority = 5, enabled = true, groups = "mobile")
    public void testAndroid() {
        try {
            startTest("Login Android9", "To verify if user is able to login in Android9");
            initMobileDriver(android9, 840, 420);
            useCases.signInAndBrowse();
            logTest(LogStatus.PASS, "able to login and open BetSlip", "android_pass");
        } catch (AssertionError e) {
            logTest(LogStatus.FAIL, "", "and9_fail");
            endTest();
        }

    }

    /**
     * writes report, close driver, kills any instance of driver, opens report in browser
     */
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            writeReport();
            quitDriver();
            killDriver();
            openReport();
        } catch (Exception e) {
            //
        }
    }
}
