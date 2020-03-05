package cn.jumper.study.leecode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jumper
 * 2019/10/14
 */
public class SumArraySolutionTest {
    private SumArraySolution sumArraySolution;

    @BeforeEach
    public void before() {
        sumArraySolution = new SumArraySolution();
    }

    @Test
    public void testLength() {
        int[] nums = new int[0];
        int[] sum = sumArraySolution.twoSum(nums, 9);
        Assertions.assertArrayEquals(sum, null);
        nums = new int[1];
        sum = sumArraySolution.twoSum(nums, 9);
        Assertions.assertArrayEquals(sum, null);
        nums = new int[2];
    }

    @Test
    public void testOne() {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ints = sumArraySolution.twoSum(nums, 9);
        Assertions.assertArrayEquals(ints, new int[]{0, 1});
    }
}
