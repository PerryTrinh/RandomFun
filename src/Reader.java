import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Perry on 1/9/2017.
 */
public class Reader {
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> records = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String str;
            while((str = reader.readLine()) != null) {
                String[] ar = str.split(",");
                records.addAll(Arrays.asList(ar));
            }
            reader.close();
            return records;
        } catch (Exception e) {
            System.out.format("Exception occurred while trying to read '%s'.", fileName);
            e.printStackTrace();
            return null;
        }
    }
}
