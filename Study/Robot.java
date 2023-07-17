public class Robot { // 設計図
    /* フィールド */
    String color; // ロボットの色名を格納する変数

    /* メソッド */
    public void hello() { // 「こんにちは」と挨拶する
        System.out.println("こんにちは");
    }

    public void getColor() { // 色名を出力する
        System.out.println("色は" + this.color + "です");
    }
}