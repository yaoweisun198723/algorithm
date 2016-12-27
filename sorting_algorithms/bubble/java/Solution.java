public class Solution {
	public static void bubbleSort(int[] nums) {
		for (int i=0; i < nums.length-1; i++) {
			for (int j=i+1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					int tmp = nums[i];
					nums[i] = nums[j];
					nums[j] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = {-1, 0, 5, 4, 3, 1, 8};
		Solution.bubbleSort(nums);
		for (int i:nums) {
			System.out.println(i);
		}
	}
}
