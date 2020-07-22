package kobe.mamba.web.drools.model.fact;

/**
 * @author mamba
 * @ 2020/7/22
 */
public class AddressCheckResult {
    private boolean postCodeResult = false; // true:通过校验；false：未通过校验

    public boolean isPostCodeResult() {
        return postCodeResult;
    }

    public void setPostCodeResult(boolean postCodeResult) {
        this.postCodeResult = postCodeResult;
    }
}
