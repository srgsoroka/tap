package sample;

import java.util.Date;
import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.InitialValue;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.form.StringPropertySelectionModel;
import org.apache.tapestry.html.BasePage;

public abstract class Home extends BasePage {

    @InjectPage("Result")
    abstract public Result getResultPage();

    @InitialValue("literal:MSFT")
    public abstract String getStockId();

    public abstract void setStockId(String stockId);

    public abstract Date getQuoteDate();

    public IPropertySelectionModel getAvailStockIds() {
        return new StringPropertySelectionModel(new String[]{"IDM", "MSFT", "RHAT"});
    }

    public IPage onOk(IRequestCycle cycle) {
        //System.out.println("Listener called. Stock id is: " + stockId);
        //cycle.activate("Result");
        int stockValue = (getStockId() + getQuoteDate().toString()).hashCode() % 10;

        /*Result resultPage = (Result) cycle.getPage("Result");
        resultPage.setStockValue(stockValue);*/
        Result resultPage = getResultPage();
        resultPage.setStockValue(stockValue);
        return resultPage;
    }

}
