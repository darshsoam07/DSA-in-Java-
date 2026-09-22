class Solution {
    static class Node {
        int prod;
        int[] remain;

        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    static class SegmentTree {
        int n, k;
        Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];
            build(0, 0, n - 1, nums);
        }

        private Node merge(Node left, Node right) {
            if (left == null) return right;
            if (right == null) return left;

            Node res = new Node(k);
            res.prod = (left.prod * right.prod) % k;

            for (int i = 0; i < k; i++) {
                res.remain[i] += left.remain[i];
                int shiftedRemainder = (i * left.prod) % k;
                res.remain[shiftedRemainder] += right.remain[i];
            }
            return res;
        }

        private void build(int node, int l, int r, int[] nums) {
            tree[node] = new Node(k);
            if (l == r) {
                int val = nums[l] % k;
                tree[node].prod = val;
                tree[node].remain[val] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * node + 1, l, mid, nums);
            build(2 * node + 2, mid + 1, r, nums);
            tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
        }

        public void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = new Node(k);
                int modVal = val % k;
                tree[node].prod = modVal;
                tree[node].remain[modVal] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(2 * node + 1, l, mid, idx, val);
            } else {
                update(2 * node + 2, mid + 1, r, idx, val);
            }
            tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
        }

        public Node query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) {
                return tree[node];
            }
            int mid = l + (r - l) / 2;
            if (qr <= mid) {
                return query(2 * node + 1, l, mid, ql, qr);
            } else if (ql > mid) {
                return query(2 * node + 2, mid + 1, r, ql, qr);
            } else {
                Node leftResult = query(2 * node + 1, l, mid, ql, qr);
                Node rightResult = query(2 * node + 2, mid + 1, r, ql, qr);
                return merge(leftResult, rightResult);
            }
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        SegmentTree segTree = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            segTree.update(0, 0, nums.length - 1, index, value);
            Node res = segTree.query(0, 0, nums.length - 1, start, nums.length - 1);
            ans[i] = res.remain[x];
        }

        return ans;
    }
}
