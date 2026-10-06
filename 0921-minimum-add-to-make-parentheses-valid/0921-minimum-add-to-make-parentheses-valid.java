class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int needOpen = 0;
        
        for(char currentBracket : s.toCharArray()){
            if(currentBracket == '('){
                stack.push(currentBracket);
            } else {
                if(!stack.isEmpty()){
                    stack.pop();
                } else {
                    needOpen++;
                }
            }
        }
        return needOpen + stack.size();
    }
}