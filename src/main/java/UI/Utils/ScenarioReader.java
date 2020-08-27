package UI.Utils;

import java.util.HashMap;

public class ScenarioReader extends YmlReader {
    private HashMap<?, ?> scenarios;
    private HashMap<String, ?> scenario;

    protected HashMap<String, ?> getScenario() {
        return scenario;
    }

    protected ScenarioReader(ScenarioObj.ScenarioEnum scenarioEnum) {
        this.scenarios = getData();
        this.scenario = getDataByScenario(scenarioEnum);
    }

    private HashMap<?, ?> getData() {
        return super.getData("scenarios.yml");
    }

    private HashMap<String, HashMap<String, String>> getDataByScenario(ScenarioObj.ScenarioEnum scenarioEnum) {
        return (HashMap<String, HashMap<String, String>>) scenarios.get(scenarioEnum.getValue());
    }
}
