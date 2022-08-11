import java.util.HashMap;
import java.util.Map;

/*
This excercise it is meant to convert a number into their written name
for example 999 -> nine hundred ninety nine
 */

public class NumberToWords {


        static Map<Integer, String> name = new HashMap<>();


        public static void main(String[] args) {
            //works for numbers from 1 to 99,999
            System.out.println(getNameOfNumber(999));

        }

        public static void fillValues() {
            name.put(0,"");
            name.put(1,"ONE");
            name.put(2, "TWO");
            name.put(3, "THREE");
            name.put(4, "FOUR");
            name.put(5, "FIVE");
            name.put(6, "SIX");
            name.put(7,"SEVEN");
            name.put(8,"EIGHT");
            name.put(9, "NINE");
            name.put(10, "TEN");
            name.put(11,"ELEVEN");
            name.put(12, "TWELVE");
            name.put(13, "THIRTEEN");
            name.put(14, "FOURTEEN");
            name.put(15, "FIFTEEN");
            name.put(16, "SIXTEEN");
            name.put(17,"SEVENTEEN");
            name.put(18, "EIGHTEEN");
            name.put(19, "NINETEEN");
            name.put(20,"TWENTY");
            name.put(30, "THIRTY");
            name.put(40,"FORTY");
            name.put(50,"FIFTY");
            name.put(60, "SIXTY");
            name.put(70,"SEVENTY");
            name.put(80,"EIGHTY");
            name.put(90,"NINETY");
            name.put(100, "HUNDRED");
            name.put(1000, "THOUSAND");
        }

        public static String getNameOfNumber(int number) {
            fillValues();
            String result = "";

            if(number%100 !=0) {

                Map<Integer, String> mapResult = getBelow100(number, result);
                Map.Entry<Integer,String> entry = mapResult.entrySet().iterator().next();
                number = entry.getKey();
                result = entry.getValue();
            }

            if(number%1000 != 0) {

                result = name.get(100) + " " + result;
                int intermediateValue = number / 100 % 10;
                Map<Integer, String> mapResult = getBelow100(intermediateValue, result);
                Map.Entry<Integer,String> entry = mapResult.entrySet().iterator().next();
                number = number/1000 * 1000;
                result = entry.getValue();

            }

            if(number%1000000 != 0) {
                result = name.get(1000) + " " + result;
                int intermediateValue = number / 1000;
                Map<Integer, String> mapResult = getBelow100(intermediateValue, result);
                Map.Entry<Integer,String> entry = mapResult.entrySet().iterator().next();
                number = number/1000 * 1000;
                result = entry.getValue();
            }





            return result;
        }

        private static Map<Integer,String> getBelow100(int number, String result) {

            if(number%100 < 20 && number%100 !=0) {
                result = name.get(number%100) + " " + result;
                number -= number%100;
            } else if(number%100 >= 20) {
                result = name.get(number%10) + " " + result;
                number -= number%10;
                result = name.get(number%100) + " " + result;
                number -= number%100;
            }

            return Map.of(number,result);
        }



}
