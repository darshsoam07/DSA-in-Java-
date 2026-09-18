class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        Map<Character,int[]> mp = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(mp.containsKey(ch)) mp.get(ch)[1]=i;
            else mp.put(ch,new int[]{i,i});
        }
        List<int[]> subs = new ArrayList<>();
        for(Map.Entry<Character,int[]> entry : mp.entrySet()){
            int left = entry.getValue()[0];
            int right = entry.getValue()[1];
            boolean valid = true;
            for(int i = left+1; i < right; i++) {
                int[] range = mp.get(s.charAt(i));
                if(range[0]<left) {valid = false; break;}
                else if(range[1] > right) right = Math.max(right, range[1]);
            }
            if(valid) subs.add(new int[]{left,right});
        }
        Collections.sort(subs,(a,b)->Integer.compare(a[1],b[1]));
        List<String> ans = new ArrayList<>();

        for(int i = 0; i < subs.size(); i++) {
            ans.add(s.substring(subs.get(i)[0], subs.get(i)[1]+1));
            while(i<subs.size()-1 && subs.get(i)[0]<subs.get(i+1)[1] && subs.get(i+1)[0]<subs.get(i)[1]) i++;
        }
        return ans;
    }
}