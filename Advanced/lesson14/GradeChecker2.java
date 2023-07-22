
// import
import java.io.*;
import java.util.*;

public class GradeChecker2 {
    void run(String[] args) throws IOException {
        HashMap<Integer, Double> exam = new HashMap<>();
        HashMap<Integer, Integer> assign = new HashMap<>();
        HashMap<Integer, Integer> mini = new HashMap<>();
        File examFile = new File(args[0]); // File型の実体を作成する
        File assignFile = new File(args[1]);
        File miniFile = new File(args[2]);
        this.examReader(exam, examFile);
        this.assignReader(assign, assignFile);
        this.miniReader(mini, miniFile);
        for (Integer i = 1; i < exam.size(); i++) {
            this.scoreCalc(exam, assign, mini, i);
        }
    }

    // exam.csv
    void examReader(HashMap<Integer, Double> student, File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Double value = this.calc(array[1]);
            student.put(key, value);
        }
        in.close();
    }

    Double calc(String value) throws IOException { // 点数を四捨五入する
        Double score = Double.valueOf(value) * 1000;
        Long afterScore = Math.round(Double.valueOf(score));
        Double ans = (double) afterScore / 1000;
        return ans;
    }

    // assignments.csv
    void assignReader(HashMap<Integer, Integer> assign, File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Integer value = this.assignCalc(array); // valueを計算する
            assign.put(key, value);
            // System.out.printf("%d,%s%n", key, value);
        }
        in.close();
    }

    Integer assignCalc(String[] array) { // assignReader:valueの計算
        Integer value = 0;
        for (Integer i = 1; i < array.length; i++) {
            if (Objects.equals(array[i], "")) {
                value += 0;
            } else {
                value += Integer.valueOf(array[i]);
            }
        }
        return value;
    }

    // miniexam.csv
    void miniReader(HashMap<Integer, Integer> mini, File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Integer value = this.miniCalc(array); // valueを計算する
            mini.put(key, value);
            // System.out.printf("%d,%s%n", key, value);
        }
        in.close();
    }

    Integer miniCalc(String[] array) {
        Integer value = 0;
        for (Integer i = 1; i < array.length; i++) {
            if (Objects.equals(array[i], "")) {
                value += 0;
            } else {
                value++;
            }
        }
        return value / 14 * 100;
    }

    void scoreCalc(HashMap<Integer, Double> exam, HashMap<Integer, Integer> assign, HashMap<Integer, Integer> mini,
            Integer i) throws IOException {
        Double score = (70 / 100) * exam.get(i) + (25 / 60) * assign.get(i) + 5 * mini.get(i);
        if (90 <= score) {
            System.out.printf("%d,%f,%s,%d,%d,秀%n", i, score, exam.get(i), assign.get(i), mini.get(i));
        } else if ((80 <= score) && (score < 90)) {
            System.out.printf("%d,%f,%s,%d,%d,優%n", i, score, exam.get(i), assign.get(i), mini.get(i));
        } else if ((70 <= score) && (score < 80)) {
            System.out.printf("%d,%f,%s,%d,%d,良%n", i, score, exam.get(i), assign.get(i), mini.get(i));
        } else if ((60 <= score) && (score < 70)) {
            System.out.printf("%d,%f,%s,%d,%d,可%n", i, score, exam.get(i), assign.get(i), mini.get(i));
        } else {
            System.out.printf("%d,%f,%s,%d,%d,不可%n", i, score, exam.get(i), assign.get(i), mini.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        GradeChecker2 app = new GradeChecker2();
        app.run(args);
    }
}