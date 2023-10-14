package lab1;

import java.util.ArrayList;

public class StringCalculator{
    public static void main(String[] args){
        StringCalculator strcalc = new StringCalculator();
        String str = "//[=]\n1=2===3==2";
        try{
            if (str.endsWith(",") || str.endsWith("\n")) {
                throw new IllegalArgumentException("Invalid input: string ends with a delimiter");
            }

            int sum = strcalc.add(str);
            System.out.println("The sum of these numbers is: " + sum);
        }
        catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }

    public int add(String numbers){
        if (numbers == null || numbers.isEmpty()){
            return 0;
        }
        if (numbers.length() > 4  && numbers.charAt(0) == '/' && numbers.charAt(1) == '/' && numbers.charAt(3) == '\n'){
            String del = String.valueOf(numbers.charAt(2));
            numbers = numbers.substring(4);
            numbers = numbers.replace(del, ",");
        }
        if (numbers.length() > 6  && numbers.charAt(0) == '/' && numbers.charAt(1) == '/' && numbers.charAt(2) == '[' && numbers.charAt(4) == ']' && numbers.charAt(5) == '\n'){
            String del = String.valueOf(numbers.charAt(3));
            numbers = numbers.substring(6);
            numbers = numbers.replace(del, ",");
        }
        String[] nums = numbers.split("[,\\n]+");
        int sum = 0;
        for (String num : nums){
            try {
                Integer.parseInt(num);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        ArrayList<Integer> neg_int = new ArrayList<>();
        for(String num : nums){
            if(Integer.parseInt(num) < 0){
                neg_int.add(Integer.parseInt(num));
            }
        }
        if(!neg_int.isEmpty()){
            throw new IllegalArgumentException("Negative numbers are not allowed: " +neg_int);
        }
        for(String num : nums){
            if(Integer.parseInt(num) <= 1000){
                sum += Integer.parseInt(num);
            }
        }
        return sum;
    }
}