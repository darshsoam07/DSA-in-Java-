class Solution {
    public boolean halvesAreAlike(String s) {
        String v = "aeiouAEIOU";
        int bal = 0, mid = s.length() / 2;
        for(int i = 0; i < mid; i++){
            if(v.indexOf(s.charAt(i)) != -1) bal++;
            if(v.indexOf(s.charAt(i + mid)) != -1)bal--;
        }
        return bal == 0;
    }
}