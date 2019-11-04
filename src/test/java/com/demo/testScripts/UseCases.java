package com.demo.testScripts;

import com.demo.pages.*;

/**
 * This class contains the test use cases
 */
public class UseCases {

    /**
     * creating instance of the classes
     */

    HomePage homePage = new HomePage();
    SignUpModal signUpModal = new SignUpModal();


    public void signUp(){
        homePage.goToHomePage().selectSignUp();
        signUpModal.selectLetsStart()
                .enterPersonalDetails("23051990","ank@co.nz","0276451103",
                "36 Taranaki Street, Te Aro, Wellington");
               // .enterDlDetails()
               // .enterInterest()
               // .createUserName()

    }



}
