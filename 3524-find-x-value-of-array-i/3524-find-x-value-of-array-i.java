class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        long[] previousRemainderCount = new long[k];

        for(int num : nums) {
            long[] currentRemainderCount = new long[k];

            int numRemainder = num % k;
            currentRemainderCount[numRemainder]++;

            for(int oldRemainder = 0; oldRemainder < k; oldRemainder++){
                int newRemainder = (oldRemainder * numRemainder) % k;
                currentRemainderCount[newRemainder] += previousRemainderCount[oldRemainder];
            }

            previousRemainderCount = currentRemainderCount;

            for(int remainder = 0; remainder < k; remainder++) {
                res[remainder] += previousRemainderCount[remainder];
            }
        }
        return res;
    }
}