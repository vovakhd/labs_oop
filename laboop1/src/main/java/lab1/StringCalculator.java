package lab1;

public class StringCalculator{
    public static void main(String[] args){
        StringCalculator strcalc = new StringCalculator();
        String str = "1\n2";
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
        if (numbers.contains(",\n") || numbers.contains("\n,") || numbers.contains("\n\n") || numbers.contains(",,")){
            throw new IllegalArgumentException("Invalid input: several delimiters in a row");
        }
        String[] nums = numbers.split("[,\\n]");
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