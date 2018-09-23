package ml.physicsis.calculator;

public class SpecialChar {
    public int position;
    public int charType;

    public static final char[] operationSign = {'+', '-', '×', '÷'};

    public SpecialChar(int position, int charType) {
        this.position = position;
        this.charType = charType;
    }

    public static boolean isSpecialChar(char c){
        for (int i = 0; i < operationSign.length; i++){
            if (c == operationSign[i]){
                return true;
            }
        }
        return false;
    }

    public static boolean foundSpecialChar(String s){
        for (int i = 0; i < s.length(); i++){
            if (isSpecialChar(s.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
