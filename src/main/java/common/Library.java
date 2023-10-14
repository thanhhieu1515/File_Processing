
package common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import model.Person;


public class Library {
       //get list person by path file
    public static ArrayList<Person> getListPerson(String pathFile) {
        ArrayList<Person> lp = new ArrayList<>();
        File file = new File(pathFile);
        //check file exist or not and path must be file
        if (!file.exists() || !file.isFile()) {
            System.err.println("Path doesn't exist");
            return null;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferReader.readLine()) != null) {
                String[] infoPerson = line.split(";");
                lp.add(new Person(infoPerson[0], infoPerson[1],
                        getSalary(infoPerson[2])));

            }
        } catch (IOException e) {
            System.err.println("Can't read file.");
        }
        return lp;
    }

    //get salary 
    public static double getSalary(String salary) {
        double salaryResult = 0;
        try {
            salaryResult = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            salaryResult = 0;
        } finally {
            return salaryResult;
        }
    }

    //display list person
    public static void getPerson(ArrayList<Person> lp, double money) {
        System.out.println("------------- Result ----------");
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Address", "Money");
        for (Person person : lp) {
            if (person.getMoney() >= money) {
                System.out.printf("%-20s%-20s%-20.1f\n", person.getName(),
                        person.getAddress(), person.getMoney());
            }
        }
        Collections.sort(lp);
        System.out.println("Max: " + lp.get(0).getName());
        System.out.println("Min: " + lp.get(lp.size() - 1).getName());
    }

    //get new content
    public static String getNewContent(String pathFileInput) {
        HashSet<String> newContent = new HashSet<>();
        File file = new File(pathFileInput);
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String word = input.next();
                newContent.add(word + " ");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Can’t read file");
        }
        String content = "";
        for (String line : newContent) {
            content += line;
        }
        return content;
    }

    //write new content to file
    public static boolean copyWordOneTimes(String source, String destination) {
    try {
        File sourceFile = new File(source);
        FileReader fileReader = new FileReader(sourceFile);
        BufferedWriter bufferedWriter;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            File destFile = new File(destination);
            FileWriter fileWriter = new FileWriter(destFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            HashSet<String> uniqueWords = new HashSet<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(";");
                for (String word : words) {
                    if (!uniqueWords.contains(word)) {
                        uniqueWords.add(word);
                        bufferedWriter.write(word + " ");
                    }
                }
            }
        }
        bufferedWriter.close();

        return true;
    } catch (IOException e) {
        return false;
    }
}

}
