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
    BetSlipPage betSlipPage = new BetSlipPage();
    LivePool livePool = new LivePool();


    public void signUp() {
        homePage.goToHomePage().selectSignUp();
        signUpModal.selectLetsStart()
                .enterPersonalDetails("23051990", "ank@co.nz", "0276451103",
                        "36 Taranaki Street, Te Aro, Wellington")
                .enterDlDetails("AA012077", "111")
                .enterInterest("Retired", "Seasonally", "Over $1000", "Cash", "Both")
                .createUserName("India11581", "Singh");

    }

    public boolean signInAndOut() {
        return homePage.goToHomePage().selectSignIn()
                .enterCredentialsAndLogin("sweet", "India234")
                .signOut();

    }

    public boolean placeLivePoolBet() {
        homePage.goToHomePage().selectSignIn().enterCredentialsAndLogin("sweet", "India234");
        livePool.goToLivePool().selectToBet();
        if (betSlipPage.isBetSlipPresent() == false) {
            betSlipPage.goToBetSlip();
        }
        return betSlipPage.putStake().acceptBet().acceptBet().insufficientFund();
    }

    public boolean signInAndBrowse() {
        homePage.goToHomePage().selectSignIn().enterCredentialsAndLogin("sweet", "India234").selectSlipMobile();
        return betSlipPage.isBetSlipPresent();
    }

    public boolean getError() {
        return signUpModal.checkErrorSignUp();
    }
}
