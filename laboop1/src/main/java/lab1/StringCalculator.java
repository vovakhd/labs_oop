package lab1;

import java.util.Scanner;

public class StringCalculator{
    public static void main(String[] args){
        StringCalculator string_calculator = new StringCalculator();
        Scanner sc = new Scanner(System.in);

        try{
            System.out.print("Enter a string: ");
            String string = sc.nextLine();

            if (string.endsWith(",")) {
                throw new IllegalArgumentException("Invalid input: string ends with a comma");
            }

            int sum = string_calculator.add(string);

            System.out.println();
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
        String[] nums = numbers.split(",");
        int sum = 0;
        for (String num : nums){
            try {
                sum += Integer.parseInt(num);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return sum;
    }
}