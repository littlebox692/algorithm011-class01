public class MaxCoins {
    public int maxCoins(int[] nums) {
		// 构建包含虚拟节点的开区间
		int n = nums.length;
		int[] rec = new int[n + 2];
		rec[0] = rec[n + 1] = 1;
		for (int i = 1; i < n + 1; i++) {
			rec[i] = nums[i - 1];
		}
		// dp数组 dp[i][j] = x,表示戳破i和气球j之间(开区间)的所有气球，可以获得的最大coins为x
		// dp状态转移方程: dp[i][j] = dp[i][k] + dp[k][j] + rec[i] * rec[k] * rec[j]
		// base case
		int[][] dp = new int[n + 2][n + 2];
		for (int i = n; i >= 0; i--) {
			for (int j = i + 1; j <= n + 1; j++) {
				for (int k = i + 1; k < j; k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + rec[i] * rec[j] * rec[k]);
				}
			}
		}
		return dp[0][n + 1];
	}
}