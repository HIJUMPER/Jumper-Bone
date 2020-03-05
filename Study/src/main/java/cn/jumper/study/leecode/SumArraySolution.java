package cn.jumper.study.leecode;

import java.util.Arrays;

/**
 * @author Jumper
 * 2019/10/14
 */
public class SumArraySolution {

    public int[] twoSum(int[] nums, int target) {
        if (nums.length<2){
            return null;
        }
        int[] copyOf = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copyOf);
        for (int i = 0; i < copyOf.length; i++) {
            inner:
            for (int j = i + 1; j < copyOf.length; j++) {
                if (copyOf[i] + copyOf[j] > target) {
                    break inner;
                }
                if (copyOf[i] + copyOf[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
