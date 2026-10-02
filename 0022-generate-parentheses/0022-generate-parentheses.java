class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generate("", n, ans);
        return ans;
    }
    void generate(String current, int n, List<String> ans) {
        if(current.length() == 2 * n) {
            if(isValid(current)){
                ans.add(current);
            }
            return;
        }
        generate(current + "(", n, ans);
        generate(current + ")", n, ans);
    }
    boolean isValid(String s) {
        int balanced = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '('){
                balanced++;
            }
            else{
                balanced--;
            }
            if(balanced < 0){
                return false;
            }
        }
        return balanced == 0;
    }
}