package com.demo.pages;

import org.openqa.selenium.By;

import static com.demo.utilities.PageUtils.*;

public class BetSlipPage {
    By btnBetSlip = By.cssSelector(".my-bets-view--BETSLIP > .count");
    By txtStake = By.cssSelector("input.form-control.stake.ob-betslip-stake-input");
    By btnAccept = By.cssSelector(".btn-success");


    public BetSlipPage goToBetSlip() {
        click(find(btnBetSlip));
        return this;
    }

    public BetSlipPage putStake() {
        input(find(txtStake), "2");
        scrollUp();
        scrollUp();
        return this;
    }

    public BetSlipPage acceptBet() {
        click(find(btnAccept));
        return this;
    }


    public boolean isBetSlipPresent() {
        if (find(By.cssSelector("div.ob-betslip-bets-container")) != null) {
            return true;
        }
        return false;
    }
    public boolean insufficientFund(){
        if (find(By.cssSelector("div.notification-header"))!=null){
            return true;
        }
        return false;
    }


    public void closeBetSlip() {
        click(find(By.cssSelector("div.my-bets-toggle to-collapse")));
    }

    public BetSlipPage browseInBetSlip() {
        click(find(By.xpath("//button[text()='PENDING']")));
        click(find(By.xpath("//button[text()='CASHOUT']")));
        click(find(By.xpath("//button[text()='RESULTS']")));
        return this;
    }

}
