public class Permute {
    private static List<List<Integer>> res = new LinkedList<>();

	public static void main(String[] args) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        int[] nums2 = {1, 2, 3, 4};
        backtrack(nums, track);
        return res;
    }
    
    private static void backtrack(int[] nums, LinkedList<Integer> track) {
		if (nums.length == track.size()) {
			res.add(track);
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (track.contains(nums[i])) {
				continue;
			}
			track.add(nums[i]);
			backtrack(nums, track);
			track.removeLast();
		}

	}
    
}