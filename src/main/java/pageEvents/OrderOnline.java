package main.java.pageEvents;

import main.java.PageObjects.HomePageElements;
import main.java.PageObjects.OrderOnlineElements;
import main.java.Utils.ElementFetch;
import org.testng.Assert;

public class OrderOnline {
    public void verifyOrderOnlineOpenOrNot(){
        ElementFetch elementFetch= new ElementFetch();
        elementFetch.getListOfWebElements("XPATH",OrderOnlineElements.orderOnlineTittle);
    }
    public void findLocation(){
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getWebElement("XPATH",OrderOnlineElements.zipCodeStoreLocator).sendKeys("75201");
        elementFetch.getWebElement("XPATH",OrderOnlineElements.searchButton).click();
    }
}
