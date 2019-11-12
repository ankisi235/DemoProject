package com.demo.pages;

import org.openqa.selenium.By;

import static com.demo.utilities.PageUtils.*;
import static com.demo.utilities.PageUtils.find;

public class SignUpModal {

    By btnLetsStart = By.cssSelector("button.registration-intro__sign-up-button");
    By modal = By.cssSelector("div.spa-modal-header");
    By txtPromoCode = By.id("promoCode");
    By txtFirstName = By.id("firstName");
    By txtMiddleName = By.id("middleName");
    By txtLastName = By.id("lastName");
    By txtDob = By.id("dob");

    By txtEmailAddress = By.id("emailAddresses[0].address");
    By txtPhoneNumber = By.id("phoneNumbers[0].number");
    By txtResidentialAddress = By.name("addresses[0].addressLookupKey");
    By ddlAddSuggestion = By.className("suggestion");
    By btnContinue = By.xpath("//button[text()='Continue']");

    By rbtnNzdl = By.id("NZ_DL");
    By txtIdFirstName = By.id("identityDocuments[0].documentFirstName");
    By txtIdMiddleName = By.id("identityDocuments[0].documentMiddleName");
    By txtIdLastName = By.id("identityDocuments[0].documentLastName");
    By txtIdLicenceNumber = By.id("identityDocuments[0].driversLicenceNumber");
    By txtVersionNumber = By.id("identityDocuments[0].driversLicenceVersion");

    By ddlIndustryRef = By.id("industryRef");
    By ddlFreq = By.id("frequency");
    By ddlStake = By.id("stake");
    By ddlPayMethod = By.id("payMethodType");
    By rbtnInterest = By.cssSelector("div.radio-option");

    By txtUserNme = By.id("username");
    By txtPassword = By.id("password");
    By txtPin = By.id("pin");
    By txtMaidanName = By.id("challengeQuestions[0].answer");
    By cbxTermsAndConditions = By.id("id_agreedTermsAndConditions");
    By btnRegisterNow = By.xpath("//button[text()='Register Now']");

    By lblErrorBanner = By.cssSelector("div.error-banner");

    public static String FirstName, MiddleName, LastName, UserName, Password, Pin;

    public String getFirstName(boolean notNull) {
        if (notNull) {
            FirstName = genRandomly(5, true, false);
            return FirstName;
        }
        return "";
    }

    public String getMiddleName(boolean notNull) {
        if (notNull) {
            MiddleName = genRandomly(5, true, false);
            return MiddleName;
        }
        return "";
    }

    public String getLastName(boolean notNull) {
        if (notNull) {
            LastName = genRandomly(5, true, false);
            return LastName;
        }
        return "";
    }

    public String getUserName(boolean notNull) {
        if (notNull) {
            UserName = genRandomly(5, true, true);
            return UserName;
        }
        return "";
    }

    /**
     * clicks on the lets get started button
     *
     * @return page objects
     */
    public SignUpModal selectLetsStart() {
        click(find(btnLetsStart));
        getFirstName(true);
        getMiddleName(true);
        getLastName(true);
        getUserName(true);
        return this;
    }

    public SignUpModal enterPersonalDetails(String dob, String email, String contact, String residentAdd) {
        input(find(txtFirstName), FirstName);
        input(find(txtMiddleName), MiddleName);
        input(find(txtLastName), LastName);
        input(find(txtDob), dob);
        click(find(btnContinue));
        input(find(txtEmailAddress), email);
        input(find(txtPhoneNumber), contact);
        input(find(txtResidentialAddress), residentAdd);
        find(ddlAddSuggestion);
        selectFirstItemFromList(ddlAddSuggestion);
        click(find(btnContinue));
        return this;
    }

    public SignUpModal enterDlDetails(String ln, String ver) {
        click(find(rbtnNzdl));
        input(find(txtIdFirstName), FirstName);
        input(find(txtIdMiddleName), MiddleName);
        input(find(txtIdLastName), LastName);
        input(find(txtIdLicenceNumber), ln);
        input(find(txtVersionNumber), ver);
        click(find(btnContinue));
        return this;
    }

    public SignUpModal enterInterest(String industry, String freq, String betSize, String payWay,
                                     String interest) {
        selectFromDropdown(find(ddlIndustryRef), industry);
        selectFromDropdown(find(ddlFreq), freq);
        selectFromDropdown(find(ddlStake), betSize);
        selectFromDropdown(find(ddlPayMethod), payWay);
        selectFromRadioGroup(rbtnInterest, interest, By.name("interests"));
        click(find(btnContinue));
        return this;
    }

    public SignUpModal createUserName(String pss, String mmn) {
        input(find(txtUserNme), UserName);
        input(find(txtPassword), pss);
        input(find(txtPin), genRandomly(4, false, true));
        input(find(txtMaidanName), mmn);
        selectCheckBox(cbxTermsAndConditions);
        click(find(btnRegisterNow));
        return this;
    }

    public boolean checkErrorSignUp() {
        if (find(lblErrorBanner) != null) {
            return true;
        }
        return false;
    }
//endregion
}
