package ml.physicsis.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

public class StringAnalyzer {

    private String data;
    private String repairedData;


    private boolean isErrorInData = false;

    private int PRECISION = 10;

    private final int SPECIALS_NUMBER = 4;
    private final int PLUS = 0;
    private final int MINUS = 1;
    private final int MULTIPLY = 2;
    private final int DIVIDE = 3;

    public StringAnalyzer(String data){
        this.data = data;
    }

    public StringAnalyzer(){
        this("");
    }

    public void setData(String data){
        this.data = data;
    }

    public String getData(){
        return this.data;
    }

    private LinkedList<SpecialChar> findSpecials(){
        LinkedList<SpecialChar> specialsPosition = new LinkedList<>();
        for (int i = 0; i < data.length(); i++){
            for (int j = 0; j < SPECIALS_NUMBER; j++) {
                if (data.charAt(i) == SpecialChar.operationSign[j]) {
                    SpecialChar temp = new SpecialChar(i, j);
                    specialsPosition.add(temp);
                }
            }
        }
        return specialsPosition;
    }

    private void solvePart(LinkedList<SpecialChar> specialsPosition, int index){
        int positionInString = specialsPosition.get(index).position;
        int whereFirstNumberStarts = 0;
        int whereFirstNumberEnds = positionInString - 1;
        int whereSecondNumberStarts = positionInString + 1;
        int whereSecondNumberEnds = data.length() - 1;

        if (index > 0) {
            whereFirstNumberStarts = specialsPosition.get(index-1).position + 1;
        }
        if (index < specialsPosition.size()-1) {
            whereSecondNumberEnds = specialsPosition.get(index+1).position - 1;
        }

        String number1 = data.substring(whereFirstNumberStarts, whereFirstNumberEnds+1);
        String number2 = data.substring(whereSecondNumberStarts, whereSecondNumberEnds+1);
        BigDecimal decimal1 = new BigDecimal(number1);
        BigDecimal decimal2 = new BigDecimal(number2);

        BigDecimal result;

        int actCharType = specialsPosition.get(index).charType;

        if (actCharType == MULTIPLY) {
            result = decimal1.multiply(decimal2);
        }
        else if (actCharType == DIVIDE){
            result = decimal1.divide(decimal2, PRECISION, RoundingMode.HALF_UP);
        }
        else if(actCharType == PLUS){
            result = decimal1.add(decimal2);
        }
        else if(actCharType == MINUS){
            result = decimal1.subtract(decimal2);
        }
        else{
            result = new BigDecimal("0");
        }

        String resultString = result.toString();
        data = data.substring(0, whereFirstNumberStarts) + resultString +
                data.substring(whereSecondNumberEnds+1, data.length());
        calculateResultWithoutQuotesHelp(findSpecials());
    }

    private void calculateResultWithoutQuotesHelp(LinkedList<SpecialChar> specialsPosition) {
        if (specialsPosition.size() > 0){
            int indexOfFirstMultiDiv = -1;
            int indexOfFirstPlusMinus = -1;
            boolean MultiDivFound = false;
            boolean PlusMinusFound = false;
            for (int i = 0; i < specialsPosition.size(); i++){
                int type = specialsPosition.get(i).charType;

                if ((type == PLUS || type == MINUS) && PlusMinusFound == false){
                    PlusMinusFound = true;
                    indexOfFirstPlusMinus = i;
                }
                else if((type == MULTIPLY || type == DIVIDE) && MultiDivFound == false) {
                    MultiDivFound = true;
                    indexOfFirstMultiDiv = i;
                }
            }

            if (MultiDivFound == true){
                solvePart(specialsPosition, indexOfFirstMultiDiv);
            }
            else {
                solvePart(specialsPosition, indexOfFirstPlusMinus);
            }
        }
    }

    private void deleteZerosInData(){
        while ((data.length() > 1 && data.charAt(data.length()-1) == '0' && data.indexOf('.') > 0) || data.charAt(data.length()-1) == '.'){
            data = data.substring(0, data.length()-1);
        }
    }

    private void repairWrongData(){
        while (SpecialChar.isSpecialChar(data.charAt(0))){
            data = data.substring(1, data.length());
        }
        while (SpecialChar.isSpecialChar(data.charAt(data.length()-1))){
            data = data.substring(0, data.length()-1);
        }

        for (int i = 1; i < data.length()-1; i++) {
            if (SpecialChar.isSpecialChar(data.charAt(i)) && SpecialChar.isSpecialChar(data.charAt(i+1))){
                if (data.charAt(i) == data.charAt(i+1)){
                    isErrorInData = false;
                    data = data.substring(0, i+1) + data.substring(i+2, data.length());
                    i--;
                }
                else {
                    isErrorInData = true;
                }
            }
        }
    }

    public String getResultWithoutQuotes(){
        repairWrongData();
        repairedData = data;
        System.out.println(data);

        // data variable is going to be result now

        if (isErrorInData == false){
            calculateResultWithoutQuotesHelp(findSpecials());
            deleteZerosInData();
            return data;
        }
        else{
            return "Input error";
        }
    }
}