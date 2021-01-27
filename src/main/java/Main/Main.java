package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        String fileName = fileNameInput();
        validateFile(fileName);

        List<Integer> array = new ArrayList<>();
        try (BufferedReader bufReader = new BufferedReader(new FileReader("temp.txt"))) {
            String temp;
            while ((temp = bufReader.readLine()) != null) {
                array.add(Integer.parseInt(temp));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println("Raising sequence: " );
        for (Integer var:raisingSequence(array)) {
            System.out.print( var + " ");
        }
        System.out.println();

        System.out.println("Decreasing sequence: " );
        for (Integer var:decreasingSequence(array)) {
            System.out.print( var + " ");
        }
        System.out.println();

        Collections.sort(array);

        getMedian(array);
        System.out.println("Max. value: "  + array.get(array.size()-1));
        System.out.println("Min. value: "  + array.get(0));
        getAverage(array);
    }

    private static String fileNameInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            reader.mark(25);
            System.out.println("Input task title: (10m_2021.txt)");
            return reader.readLine();
        }
        catch (IllegalArgumentException | IOException e)
        {
            return fileNameInput();
        }
    }

    private static void validateFile(String fileName) {
        try {
            Files.write(Paths.get("temp.txt"),
                    (Iterable<String>) Files.lines(Paths.get(fileName))
                            .map(s -> s.replace('O', '0'))::iterator
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getMedian(List<Integer> array) {
        if (array.size() % 2 == 0) {
            System.out.println("Median: " + (double)(array.get(array.size()/2) + array.get((array.size()/2) + 1))/2);
        }
        else {
            System.out.println("Median: " + (double)(array.get((array.size() + 1)/2)));
        }
    }

    private static void getAverage(List<Integer> array) {
        long sum = 0;
        for (Integer var: array) {
            sum = sum + var;
        }
        System.out.println("Average: "  + sum/array.size());
    }

    private static List<Integer> decreasingSequence(List<Integer> array) {
        int counter = 0;
        Integer prevValue = null;
        List<Integer> decreasingList = new LinkedList<>();
        List<Integer> tempList = new LinkedList<>();

        for (Integer var: array) {
            if (prevValue == null || var < prevValue) {
                tempList.add(var);
                counter++;
            }
            else {
                if (counter > decreasingList.size()) {
                    decreasingList = tempList;
                }
                tempList = new LinkedList<>();
                tempList.add(var);
                counter = 0;
            }
            prevValue = var;
        }
        if (counter > decreasingList.size()) {
            decreasingList = tempList;
        }

        return decreasingList;
    }

    private static List<Integer> raisingSequence(List<Integer> array) {
        int counter = 0;
        Integer prevValue = null;
        List<Integer> raisingList = new LinkedList<>();
        List<Integer> tempList = new LinkedList<>();

        for (Integer var:array) {
            if (prevValue == null || var > prevValue ) {
                tempList.add(var);
                counter++;
            }
            else {
                if (counter > raisingList.size()) {
                    raisingList = tempList;
                }
                tempList = new LinkedList<>();
                tempList.add(var);
                counter = 0;
            }
            prevValue = var;
        }
        if (counter > raisingList.size()) {
            raisingList = tempList;
        }

        return raisingList;
    }
}



