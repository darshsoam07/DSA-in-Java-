class Solution {
    private List<Integer>[] adj;
    private int[] newParent;
    private int[] lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.newParent = parent.clone();
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        findNewParents(0);

        for (int i = 0; i < n; i++) {
            adj[i].clear();
        }
        for (int i = 1; i < n; i++) {
            adj[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        countSizes(0, ans);
        return ans;
    }

    private void findNewParents(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : adj[u]) {
            findNewParents(v);
        }
        lastSeen[charIdx] = prevAncestor;
    }

    private int countSizes(int u, int[] ans) {
        int size = 1;
        for (int v : adj[u]) {
            size += countSizes(v, ans);
        }
        ans[u] = size;
        return size;
    }
}