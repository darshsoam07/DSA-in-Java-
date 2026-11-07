class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int m = tasks.length;

        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) ->
            a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) ->
            a[0] != b[0] ? a[0] - b[0] : (a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]));

        for (int i = 0; i < servers.length; i++) free.offer(new int[]{servers[i], i});

        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= i) {
                int[] s = busy.poll();
                free.offer(new int[]{s[1], s[2]});
            }

            if (!free.isEmpty()) {
                int[] s = free.poll();
                ans[i] = s[1];
                busy.offer(new int[]{i + tasks[i], s[0], s[1]});
            } else {
                int[] s = busy.poll();
                ans[i] = s[2];
                busy.offer(new int[]{s[0] + tasks[i], s[1], s[2]});
            }
        }
        return ans;
    }
}