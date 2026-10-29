import java.util.*;

class Solution {
    private int timer = 1; // 1-indexed for Fenwick Tree compatibility
    private int[] inTime;
    private int[] outTime;
    private int[] depth;
    private int[][] up;
    private int LOG;
    private List<List<Integer>> adj;
    
    // Binary Indexed Tree (Fenwick Tree) for point queries and range updates via subtree bounds
    static class FenwickTree {
        int size;
        int[] bit;

        FenwickTree(int n) {
            this.size = n;
            this.bit = new int[n + 2];
        }

        // Applies change mask up to index position
        void add(int idx, int val) {
            for (; idx <= size; idx += idx & -idx) {
                bit[idx] ^= val;
            }
        }

        // Fetches point mask up to root path cancellation
        int query(int idx) {
            int mask = 0;
            for (; idx > 0; idx -= idx & -idx) {
                mask ^= bit[idx];
            }
            return mask;
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        inTime = new int[n];
        outTime = new int[n];
        depth = new int[n];
        LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        up = new int[n][LOG];
        
        int[] initialMasks = new int[n];
        char[] currentChars = s.toCharArray();
        
        // Initial setup step
        dfs(0, 0, 0, 0, currentChars, initialMasks);

        FenwickTree bit = new FenwickTree(timer);
        // Build the structure using continuous point definitions
        for (int i = 0; i < n; i++) {
            bit.add(inTime[i], initialMasks[i]);
            bit.add(inTime[i] + 1, initialMasks[i]);
        }

        List<Boolean> results = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            String type = parts[0];
            
            if (type.equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                char oldChar = currentChars[node];
                
                if (oldChar != newChar) {
                    int changeMask = (1 << (oldChar - 'a')) ^ (1 << (newChar - 'a'));
                    // A range update on flattened array [inTime, outTime] via BIT updates
                    bit.add(inTime[node], changeMask);
                    bit.add(outTime[node] + 1, changeMask);
                    currentChars[node] = newChar;
                }
            } else { // "query"
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                
                int lca = getLCA(u, v);
                int maskU = bit.query(inTime[u]);
                int maskV = bit.query(inTime[v]);
                int maskLCANodeOnly = 1 << (currentChars[lca] - 'a');
                
                int finalPathMask = maskU ^ maskV ^ maskLCANodeOnly;
                
                // Formulate validity if path has at most 1 bit set to odd count
                boolean canFormPalindrome = (finalPathMask & (finalPathMask - 1)) == 0;
                results.add(canFormPalindrome);
            }
        }

        return results;
    }

    private void dfs(int node, int p, int d, int currentMask, char[] s, int[] initialMasks) {
        inTime[node] = timer++;
        depth[node] = d;
        up[node][0] = p;
        
        for (int i = 1; i < LOG; i++) {
            up[node][i] = up[up[node][i - 1]][i - 1];
        }

        currentMask ^= (1 << (s[node] - 'a'));
        initialMasks[node] = currentMask;

        for (int neighbor : adj.get(node)) {
            if (neighbor != p) {
                dfs(neighbor, node, d + 1, currentMask, s, initialMasks);
            }
        }
        outTime[node] = timer - 1;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        
        if (u == v) return u;
        
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }
}
