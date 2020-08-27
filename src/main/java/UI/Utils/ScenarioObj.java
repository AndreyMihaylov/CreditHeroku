package UI.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings("unchecked")
public class ScenarioObj extends ScenarioReader {

    private String apr;
    private String limit;
    private String interest;
    private String total;
    List<HashMap<String,String>> drawList;
    List<HashMap<String,String>> paymentList;


    public ScenarioObj(ScenarioEnum scenarioEnum) {
        super(scenarioEnum);
        this.apr = getAprFromFile();
        this.limit = getLimitFromFile();
        this.interest = getInterestFromFile();
        this.total = getTotalFromFile();
        this.drawList = getDraws();
        this.paymentList = getPayments();
    }

    public String getApr() {
        return apr;
    }

    public String getLimit() {
        return limit;
    }

    public Float getInterest() {
        return Float.valueOf(interest);
    }

    public Float getTotal() {
        return Float.valueOf(total);
    }

    public List<HashMap<String,String>> getDraws() {

        List<HashMap<String,String>> drawList = new ArrayList<>();
        for (String key:getScenario().keySet()){
            if (key.contains("draw")){
                drawList.add((HashMap<String, String>) getScenario().get(key));
            }
        }
        return drawList;
    }

    public List<HashMap<String,String>> getPayments() {

        List<HashMap<String,String>> paymentList = new ArrayList<>();
        for (String key:getScenario().keySet()){
            if (key.contains("payment")){
                paymentList.add((HashMap<String, String>) getScenario().get(key));
            }
        }
        return paymentList;
    }



    private String getAprFromFile() {
        return (String) getScenario().get("apr");
    }

    private String getLimitFromFile() {
        return (String) getScenario().get("limit");
    }

    private String getInterestFromFile() {
        HashMap<String, String> result = (HashMap<String, String>) getScenario().get("result");
        return result.get("interest");
    }

    private String getTotalFromFile() {
        HashMap<String, String> result = (HashMap<String, String>) getScenario().get("result");
        return result.get("total");
    }

    // take from scenarios.yml. Add to enum if created new scenario in the file
    public enum ScenarioEnum {
        SCENARIO1("scenario1"), SCENARIO2("scenario2");

        private final String value;

        ScenarioEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
