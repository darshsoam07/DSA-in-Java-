class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int cheapestPrice = Integer.MAX_VALUE;
        for (int[] vec : items) {
            int f = vec[0];
            int p = vec[1];
            freq[f]++;
            cheapestPrice = Math.min(cheapestPrice, p);
        }

        int[] factorSum = new int[n + 1]; 
        for (int f = 1; f <= n; f++) {
            for (int k = f; k <= n; k += f) {
                factorSum[f] += freq[k];
            }
        }

        List<int[]> units = new ArrayList<>();
        for (int[] vec : items) {
            int f = vec[0];
            int p = vec[1];

            int cnt = factorSum[f] - 1; 

            if (cnt > 0 && p < 2L * cheapestPrice)
                units.add(new int[]{p, cnt});
        }

        units.sort((a, b) -> Integer.compare(a[0], b[0]));

        int result = 0;

        for (int[] unit : units) {
            int price = unit[0];
            int factorCount = unit[1];

            int k = Math.min(factorCount, budget / price);
            budget -= k * price;
            result += 2 * k; 
        }

        result += budget / cheapestPrice; 
        return result;
    }
}