package UI.Utils;

import java.util.HashMap;

public class EditDataReader extends YmlReader{
    private HashMap<String, ?> data;

    protected HashMap<String, ?> getData() {
        return data;
    }

    public EditDataReader() {
        this.data = getDataFromFile();
    }

    private HashMap<String, ?> getDataFromFile() {
        return (HashMap<String, ?>) super.getData("EditTestData.yml");
    }
}
