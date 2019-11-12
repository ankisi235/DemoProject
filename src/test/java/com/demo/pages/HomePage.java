package com.demo.pages;

import static com.demo.utilities.PageUtils.*;

import org.openqa.selenium.By;

/**
 * Page Object encapsulates the Home page
 */
public class HomePage {

    public final String homeUrl = "https://www.tab.co.nz";

    //region - locators

    By btnSignUp = By.cssSelector("a.sign-up");


    By btnSignIn = By.cssSelector("a.sign-in");
    By txtUserName = By.cssSelector("input#LabeledTextField_username");
    By txtPassword = By.cssSelector("input#LabeledTextField_login-password-id");
    By btnLogin = By.cssSelector("button.login-button");

    By btnPlayerDetails = By.cssSelector("div.player-details");
    By btnSignOut = By.cssSelector("button.button-sign-out");
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


    //region - sign in - out

    /**
     * clicks on sign in link
     */
    public HomePage selectSignIn() {
        click(find(btnSignIn));
        return this;
    }

    public HomePage enterCredentialsAndLogin(String un, String pwd) {
        input(find(txtUserName), un);
        input(find(txtPassword), pwd);
        click(find(btnLogin));
        find(btnPlayerDetails);
        return this;
    }

    public boolean signOut() {
        click(find(btnPlayerDetails));
        click(find(btnSignOut));
        if(btnSignIn !=null){
            return true;
        }
        return false;
    }

    public void selectSlipMobile(){
        click(find(By.cssSelector("div.my-bets-compact-counts")));
    }
    //endregion

}
