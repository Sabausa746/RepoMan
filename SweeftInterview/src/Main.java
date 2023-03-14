import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Interview interview = new Interview();
        System.out.println(interview.singleNumber(new int[]{1, 1, 2, 2, 3, 3, 4, 6, 6})); //Expected answer: 4
        System.out.println(interview.minSplit(127)); //Expected answer: 6
        System.out.println(interview.notContains(new int[]{1,2,3,4,6,7,8})); //Expected answer: 5
        System.out.println(interview.binaryAdditionWithParseInt("1100","100")); //Expected answer: 10000
        System.out.println(interview.binaryAddition("1100","100")); //Expected answer: 10000
        System.out.println(interview.countVariants(5)); //Expected answer: 8

        /*
        I added only the most important methods in the MyList class, however there are more that can be added.
        Since is uses the LinkedHashMap the remove is not dependent on the size of the array, however it is not indexed.
        */

        MyList<Integer> temp = new MyList<>();
        temp.add(10);
        System.out.println("added 10");
        temp.add(5);
        System.out.println("added 5");
        temp.add(4);
        System.out.println("added 4");
        temp.remove(4);
        System.out.println("removed 4");
        temp.add(6);
        System.out.println("added 6");
        temp.remove(5);
        System.out.println("removed 5");
        temp.add(4);
        System.out.println("added 4");
        List<Integer> arr = temp.asList();
        for (Object integer : arr) {
            System.out.println(integer);
        }


    }
}

class Interview {


    public int singleNumber(int[] nums) {
        int[] sortedArr = Arrays.stream(nums).sorted().toArray();
        try {
            for (int i = 0; i < sortedArr.length; i += 2) {
                if (sortedArr[i] != sortedArr[i + 1]) {
                    return sortedArr[i];
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return sortedArr[sortedArr.length - 1];
        }
        System.out.println("Element not found");
        return 0;
    }
    public int minSplit(int amount) {
        int total = 0;
        while (amount >= 50) {
            total++;
            amount -= 50;
        }
        while (amount >= 20) {
            total++;
            amount -= 20;
        }
        while (amount >= 10) {
            total++;
            amount -= 10;
        }
        while (amount >= 5) {
            total++;
            amount -= 5;
        }
        total += amount;
        return total;

    }
    public int notContains(int[] array){
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        list = list.stream().sorted().collect(Collectors.toList());

        for (Integer integer : list) {
            if(integer>1 && !list.contains(integer-1)){
                return integer-1;
            }
        }
        return list.get(list.size()-1)+1;
    }
    public String binaryAdditionWithParseInt(String a, String b){
        int num1 = Integer.parseInt(a,2);
        int num2 = Integer.parseInt(b,2);
        return Integer.toBinaryString((num1+num2));
    }
    public String binaryAddition(String a, String b) {
        int first = a.length() - 1;
        int second = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (first >= 0 || second >= 0) {
            int sum = carry;
            if (first >= 0) {
                sum += a.charAt(first) - '0';
                first--;
            }
            if (second >= 0) {
                sum += b.charAt(second) - '0';
                second--;
            }
            carry = sum >> 1;
            sum = sum & 1;
            sb.append(sum == 0 ? '0' : '1');
        }
        if (carry > 0)
            sb.append('1');

        sb.reverse();
        return String.valueOf(sb);
    }
    public int countVariants(int stairsCount){
        int one = countVariantsRecursion(stairsCount,0,1) ;
        int two = countVariantsRecursion(stairsCount,0,2);
        return one + two;
    }
    private int countVariantsRecursion(int stairsCount,int currentStep,int nextStep){
        if(currentStep + nextStep == stairsCount){
            return 1;
        }else if(currentStep + nextStep > stairsCount)
            return 0;
        else {
            currentStep += nextStep;
            return countVariantsRecursion(stairsCount,currentStep,1) + countVariantsRecursion(stairsCount,currentStep,2);
        }
    }

}