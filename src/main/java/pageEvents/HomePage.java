package main.java.pageEvents;

import main.java.PageObjects.HomePageElements;
import main.java.Utils.ElementFetch;

public class HomePage {

    public void clickOnOnlineOrder(){
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getWebElement("XPATH", HomePageElements.OrderOnlineButton).click();
    }
}
