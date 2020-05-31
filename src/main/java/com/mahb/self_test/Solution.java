package com.mahb.self_test;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-03-13 17:10
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a triple tree node.
 * public class TreeNode {
 * int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode mid;
 * TreeNode(int x) { val = x; } *}
 */
public class Solution {


    public static void main(String[] args) {


        int[] nums =new int[]{ 2,7,4,5,9,-2,10,-5,20 };

        int[] result = new Solution().twoSum(nums, 0);

        System.out.println(nums[result[0]]+" ---- "+ nums[result[1]] );
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }



    public void showMaxChildArray(int[] arr){





    }


}

