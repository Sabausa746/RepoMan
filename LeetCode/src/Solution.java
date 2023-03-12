
import javax.print.DocFlavor;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] + nums[j - i] == target) {
                    return new int[]{j - i, j};
                }
            }
        }
        return null;
    }

    public boolean isPalindrome(int x) {
        StringBuilder one = new StringBuilder();
        StringBuilder two = new StringBuilder();

        String xx = String.valueOf(x);
        for (int i = 0; i < xx.length(); i++) {
            one.append(xx.charAt(i));
            two.append(xx.charAt(xx.length() - i - 1));
        }
        String s1 = one.toString();
        String s2 = two.toString();

        return s1.equals(s2);
    }

    public String longestCommonPrefix(String[] strs) {
        String temp = "";
        for (int i = 0; i < strs[0].length(); i++) {
            char a = strs[0].charAt(i);
            boolean tempBool = true;
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() == i) {
                    tempBool = false;
                } else if (strs[j].charAt(i) != a) {
                    tempBool = false;
                    break;
                }
            }
            if (tempBool) {
                temp += a;
            } else break;
        }

        return temp;
    }

    public boolean isValid(String s) {
        Stack<Character> temp = new Stack<>();

        for (char ch : s.toCharArray()) {
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    temp.push(ch);
                    break;

                case ')':
                    if (temp.isEmpty() || temp.pop() != '(') {
                        return false;
                    }
                    break;

                case '}':
                    if (temp.isEmpty() || temp.pop() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if (temp.isEmpty() || temp.pop() != '[') {
                        return false;
                    }
                    break;
            }
        }

        return temp.isEmpty();

    }

    public int removeDuplicates(int[] nums) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i : nums) {
            if (!temp.contains(i)) {
                temp.add(i);
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            nums[i] = temp.get(i);
        }
        return temp.size();

    }

    public int removeElement(int[] nums, int val) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i : nums) {
            if (i != val) {
                temp.add(i);
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            nums[i] = temp.get(i);
        }
        return temp.size();
    }

    public int searchInsert(int[] nums, int target) {
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
            if (nums[i] > target) {
                index = i - 1;
                break;
            }
        }

        return (index == -1) ? 0 : index;
    }

    public int lengthOfLastWord(String s) {
        s = s.trim();
        String[] p = s.split(" ");
        return p[p.length - 1].length();
    }

    public int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int i : digits) {
            sb.append(i);
        }
        String ans = addOne(sb.toString(), 1);
        int[] answer = new int[ans.length()];
        for (int i = 0; i < ans.length(); i++) {
            answer[i] = Integer.parseInt(String.valueOf(ans.charAt(i)));
        }

        return answer;
    }

    public String addOne(String st, int carry) {
        if (st.length() == 0)
            return "";
        if (st.equals("9") && carry == 1) {
            return "10";
        }
        if (st.charAt(st.length() - 1) == '9' && carry == 1) {
            return addOne(st.substring(0, st.length() - 1), 1) + "0";
        } else {
            int temp = Integer.parseInt(String.valueOf(st.charAt(st.length() - 1))) + 1;
            return st.substring(0, st.length() - 1) + temp;
        }
    }

    public String countAndSay(int n) {
        String ans = "1";
        for (int i = 0; i < n - 1; i++) {
            ans = theThing(ans);
        }
        return ans;
    }

    public String theThing(String s) {
        if (s.length() == 1) {
            return "11";
        } else {
            char currChar = '1';
            int num = 0;
            Stack<String> st = new Stack<>();
            for (int i = s.length() - 1; i >= 0; i--) {
                if (currChar != s.charAt(i)) {
                    st.push(currChar + "");
                    st.push(num + "");
                    currChar = s.charAt(i);
                    num = 1;
                } else {
                    num++;
                }
            }
            st.push(currChar + "");
            st.push(num + "");
            StringBuilder stringBuilder = new StringBuilder();
            while (!st.isEmpty()) {
                stringBuilder.append(st.pop());
            }
            return stringBuilder.toString();
        }
    }

    public int romanToInt(String s) {
        Map<Character, Integer> romans = new HashMap<>(Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000));
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                ans += romans.get(s.charAt(i));
            } else if (romans.get(s.charAt(i - 1)) < romans.get(s.charAt(i))) {
                ans += romans.get(s.charAt(i));
                ans -= romans.get(s.charAt(i - 1)) * 2;
            } else {
                ans += romans.get(s.charAt(i));
            }


        }
        return ans;
    }

    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> values = new ArrayList<>();

        ListNode currentNode = head;

        while (currentNode != null) {
            values.add(currentNode.val);
            currentNode = currentNode.next;
        }

        int a = 0;
        int b = values.size() - 1;
        while (a < b) {
            if (values.get(b) != values.get(a))
                return false;
            a++;
            b--;
        }
        return true;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Stack<Character> mg = new Stack<>();
        for (char ch : magazine.toCharArray()) {
            mg.push(ch);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (!mg.contains(ransomNote.charAt(i))) {
                return false;
            } else {
                mg.remove(mg.indexOf(ransomNote.charAt(i)));
            }
        }
        return true;
    }

    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String st = "";
            if (i % 3 == 0) {
                st += "Fizz";
            }
            if (i % 5 == 0) {
                st += "Buzz";
            }
            if (st.isEmpty()) {
                st = i + "";
            }
            ans.add(st);
        }
        return ans;
    }

    public ListNode middleNode(ListNode head) {
        ArrayList<ListNode> values = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            values.add(currentNode);
            currentNode = currentNode.next;
        }
        if (values.size() % 2 == 1) {
            return values.get((int) Math.floor(values.size() / 2f));
        } else return values.get(values.size() / 2 + 1);

    }

    public int[] kWeakestRows(int[][] mat, int k) {
        HashMap<Integer, Integer> soldiers = new HashMap<>();

        for (int i = 0; i < mat.length; i++) {
            int num = 0;
            for (int j = 0; j < mat[0].length; j++) {
                num += mat[i][j];
            }
            soldiers.put(i, num);
        }

        List<Map.Entry<Integer, Integer>> temp = soldiers.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).sorted(Comparator.comparing(Map.Entry::getValue)).toList();
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = temp.get(i).getKey();
        }

        return ans;
    }

    public int numberOfSteps(int num) {
        if (num == 0) {
            return 0;
        } else return num % 2 == 0 ? numberOfSteps(num / 2) + 1 : numberOfSteps(num - 1) + 1;
    }

    public int maximumWealth(int[][] accounts) {
        HashMap<Integer, Integer> moneies = new HashMap<>();

        for (int i = 0; i < accounts.length; i++) {
            int num = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                num += accounts[i][j];
            }
            moneies.put(i, num);
        }
        List<Map.Entry<Integer, Integer>> temp = moneies.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).toList();
        return temp.get(temp.size() - 1).getValue();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummyHead.next;

    }

    public int lengthOfLongestSubstring(String s) {

        ArrayList<Character> thing = new ArrayList<>();
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!thing.contains(s.charAt(i))) {
                thing.add(s.charAt(i));
            } else {
                if (longest < thing.size())
                    longest = thing.size();
                int temp = thing.indexOf(s.charAt(i));
                thing.subList(0, temp + 1).clear();


                thing.add(s.charAt(i));
            }
        }

        return Math.max(thing.size(), longest);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int n : nums1) {
            nums.add(n);
        }
        for (int n : nums2) {
            nums.add(n);
        }
        nums.sort((a, b) -> b - a);
        int s = nums.size();
        int a = nums.get(s / 2);
        return s % 2 == 1 ? nums.get((int) Math.floor(s / 2d)) : (nums.get(s / 2) + nums.get(s / 2 - 1)) / 2d;
    }

    public boolean isHappy(int n) {
        try {
            ArrayList<Integer> arr = new ArrayList<>();
            while (true) {
                String s = n + "";
                int a = 0;
                for (int i = 0; i < s.length(); i++) {
                    a += Math.pow((Integer.parseInt(s.charAt(i) + "")), 2);
                }
                if (a == 1) {
                    return true;
                } else if (arr.contains(a)) {
                    return false;
                } else {
                    arr.add(a);
                    n = a;
                }
            }
        } catch (StackOverflowError s) {
            return false;
        }
//            if(n==1) {
//                return true;
//            }else {
//                String s = n+"";
//                int a = 0;
//                for (int i = 0; i < s.length(); i++) {
//                    a += Math.pow((Integer.parseInt(s.charAt(i) + "")),2);
//                }
//                if(arr.contains(a))
//                    return false;
//                else arr.add(a);
//                return isHappy(a);
//            }
    }

    public boolean isNumber(String s) {
        switch (s.charAt(s.length() - 1)) {
            case 'f', 'l', 'd' -> {
                return false;
            }

        }

        try {
            try {
                if (!s.equalsIgnoreCase("infinity") && !s.equalsIgnoreCase("-infinity") && !s.equalsIgnoreCase("+infinity")) {
                    Double.parseDouble(s);
                    return true;
                }
            } catch (Exception ignored) {
            }
            ArrayList<Character> temp = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
            if (!temp.contains(s.charAt(s.length() - 1))) {
                return false;
            }
            if (s.charAt(0) != '-' || s.charAt(0) != '+') {
                if (!temp.contains(s.charAt(0))) {
                    return false;
                }
                s = s.substring(1);
                Integer.parseInt(s.charAt(0) + "");
            }
            String[] a = s.split("//.");
            for (String f : a) {
                String[] b = f.toLowerCase().split("e");
                if (b.length > 2)
                    return false;
                for (int i = 0; i < b.length; i++) {
                    if (i == 0) {
                        Double.parseDouble(b[i]);
                    } else {
                        Integer.parseInt(b[i]);
                    }
                }
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public int divide(int dividend, int divisor) {
        int a = 1;
        if (dividend < 0) {
            a = -1;
        }
        if (divisor < 0) {
            if (a == -1) {
                a = 1;
            } else a = -1;
        }
        int one = Math.abs(dividend);
        if (one == Integer.MIN_VALUE && (divisor == 1 || divisor == -1)) {
            if (divisor == -1)
                return Integer.MAX_VALUE;
            else return Integer.MIN_VALUE;
        }
        if (one == Integer.MAX_VALUE && (divisor == 1 || divisor == -1)) {
            if (divisor == -1)
                return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }
        int two = Math.abs(divisor);
        int temp = two;
        int counter = 0;
        while (two <= one) {
            two += temp;
            counter++;
        }
        return counter * a;
    }

    public int maxDistance(int[] colors) {
        int longest = 0;
        for (int i = 0; i < colors.length; i++) {
            for (int j = colors.length - 1; j > i; j--) {
                while (colors[j] == colors[i]) {
                    if (j == 0) {
                        break;
                    }
                    j--;
                }
                if (longest < j - i) {
                    longest = j - i;
                }
                break;
            }
        }
        return longest;
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int best = 0;
        for (int a : arr) {
            if (a > best) {
                best++;
            }
        }
        return best;
    }

    public int reverse(int x) {
        String s = x + "";
        String newS = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            newS += s.charAt(i);
        }
        if (newS.charAt(newS.length() - 1) == '-') {
            newS = '-' + newS.substring(0, newS.length() - 1);
        }
        long f = Long.parseLong(newS);
        if (f > Integer.MAX_VALUE || f < Integer.MIN_VALUE) {
            return 0;
        } else return (int) f;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> numsB = new ArrayList<>();
        for (int n : nums) {
            numsB.add(n);
        }
        numsB.sort(Comparator.comparingInt(a -> a));

        for (int i = 0; i < numsB.size() - 2; i++) {
            if (i > 0 && numsB.get(i) == numsB.get(i - 1)) {
            }
            int left = i + 1;
            int right = numsB.size() - 1;

            while (left < right) {
                int total = numsB.get(i) + numsB.get(left) + numsB.get(right);
                if (total == 0) {
                    ans.add(new ArrayList<>(List.of(numsB.get(i), numsB.get(left), numsB.get(right))));
                    left += 1;
                    right -= 1;

                    while (numsB.get(left) == numsB.get(left - 1) && left < right) {
                        left += 1;
                    }
                    while (numsB.get(right) == numsB.get(right + 1) && left < right) {
                        right -= 1;
                    }
                } else if (total < 0) {
                    left += 1;
                } else right -= 1;
            }
        }
        ans.forEach(a -> a.sort((f, b) -> b - f));
        return ans.stream().distinct().toList();
    }

    public String longestPalindrome(String s) {
        String m = "";
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            // odd length
            int left = i;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > longest) {
                    longest = right - left + 1;
                    m = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
            // even length
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > longest) {
                    longest = right - left + 1;
                    m = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }
        return m;
    }

    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        if (s.equals(""))
            return null;

        ArrayList<StringBuilder> sbs = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            sbs.add(new StringBuilder());
        }
        int j = 0;
        int dir = 1;
        for (int i = 0; i < s.length(); i++) {
            sbs.get(j).append(s.charAt(i));
            if (j + dir == numRows || j + dir == -1) {
                dir *= -1;
            }
            j += dir;
        }
        String ret = "";
        for (int i = 0; i < sbs.size(); i++) {
            ret += sbs.get(i).toString();
        }
        return ret;
    }

    public int myAtoi(String s) {
        s = s.trim();

        int isNeg = 1;
        if (s.charAt(0) == '-') {
            isNeg = -1;
            s = s.substring(1);
        }
        s = removeLetters(s);

        if (s.length() <= 1)
            return Integer.parseInt(s) * isNeg;
        while (s.charAt(0) == '0') {
            s = s.substring(1);
        }
        long ansLong = Long.parseLong(s) * isNeg;

        if (ansLong > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (ansLong < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) ansLong;
        }
    }

    private String removeLetters(String s) {
        ArrayList<Character> arrayList = new ArrayList<>();

        arrayList.add('1');
        arrayList.add('2');
        arrayList.add('3');
        arrayList.add('4');
        arrayList.add('5');
        arrayList.add('6');
        arrayList.add('7');
        arrayList.add('8');
        arrayList.add('9');
        arrayList.add('0');

        int i = 0;
        while (i < s.length() && arrayList.contains(s.charAt(i))) {
            i++;
        }
        return s.substring(0, i);
    }


    String s = "";
    String p = "";

    public boolean isMatch(String s, String p) {
        this.p = p;
        this.s = s;
        return isMatchRecursion(0, 0);
    }

    public boolean isMatchRecursion(int sPointer, int pPointer) {
        if (sPointer >= s.length() && pPointer >= p.length()) {
            return true;
        }
        if (pPointer >= p.length()) {
            return false;
        }

        boolean match = sPointer < s.length() && (s.charAt(sPointer) == p.charAt(pPointer) || p.charAt(pPointer) == '.');

        if ((pPointer + 1) < p.length() && p.charAt(pPointer + 1) == '*') {
            return isMatchRecursion(sPointer, pPointer + 2) || (match && isMatchRecursion(sPointer + 1, pPointer));
        }
        if (match) {
            return isMatchRecursion(sPointer + 1, pPointer + 1);
        }
        return false;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0){
            return  null;
        }

        ArrayList<Integer> values = new ArrayList<>();
        for (ListNode node : lists) {
            while(node != null){
                values.add(node.val);
                node = node.next;
            }
        }
        values.sort(Comparator.comparingInt(a -> a));

        ListNode ans = null;
        ListNode temp = null;
        for (Integer i : values){
            if(temp == null){
                temp = new ListNode(i);
                ans = temp;
            }
            else{
                temp.next = new ListNode(i);
                temp = temp.next;
            }
        }
        return ans;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        int wordLength = words[0].length();
        int totalWordsLength = wordLength * words.length;
        Map<String, Integer> hash = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        char[] s2 = s.toCharArray();
        for (String value : words) {
            hash.putIfAbsent(value, hash.size());
        }
        int[] count = new int[hash.size()];
        for (String word : words) {
            count[hash.get(word)]++;
        }
        for (int i = 0; i < wordLength; i++) {
            for (int j = i; j <= s.length() - totalWordsLength; j += wordLength) {
                int[] localCount = new int[hash.size()];
                for (int k = j + totalWordsLength - wordLength; k >= j; k -= wordLength) {
                    String str = new String(s2, k, wordLength);     // [ k, k+wordLength )
                    Integer key = hash.get(str);
                    if (!(key != null && count[key] >= ++localCount[key])) {
                        j = k;
                        break;
                    }
                    if (j == k) {
                        ans.add(j);
                    }
                }
            }
        }
        return ans;
    }
    public int factorial(int i){
        if(i==1){
            return 1;
        }else return i * factorial(i-1);
    }

}