
package DataAccess;

import common.Validate;
import common.Library;
import model.Person;

import java.util.ArrayList;


public class FileProcessing {
    private static FileProcessing instance = null;

    public static FileProcessing Instance() {
        if (instance == null) {
            synchronized (FileProcessing.class) {
                if (instance == null) {
                    instance = new FileProcessing();
                }
            }
        }
        return instance;
    }


    public void findPersonInfo() {
        System.out.println("--------- Person Info ---------");
        ArrayList<Person> lp;
        System.out.print("Enter file path: ");
        String pathFile = Validate.checkInputPathFile();
        lp = Library.getListPerson(pathFile);

        if (lp == null) {
            return;
        }
        double money = Validate.checkInputMoney();
        Library.getPerson(lp, money);
    }


    public void copyNewFile() {
        System.out.println("------------- Copy Text --------------");
        System.out.print("Enter Source: ");
        String pathFileInput = Validate.checkInputPathFile();
        System.out.print("Enter new file name: ");
        String pathFileOutput = Validate.checkInputPathFile();

        try {
            if (Library.copyWordOneTimes(pathFileInput, pathFileOutput)) {
                System.out.println("Copy done...");
            } else {
                System.err.println("Copy failed.");
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}


