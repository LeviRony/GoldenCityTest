package com.pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class HomepageObjects {

    private Page page;

    public HomepageObjects(Page page) {
        this.page = page;
    }

    private Locator homepageHeaderImage() {
        return page.locator("section")
                .filter(new Locator.FilterOptions().setHasText("Invest and Trade in Real"))
                .locator("div")
                .nth(1);
    }

    @Step
    public boolean isVisibleHeaderImage() {
        return homepageHeaderImage().isVisible();
    }

    private Locator featuredInvestmentLink() {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Invest Now"));
    }

    @Step
    public void clickFeaturedInvestmentLink(int squareIndex) {
        featuredInvestmentLink().nth(squareIndex).click();
    }
}
