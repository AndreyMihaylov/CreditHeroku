package UI.Utils;

import java.util.List;

public class EditDataObjNeg extends EditDataReader {

    private List<String> apr;
    private List<String> limit;


    public EditDataObjNeg() {
        this.apr = getAprFromFile();
        this.limit = getLimitFromFile();

    }

    public List<String> getApr() {
        return apr;
    }

    public List<String> getLimit() {
        return limit;
    }

    private List<String> getAprFromFile() {
        return (List<String>) getData().get("apr");
    }

    private List<String> getLimitFromFile() {
        return (List<String>) getData().get("limit");
    }

}
