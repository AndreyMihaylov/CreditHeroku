package UI.Utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import static UI.Utils.CommonUtils.getPathToResources;

public abstract class YmlReader {

    protected HashMap<?, ?> getData(String fileName) {
        FileInputStream stream = null;
        HashMap<?, ?> object = null;
        Yaml yaml;
        try {
            File file = new File(getPathToResources(fileName));
            stream = new FileInputStream(file);
            yaml = new Yaml();
            object = yaml.load(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stream != null)
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return object;
    }
}