package com.demo.pages;

import static com.demo.utilities.PageUtils.*;

import org.openqa.selenium.By;

/**
 * Page Object encapsulates the Home page
 */
public class HomePage {

    public final String homeUrl = "https://wwww.tab.co.nz";

    //region - locators

    By btnSignUp = By.cssSelector("a.sign-up");


    By btnSignIn = By.cssSelector("a.sign-in");
    By txtUserName = By.cssSelector("input#LabeledTextField_username");
    By txtPassword = By.cssSelector("input#LabeledTextField_login-password-id");
    By btnLogin = By.cssSelector("button.login-button");
    // endregion

    //region - sign up
    /**
     * launches home page and maximizes the browser
     *
     * @return page object
     */
    public HomePage goToHomePage() {
        launchURL(homeUrl, "HomePage");
        return this;
    }

    /**
     * clicks on Join Now button
     */
    public void selectSignUp() {
        click(find(btnSignUp));
    }



    //region - sign in

    /**
     * clicks on sign in link
     */
    public void selectSignIn() {
        click(find(btnSignIn));
    }

    //endregion

   }
