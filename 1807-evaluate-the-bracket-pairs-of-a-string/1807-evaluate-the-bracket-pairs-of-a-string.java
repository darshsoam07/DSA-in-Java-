import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        StringBuilder result = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        int i = 0;
        int n = s.length();
        
        // 2. Parse the string in a single pass
        while (i < n) {
            char current = s.charAt(i);
            
            if (current == '(') {
                i++; // Move past the opening bracket '('
                int start = i;
                
                // Find the closing bracket ')'
                while (i < n && s.charAt(i) != ')') {
                    i++;
                }
                
                // Extract the key inside the brackets
                String key = s.substring(start, i);
                result.append(map.getOrDefault(key, "?"));
                i++; 
            } else {
                result.append(current);
                i++;
            }
        }
        
        return result.toString();
    }
}
