class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int n: nums1) {
            map.put(n, 1);
        }
        List<Integer> ans = new ArrayList<>();

        for(int n : nums2) {
            if(map.containsKey(n) && map.get(n) == 1) {
                map.put(n, 0);
                ans.add(n);
            }
        }
        int[] res = new int[ans.size()];

        for(int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}