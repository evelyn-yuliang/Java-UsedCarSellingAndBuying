package guiLab.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GetMakeAndModelFromFile implements CarConstants {
    private File file;
    private HashMap<String, ArrayList> makeAndModel;

    public GetMakeAndModelFromFile() {
        file = new File(FILE_NAME_TEXT);
        makeAndModel = new HashMap<>();
        getDictionary();

    }

    private void printD() {
        for (String k : makeAndModel.keySet()) {
            System.out.println(k + " " + makeAndModel.get(k).toString());
        }
    }

    private String[] setToArray(Set<String> set) {
        String[] string = new String[set.size()];
        Iterator ite = set.iterator();
        for (int i = 0; i < set.size(); i++)
            string[i] = (String) ite.next();
        Arrays.sort(string);
        return string;
    }

    private void getDictionary() {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] info = scanner.nextLine().split("\t");
                String[] infoOfMakeAndModel = info[1].split(" ");
                String make = infoOfMakeAndModel[0];
                String model = infoOfMakeAndModel[1];
                System.out.println("read: " + make + " " + model);

                if (makeAndModel.keySet().contains(make))
                    makeAndModel.get(make).add(model);
                else {
                    ArrayList<String> models = new ArrayList<>();
                    models.add(model);
                    makeAndModel.put(make, models);
                }
                //printD();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public String[] getMake() {
        String[] s = setToArray(makeAndModel.keySet());
        return s;
    }

    public String[] getModelByMake(String make) {
        ArrayList<String> model = makeAndModel.get(make);
        String[] s = new String[model.size()];
        int i = 0;
        for (String m : model)
            s[i++] = m;
        return s;
    }

    public static void main(String[] args) {
        GetMakeAndModelFromFile test = new GetMakeAndModelFromFile();
        test.getDictionary();
        test.getMake();
    }
}
