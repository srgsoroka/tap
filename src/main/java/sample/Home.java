package sample;

import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.html.BasePage;

public abstract class Home extends BasePage {
    abstract public Result getResultPage();

    public abstract String getStockId();

    public abstract void setStockId(String stockId);

    public IPage onOk(IRequestCycle cycle) {
        //System.out.println("Listener called. Stock id is: " + stockId);
        //cycle.activate("Result");
        int stockValue = getStockId().hashCode() % 10;
        /*Result resultPage = (Result) cycle.getPage("Result");
        resultPage.setStockValue(stockValue);*/
        Result resultPage=getResultPage();
        resultPage.setStockValue(stockValue);
        return resultPage;
    }

}
