class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        while(i < n && s.charAt(i) == ' ') {
            i++;
        }
        int sign = 1;

        if(i < n && s.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        else if (i < n && s.charAt(i) == '+') {
            i++;
        }
        long num = 0;

        while(i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            num = num * 10 + digit;

            if (num * sign > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            else if (num * sign < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return (int) (num * sign);
    }
}