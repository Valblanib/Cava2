package test.java;

import main.java.pageEvents.HomePage;
import main.java.pageEvents.OrderOnline;
import org.testng.annotations.Test;

public class CavaTest extends BaseTest{
    @Test
    public void OrderOnlinePage(){
        HomePage homePage =new HomePage();
        homePage.clickOnOnlineOrder();
        OrderOnline orderOnline = new OrderOnline();
        orderOnline.verifyOrderOnlineOpenOrNot();
        orderOnline.findLocation();
    }
}
