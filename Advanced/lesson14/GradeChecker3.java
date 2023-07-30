
import java.io.*;
import java.util.*;

public class GradeChecker3 {
    void run(String[] args) throws IOException {
        HashMap<Integer, Double> exam = new HashMap<>(); // 試験成績
        HashMap<Integer, Integer> assign = new HashMap<>(); // 課題
        HashMap<Integer, Double> mini = new HashMap<>(); // 小テスト
        this.Exam(args, exam); // 試験成績の処理
        this.Assign(args, assign); // 課題の処理
        this.Mini(args, mini); // 小テストの処理
        this.Score(exam, assign, mini);
    }

    void Exam(String[] args, HashMap<Integer, Double> exam) throws IOException {
        File file = new File(args[0]); // exam.csv
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        Integer max = 0; // IDの最大値
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Double value = Double.valueOf(array[1]);
            exam.put(key, value);
            if (key > max) { // IDの最大値の処理
                max = key;
            }
        }
        for (Integer i = 1; i <= max; i++) {
            if (Objects.equals(exam.get(i), null)) { // IDが抜けていたら、抜けているIDに「0.0」を格納
                exam.put(i, 0.0);
            }
        }
        in.close();
    }

    void Assign(String[] args, HashMap<Integer, Integer> assign) throws IOException {
        File file = new File(args[1]); // assign.csv
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Integer value = this.calcAssign(array);
            assign.put(key, value);
        }
        in.close();
    }

    // 課題の点数を計算
    Integer calcAssign(String[] array) {
        Integer value = 0;
        for (Integer i = 1; i < array.length; i++) {
            if (Objects.equals(array[i], "")) { // 「null」だったら代わりに「0」を足す
                value += 0;
            } else { // 点数を足す
                value += Integer.valueOf(array[i]);
            }
        }
        return value;
    }

    void Mini(String[] args, HashMap<Integer, Double> mini) throws IOException {
        File file = new File(args[2]); // miniexam.csv
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            String[] array = line.split(",");
            Integer key = Integer.valueOf(array[0]);
            Double value = this.calcMini(array); // 点数の計算
            mini.put(key, value);
        }
        for (Integer i = 1; i < mini.size(); i++) {
            if (Objects.equals(mini.get(i), null)) {
                mini.put(i, 0.0);
            }
        }
        in.close();
    }

    // 小テストの点数の計算
    Double calcMini(String[] array) {
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

    void Score(HashMap<Integer, Double> exam, HashMap<Integer, Integer> assign, HashMap<Integer, Double> mini)
            throws IOException {
        HashMap<Integer, Double> score = new HashMap<>(); // 最終成績
        for (Integer i = 1; i <= exam.size(); i++) { // 最終成績の計算
            Double value = (70.0 / 100.0) * exam.get(i) + (25.0 / 60.0) * Double.valueOf(assign.get(i))
                    + 5.0 * (mini.get(i) / 14.0);
            value = Math.ceil(value); // 小数点以下切り上げ
            score.put(i, value);
            this.calcScore(i, score, exam, assign, mini);
        }
    }

    void calcScore(Integer i, HashMap<Integer, Double> score, HashMap<Integer, Double> exam,
            HashMap<Integer, Integer> assign,
            HashMap<Integer, Double> mini) throws IOException {
        System.out.printf("%d,%2.0f,%.3f,%s,%.0f", i, score.get(i), exam.get(i), assign.get(i), mini.get(i));
        if (Objects.equals(exam.get(i), 0.0)) {
            this.Statistics("K");
        } else if (90 <= score.get(i)) {
            this.Statistics("秀");
        } else if ((80 <= score.get(i)) && (score.get(i) < 90)) {
            this.Statistics("優");
        } else if ((70 <= score.get(i)) && (score.get(i) < 80)) {
            this.Statistics("良");
        } else if ((60 <= score.get(i)) && (score.get(i) < 70)) {
            this.Statistics("可");
        } else {
            this.Statistics("不可");
        }
    }

    void Statistics(String grade) throws IOException {
        System.out.println(grade);
    }

    public static void main(String[] args) throws IOException {
        GradeChecker3 app = new GradeChecker3();
        app.run(args);
    }
}