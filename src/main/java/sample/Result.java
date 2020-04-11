package sample;

import org.apache.tapestry.event.PageEvent;
import org.apache.tapestry.html.BasePage;

public abstract class Result extends BasePage {
/*    int stockValue;

    public int getStockValue() {
        return stockValue;
    }

    public void setStockValue(int stockValue) {
        this.stockValue = stockValue;
    }*/
abstract public void setStockValue(int stockValue) ;

    /*public void pageDetached(PageEvent event) {
        stockValue = 0;
    }*/
}
