package com.dstrube.integertostring;

import java.text.DecimalFormat;

public class Integer2String {

    private Integer2String(){}

    //Names of multiples of 10
    private static final String[] tensNames = { "", " ten", " twenty",
            " thirty", " forty", " fifty", " sixty", " seventy", " eighty",
            " ninety" };

    //Some other names of numbers
    private static final String[] numNames = { "", " one", " two", " three",
            " four", " five", " six", " seven", " eight", " nine", " ten",
            " eleven", " twelve", " thirteen", " fourteen", " fifteen",
            " sixteen", " seventeen", " eighteen", " nineteen" };

    /**
     * Converts a number to a string
     * @param number The number to convert to a string
     * @return The number converted to a string
     */
    public static String int2str(int number){
        if(number == 0) {
            return "zero";
        }
        String negativePrefix = "";
        boolean isIntegerMinValue = false;
        if(number < 0){
            negativePrefix = "negative ";
            if (number == Integer.MIN_VALUE) {
                isIntegerMinValue = true;
                number = Integer.MAX_VALUE;
            }else{
                number = Math.abs(number);
            }
        }

        String snumber;
        String result;

        // pad with 0s
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0, 3));
        //System.out.println("billions: " + billions);
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(snumber.substring(3, 6));
        //System.out.println("millions: " + millions);
        // nnnnnnXXXnnn
        int thousands = Integer.parseInt(snumber.substring(6, 9));
        //System.out.println("thousands: " + thousands);
        // nnnnnnnnnXXX
        int hundreds = Integer.parseInt(snumber.substring(9, 12));
        //System.out.println("hundreds: " + hundreds);

        //Integer.MAX_VALUE = (2^31) - 1 = 2,147,483,647
        //A little over 2 billion, so we won't get into tens or hundreds of billions;
        //but still, keeping this in place in case we want to switch to a long instead of int.
        //(Long.MAX_VALUE = 9,223,372,036,854,775,807: a little over 9 quintillion)
        String sBillions;
        if (billions == 0) {
            sBillions = "";
        } else {
            sBillions = convertLessThan1k(billions) + " billion ";
        }
        result = sBillions;

        String sMillions;
        if (millions == 0) {
            sMillions = "";
        } else {
            sMillions = convertLessThan1k(millions) + " million ";
        }
        result += sMillions;

        String sThousands;
        if (thousands == 0) {
            sThousands = "";
        } else {
            sThousands = convertLessThan1k(thousands) + " thousand ";
        }
        result += sThousands;

        if (isIntegerMinValue){
            //Integer.MIN_VALUE = -2,147,483,648; absolute value of Integer.MIN_VALUE is greater than
            // Integer.MAX_VALUE by 1
            hundreds++;
        }

        String sHundreds = convertLessThan1k(hundreds);
        result += sHundreds;

        //Remove extra spaces
        result = result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");

        return negativePrefix + result;
    }

    private static String convertLessThan1k(int number) {
        String result;

        if (number % 100 < 20) {
            result = numNames[number % 100];
            number /= 100;
        } else {
            result = numNames[number % 10];
            number /= 10;

            result = tensNames[number % 10] + result;
            number /= 10;
        }
        if (number == 0) {
            return result;
        }
        return numNames[number] + " hundred" + result;
    }
}
