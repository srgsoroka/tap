package sample;

import java.util.HashMap;
import java.util.Map;
import org.apache.hivemind.util.PropertyUtils;
import org.apache.tapestry.IPage;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.form.IFormComponent;
import org.apache.tapestry.html.BasePage;
import org.apache.tapestry.valid.ValidationConstraint;
import org.apache.tapestry.valid.ValidationDelegate;

public abstract class Postage extends BasePage {
    private Map<String, Integer> patronCodeToDiscount;

    @InjectPage("ResultPostage")
    public abstract IPage getResult();

    public abstract double getWeight();

    public abstract String getPatronCode();

    public Postage() {
        patronCodeToDiscount = new HashMap();
        patronCodeToDiscount.put("p1", new Integer(90));
        patronCodeToDiscount.put("p2", new Integer(95));
    }

    public IPage onSubmit() {
        ValidationDelegate delegate = (ValidationDelegate) getBeans().getBean("delegate");
        double weight = getWeight();
        if (weight<0){
            delegate.setFormComponent((IFormComponent)getComponent("weight"));
            delegate.recordFieldInputValue(Double.toString(weight));
            delegate.record("Weight must be >=0", ValidationConstraint.TOO_SMALL);
        }
        Integer discount = patronCodeToDiscount.get(getPatronCode());
        if (discount == null && getPatronCode()!=null){
            delegate.setFormComponent((IFormComponent)getComponent("patronCode"));
            delegate.recordFieldInputValue(getPatronCode());
            delegate.record("Patron not found", null);
        }
        if (delegate.getHasErrors()){
            return null;
        }
        int postagePerKg = 10;
        double postage = weight*postagePerKg;
        if (discount!=null){
            postage=postage*discount.intValue()/100;
        }
        IPage resultPage=getResult();
        PropertyUtils.write(resultPage,"postage",postage);
        return resultPage;
    }

}
