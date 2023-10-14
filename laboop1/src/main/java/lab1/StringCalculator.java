package lab1;

import java.util.Scanner;

public class StringCalculator{
    public static void main(String[] args){
        StringCalculator strcalc = new StringCalculator();
        Scanner sc = new Scanner(System.in);

        try{
            System.out.print("Enter a string of up to 2 numbers: ");
            String string = sc.nextLine();

            int sum = strcalc.add(string);

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
        if (nums.length > 2) {
            throw new IllegalArgumentException("Error: The method accepts up to 2 numbers.");
        }
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