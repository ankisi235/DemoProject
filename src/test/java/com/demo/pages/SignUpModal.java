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

    By txtEmailAddress = By.id("#emailAddresses[0].address");
    By txtPhoneNumber = By.id("phoneNumbers[0].number");
    By txtResidentialAddress = By.name("addresses[0].addressLookupKey");
    By ddlAddSuggestion = By.xpath("//*[class='suggestion']");
    By btnContinue = By.xpath("//button[text()='Continue']");

    By rbtnNzdl = By.id("NZ_DL");
    By txtIdFirstName = By.id("identityDocuments[0].documentFirstName");
    By txtIdMiddleName = By.id("identityDocuments[0].documentMiddleName");
    By txtIdLastName = By.id("identityDocuments[0].documentLastName");
    By txtIdLicenceNumber = By.id("identityDocuments[0].driversLicenceNumber");
    By txtVersionNumber = By.id("identityDocuments[0].driversLicenceVersion");

    By ddlIndustryRef = By.id("industryRef");
    By ddlFreq = By.id("frequency");
    By ddlPayMethod = By.id("payMethodType");

    String FirstName, MiddleName, LastName;

    public String getFirstName(boolean notNull){
        if(notNull){
            FirstName = genRandomly(5,true,false);
            return FirstName;
        }
        return "";
    }
    public String getMiddleName(boolean notNull){
        if(notNull){
            MiddleName = genRandomly(5,true,false);
            return MiddleName;
        }
        return "";
    }

    public String getLastName(boolean notNull){
        if(notNull){
            MiddleName = genRandomly(5,true,false);
            return MiddleName;
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
        return this;
    }

    public SignUpModal enterPersonalDetails(String dob, String email, String contact, String residentAdd) {
        input(find(txtFirstName), FirstName);
        input(find(txtMiddleName), MiddleName);
        input(find(txtLastName), LastName);
        input(find(txtDob),dob);
        click(find(btnContinue));
        input(find(txtEmailAddress),email);
        input(find(txtPhoneNumber),contact);
        input(find(txtResidentialAddress),residentAdd);
        click(find(ddlAddSuggestion));
        click(find(btnContinue));
        return this;
    }

    public SignUpModal enterDlDetails(String ln, String ver){
        click(find(rbtnNzdl));
        input(find(txtIdFirstName),FirstName);
        input(find(txtIdMiddleName),MiddleName);
        input(find(txtLastName), LastName);
        input(find(txtIdLicenceNumber), ln);
        input(find(txtVersionNumber),ver);
        click(find(btnContinue));
        return this;
    }

    public SignUpModal enterInterest(String industry, String freq, String betSize, String payWay,
                                  String interest){


        return this;
    }

    public SignUpModal createUserName(String un, String pss, int pin, String mmn){

        return this;
    }
//endregion

}
