public class LongestPalindrome {

    /**
     * 暴力求解
     */
	public String longestPalindrome(String s) {
		// 遍历所有的子字符串,不需要截取，只要index即可
		int length = s.length();
		int max = 0;
		char[] charArray = s.toCharArray();
		int left = 0;
		int right = 0;
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				if ((j - i) > max && validPalindrome(charArray, i, j)) {
					max = j - i;
					left = i;
					right = j;
				}
			}
		}
		return (right - left) == 0 ? "" : s.substring(left, right);
	}

	private boolean validPalindrome(char[] charArray, int left, int right) {
		while (left < right) {
			if (charArray[left] != charArray[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
    }
    
     /**
	 * dp[i][j]表示子串s[i][j]是否为回文字符串
	 * dp[i][j] = (s[i] == s[j]) && dp[i + 1][j - 1]
	 */
	public String longestPalindrome2(String s) {
		// 边界判断
		int length = s.length();
		if (length < 2) {
			return s;
		}
		int maxLen = 0;
		int left = 0;
		int right = 0;
		// init base case
		boolean[][] dp = new boolean[length][length];
		for (int i = 0; i < length; i++) {
			dp[i][i] = true;
		}
		char[] chars = s.toCharArray();
		for (int j = 1; j < length; j++) {
			for (int i = 0; i < j; i++) {
				if (chars[i] != chars[j]) {
					dp[i][j] = false;
				} else {
					if (j - i < 3) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}
				if (dp[i][j] && (j - i) > maxLen) {
					maxLen = j - i;
					left = i;
					right = j;
				}
			}
		}
		return (right - left) == 0 ? "" : s.substring(left, right);
	}
}