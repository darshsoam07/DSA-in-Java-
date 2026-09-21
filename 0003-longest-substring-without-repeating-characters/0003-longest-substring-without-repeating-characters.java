class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int right = 0, left = 0;
        int maxLen = 0;

        while (right < s.length()) {
            char currChar = s.charAt(right);
            if(map.containsKey(currChar)) {
                int currCharIdx = map.get(currChar);

                if(currCharIdx >= left) {
                    left = currCharIdx + 1;
                }
            }
            maxLen = Math.max(maxLen, right - left + 1);
            map.put(currChar, right);
            right = right + 1;
        }
        return maxLen;
    }
}