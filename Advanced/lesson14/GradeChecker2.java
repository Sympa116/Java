
// import
import java.io.*;
import java.util.*;

public class GradeChecker2 {
    void run(String[] args) throws IOException {
        HashMap<Integer, Double> exam = new HashMap<>();
        HashMap<Integer, Integer> assign = new HashMap<>();
        HashMap<Integer, Double> mini = new HashMap<>();
        File examFile = new File(args[0]); // File型の実体を作成する
        File assignFile = new File(args[1]);
        File miniFile = new File(args[2]);
        this.examReader(exam, examFile);
        this.assignReader(assign, assignFile);
        this.miniReader(mini, miniFile);
        for (Integer i = 1; i <= exam.size(); i++) {
            this.scoreCalc(exam, assign, mini, i);
        }
    }

    // exam.csv
    void examReader(HashMap<Integer, Double> student, File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        Integer max = 0;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Double value = Double.valueOf(array[1]);
            student.put(key, value);
            if (key > max) {
                max = key;
            }
            // System.out.printf("%d,%.3f%n", key, value);
        }
        for (Integer i = 1; i <= max; i++) {
            if (Objects.equals(student.get(i), null)) {
                student.put(i, 0.0);
            }
            // System.out.printf("%d,%.3f%n", i, student.get(i));
        }
        in.close();
    }

    // assignments.csv(ok)
    void assignReader(HashMap<Integer, Integer> assign, File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Integer value = this.assignCalc(array);
            assign.put(key, value);
        }
        in.close();
    }

    Integer assignCalc(String[] array) {
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

    // miniexam.csv(ok)
    void miniReader(HashMap<Integer, Double> mini, File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Double value = this.miniCalc(array); // valueを計算する
            mini.put(key, value);
        }
        for (Integer i = 1; i < mini.size(); i++) {
            if (Objects.equals(mini.get(i), null)) {
                mini.put(i, 0.0);
            }
            // System.out.printf("%d,%.1f%n", i, mini.get(i)/14.0);
        }
        in.close();
    }

    Double miniCalc(String[] array) {
        Double value = 0.0;
        for (Integer i = 1; i < array.length; i++) {
            if (Objects.equals(array[i], "")) {
                value += 0.0;
            } else {
                value += 1.0;
            }
        }
        return value;
    }

    void scoreCalc(HashMap<Integer, Double> exam, HashMap<Integer, Integer> assign, HashMap<Integer, Double> mini,
            Integer i) throws IOException {
        Double score = (70.0 / 100.0) * exam.get(i) + (25.0 / 60.0) * Double.valueOf(assign.get(i))
                + 5.0 * (mini.get(i) / 14.0);
        // System.out.printf("%d,%.0f%n", i, score);
        score = Math.ceil(score); // 小数点以下切り上げ
        if (Objects.equals(exam.get(i), 0.0)) {
            System.out.printf("%d,%2.0f,,%s,%.0f,K%n", i, score, assign.get(i), mini.get(i));
        } else if (90 <= score) {
            System.out.printf("%d,%2.0f,%.3f,%s,%.0f,秀%n", i, score, exam.get(i), assign.get(i), mini.get(i));
        } else if ((80 <= score) && (score < 90)) {
            System.out.printf("%d,%2.0f,%.3f,%s,%.0f,優%n", i, score, exam.get(i), assign.get(i),
                    mini.get(i));
        } else if ((70 <= score) && (score < 80)) {
            System.out.printf("%d,%2.0f,%.3f,%s,%.0f,良%n", i, score, exam.get(i), assign.get(i),
                    mini.get(i));
        } else if ((60 <= score) && (score < 70)) {
            System.out.printf("%d,%2.0f,%.3f,%s,%.0f,可%n", i, score, exam.get(i), assign.get(i),
                    mini.get(i));
        } else {
            System.out.printf("%d,%2.0f,%.3f,%s,%.0f,不可%n", i, score, exam.get(i), assign.get(i), mini.get(i));
        }

    }

    public static void main(String[] args) throws IOException {
        GradeChecker2 app = new GradeChecker2();
        app.run(args);
    }
}