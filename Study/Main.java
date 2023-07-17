public class Main {
    public static void main(String[] args) {
        Robot exRobot = new Robot(); // exRobot「インスタンス（ロボット）」を生成.
                                     // 設計図からロボットが完成し、実際に使える状態になる.
        exRobot.color = "青"; // インスタンス変数colorに"青"を代入
        exRobot.hello(); // helloメソッドを使用
        exRobot.getColor(); // getColorメソッドを使用
    }
}