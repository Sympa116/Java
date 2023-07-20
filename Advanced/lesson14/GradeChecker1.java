// import
import java.io.*;
import java.util.*;

public class GradeChecker1 {
    void run(String[] args) throws IOException {
        HashMap<Integer, Double> student = new HashMap<>();
        File examFile = new File(args[0]); // File型の実体を作成する
        this.readMethod(student, examFile);
    }

    void readMethod(HashMap<Integer, Double> student, File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        Integer max = 0;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Double value = this.calc(array[1]);
            student.put(key, value);
            if (key > max) {
                max = key;
            }
        }
        this.Print(student, max);
        in.close();
    }

    Double calc(String value) throws IOException { // 点数を四捨五入する
        Double score = Double.valueOf(value) * 1000;
        Long afterScore = Math.round(Double.valueOf(score));
        Double ans = (double) afterScore / 1000;
        return ans;
    }

    void Print(HashMap<Integer, Double> student, Integer max) throws IOException {
        for (Integer i = 1; i <= max; i++) {
            if (student.get(i) == null) {
                System.out.printf("%d,0.000,K%n", i);
            } else if (90 <= student.get(i)) {
                System.out.printf("%d,%s,秀%n", i, student.get(i));
            } else if ((80 <= student.get(i)) && (student.get(i) < 90)) {
                System.out.printf("%d,%s,優%n", i, student.get(i));
            } else if ((70 <= student.get(i)) && (student.get(i) < 80)) {
                System.out.printf("%d,%s,良%n", i, student.get(i));
            } else if ((60 <= student.get(i)) && (student.get(i) < 70)) {
                System.out.printf("%d,%s,可%n", i, student.get(i));
            } else {
                System.out.printf("%d,%s,不可%n", i, student.get(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GradeChecker1 app = new GradeChecker1();
        app.run(args);
    }
}