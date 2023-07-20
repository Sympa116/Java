// import
import java.io.*;

public class GradeChecker1 {
    void run(String[] args) throws IOException{
        File examFile = new File(args[0]); // File型の実体を作成する
        this.readMethod(examFile);
    }

    void readMethod(File file) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while((line = in.readLine()) != null){
            System.out.printf(line);
            System.out.println();
        }
        in.close();
    }

    public static void main(String[] args) throws IOException{
        GradeChecker1 app = new GradeChecker1();
        app.run(args);
    }
}