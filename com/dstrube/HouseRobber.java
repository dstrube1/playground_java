package com.dstrube;

/*
From ~/java:

Mac:
javac -d bin com/dstrube/HouseRobber.java
java -cp bin com.dstrube.HouseRobber

Windows:
javac -d bin com\dstrube\HouseRobber.java
java -cp bin com.dstrube.HouseRobber

https://leetcode.com/problems/house-robber/
*/

public class HouseRobber {
	public static void main(String[] args) {
		int[] nums = null;
		System.out.println("rob null: " + rob(nums));
		nums = new int[]{};
		System.out.println("rob empty: " + rob(nums));
		nums = new int[]{1};
		System.out.println("rob {1}: " + rob(nums));
		nums = new int[]{1,2};
		System.out.println("rob {1,2}: " + rob(nums));
		nums = new int[]{1,2,3};
		System.out.println("rob {1,2,3}: " + rob(nums));
		nums = new int[]{1,2,3,1};
		System.out.println("rob {1,2,3,1}: " + rob(nums));
		nums = new int[]{2,7,9,3,1};
		System.out.println("rob {2,7,9,3,1}: " + rob(nums));
	}
	
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0){ return 0;}
        if(nums.length == 1) return nums[0];
        if(nums.length == 2){
            return (nums[0] >= nums[1]) ? nums[0] : nums[1];
        }
        int odd_sum = 0;
        int even_sum = 0;
        int o_i = 0; //odd index
        int e_i = 1; //even index
        while(o_i < nums.length || e_i < nums.length){
            if(o_i == nums.length){
                break;
            }
            odd_sum += nums[o_i];
            o_i += 2;

            if(e_i == nums.length){
                break;
            }
            even_sum += nums[e_i];
            e_i += 2;
        }
        return (odd_sum >= even_sum) ? odd_sum : even_sum;
    }
}