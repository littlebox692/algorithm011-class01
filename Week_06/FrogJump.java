public class FrogJump {
    public boolean canCross(int[] stones) {
		HashMap<Integer, Set<Integer>> map = new HashMap<>();
		for (Integer i : stones) {
			map.put(i, new HashSet<Integer>());
		}
		map.get(0).add(0);
		for (int i = 0; i < stones.length; i++) {
			for (int k : map.get(stones[i])) {
                // dp[i][j]代表能否最后一跳长度为j跳到a[i]
				// dp状态转移方程: dp[i][j] = dp[k][j-1] || dp[k][j] || dp[k][j+1]
				for (int j = k - 1; j < k + 1; j++) {
					if (j > 0 && map.containsKey(stones[i] + j)) {
						map.get(stones[i] + j).add(j);
					}
				}
			}
		}
		return map.get(stones[stones.length - 1]).size() > 0;
	}
}