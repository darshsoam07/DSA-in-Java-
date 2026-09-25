import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression,0,expression.length());
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }
    private Set<String> parse(String s,int start, int end){
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        int i = start;
        while(i<end){
            char ch = s.charAt(i);
            if(ch == ','){
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
            } else if(ch == '{'){
                int balance = 1;
                int j = i + 1;
                while(j < end && balance > 0){
                    if(s.charAt(j) == '{') {
                        balance++;
                    } else if(s.charAt(j) == '}'){
                        balance--;
                    }
                    j++;
                }
                Set<String> inside = parse(s, i + 1, j - 1);

                current = multiply(current, inside);
                i = j;
            } else {
                Set<String> temp = new HashSet<>();
                for(String str : current){
                    temp.add(str+ch);
                }
                current = temp;
                i++;
                }
            }
            result.addAll(current);
            return result;
        }   
        public Set<String> multiply(Set<String> a,Set<String> b){
            Set<String> result = new HashSet<>();
            for(String x:a){
                for(String y:b){
                    result.add(x+y);
                }
            }
            return result;
        }
    }