package values;


import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColumnValue {

    private static final String PATH = "C:\\Users\\Adam\\DatabaseIntrusionDetection\\columns\\";

    public static String getValue(String column) {
        List<String> values = prepareValuesFromFile(column);
        return getValueFromList(values);
    }

    private static List<String> prepareValuesFromFile(String column){
        List<String> values = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH + column + ".txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                values.add(line);
                line = br.readLine();
            }
            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

    private static String getValueFromList(List<String> values){
        Random rand = new Random();
        int index = rand.nextInt(values.size());
        String value = values.get(index);
        if(!StringUtils.isNumeric(value)){
            value = "\"" + value + "\"";
        }
        return value;
    }
}
