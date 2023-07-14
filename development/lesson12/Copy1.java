// 第12講 練習問題12-1 ファイルをコピーする
// https://kauap.github.io/2022autumn/lesson12/copy/#1-ファイルをコピーする

// 必要な import 文を書いてください．
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Copy1 {
    void run(String[] args) throws IOException {
        // コマンドライン引数に必要な分のファイルが指定されているか確認する．
        // args.lengthが2より小さい場合，必要な引数が指定されていない旨を出力して終了する．
        if (args.length < 2) {
            System.out.println("cp: コマンドライン引数には，少なくとも，コピー元，コピー先を指定する必要があります．");
            return;
        }
        // argsの0番目，1番目の要素からそれぞれFile型の実体を作成する．
        // copyメソッドを呼び出す．
        File fromFile = new File(args[0]);
        File toFile = new File(args[1]);
        copy(fromFile, toFile);
    }

    void copy(File from, File to) throws IOException {
        // fromをBufferedReader(FileReader)で開く．
        BufferedReader in = new BufferedReader(new FileReader(from));
        // toをPrintWriter(FileWriter)で開く．
        PrintWriter out = new PrintWriter(new FileWriter(to));
        String line;
        // fromから読み込んだ内容をtoに書き出す．
        // ファイルの終わりまでこの処理を繰り返す．
        while ((line = in.readLine()) != null) {
            out.println(line);
        }
        // from, to から開いたストリームを閉じる．
        in.close();
        out.close();
    }
    // mainメソッドは省略．
    public static void main(String[] args)  throws IOException{
        Copy1 copy = new Copy1();
        copy.run(args);
    }
}

// 完成したら，このファイルをコピーして，Copy2.java を作成してください．
// その際，クラス名，mainメソッドの中の書き換えを忘れないように注意しましょう．
// これ以降も同様に完成後，次の練習問題のためにコピーしてください．