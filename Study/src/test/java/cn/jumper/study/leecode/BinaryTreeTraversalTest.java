package cn.jumper.study.leecode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Jumper
 * 2020/1/11
 */
public class BinaryTreeTraversalTest {
    private BinaryTreeTraversal binaryTreeTraversal = new BinaryTreeTraversal();

    public TreeNode dataOne = null;
    public TreeNode dataTow = null;
    public TreeNode dataThree = null;
    public TreeNode dataFour = null;
    public TreeNode dataFive = null;
    public TreeNode dataSix = null;

    @BeforeAll
    public void before() {
         /*
                1
                 \
                  2
                 /
                3
         */
        dataOne = new TreeNode(1);
        dataOne.left = null;
        dataOne.right = new TreeNode(2);
        dataOne.right.left = new TreeNode(3);

        /*
                1
             /     \
            2       3
             \     /
              4   5
         */
        dataTow = new TreeNode(1);
        dataTow.left = new TreeNode(2);
        dataTow.left.right = new TreeNode(4);
        dataTow.right = new TreeNode(3);
        dataTow.right.left = new TreeNode(5);

         /*
                3
              /   \
             1     2
         */
        dataThree = new TreeNode(3);
        dataThree.left = new TreeNode(1);
        dataThree.right = new TreeNode(2);

        /*
                  3
                 / \
                9  20
                  /  \
                 15   7
         */
        dataFour = new TreeNode(3);
        dataFour.left = new TreeNode(9);
        dataFour.right = new TreeNode(20);
        dataFour.right.left = new TreeNode(15);
        dataFour.right.right = new TreeNode(7);
        /*
                  3
                 / \
                9  20
              /       \
            10         7
         */
        dataFive = new TreeNode(3);
        dataFive.left = new TreeNode(9);
        dataFive.left.left = new TreeNode(10);
        dataFive.right = new TreeNode(20);
        dataFive.right.right = new TreeNode(7);


        /*
                  3
                 / \
                9  20
              /
            10
         */
        dataSix = new TreeNode(3);
        dataSix.left = new TreeNode(9);
        dataSix.left.left = new TreeNode(10);
        dataSix.right = new TreeNode(20);
    }


    @Test
    public void preorderTraversal() {
        List<Integer> preorderTraversal = binaryTreeTraversal.preorderTraversal(dataOne);
        System.out.println(preorderTraversal);
        Assertions.assertEquals(3, preorderTraversal.size());
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, preorderTraversal.toArray(new Integer[preorderTraversal.size()]));


        preorderTraversal = binaryTreeTraversal.preorderTraversal(dataTow);
        System.out.println(preorderTraversal);
        Assertions.assertEquals(5, preorderTraversal.size());
        Assertions.assertArrayEquals(new Integer[]{1, 2, 4, 3, 5}, preorderTraversal.toArray(new Integer[preorderTraversal.size()]));

    }

    @Test
    public void inorderTraversal() {
        List<Integer> preorderTraversal = binaryTreeTraversal.inorderTraversal(dataOne);
        System.out.println(preorderTraversal);
        Assertions.assertEquals(3, preorderTraversal.size());
        Assertions.assertArrayEquals(new Integer[]{1, 3, 2}, preorderTraversal.toArray(new Integer[preorderTraversal.size()]));

    }

    @Test
    public void postorderTraversal() {
        List<Integer> preorderTraversal = binaryTreeTraversal.postorderTraversal(dataOne);
        System.out.println(preorderTraversal);
        Assertions.assertEquals(3, preorderTraversal.size());
        Assertions.assertArrayEquals(new Integer[]{3, 2, 1}, preorderTraversal.toArray(new Integer[preorderTraversal.size()]));

        preorderTraversal = binaryTreeTraversal.postorderTraversal(dataThree);
        System.out.println(preorderTraversal);
        Assertions.assertEquals(3, preorderTraversal.size());
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, preorderTraversal.toArray(new Integer[preorderTraversal.size()]));

    }

    @Test
    public void levelorderTraversal() {

        List<List<Integer>> levelOrder = binaryTreeTraversal.levelOrder(dataThree);

        Assertions.assertEquals(2, levelOrder.size());
        Assertions.assertArrayEquals(new Integer[]{3}, levelOrder.get(0).toArray(new Integer[levelOrder.get(0).size()]));
        Assertions.assertArrayEquals(new Integer[]{1, 2}, levelOrder.get(1).toArray(new Integer[levelOrder.get(1).size()]));

        levelOrder = binaryTreeTraversal.levelOrder(dataFour);
        Assertions.assertEquals(3, levelOrder.size());
        Assertions.assertArrayEquals(new Integer[]{3,}, levelOrder.get(0).toArray(new Integer[levelOrder.get(0).size()]));
        Assertions.assertArrayEquals(new Integer[]{9, 20}, levelOrder.get(1).toArray(new Integer[levelOrder.get(1).size()]));
        Assertions.assertArrayEquals(new Integer[]{15, 7}, levelOrder.get(2).toArray(new Integer[levelOrder.get(2).size()]));
    }

    @Test
    public void maxDepthPerOrderByRecursion() {
        int maxDepth = binaryTreeTraversal.maxDepthPerOrderByRecursion(dataOne);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByRecursion(dataTow);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByRecursion(dataTow);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByRecursion(dataThree);
        Assertions.assertEquals(2, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByRecursion(dataFour);
        Assertions.assertEquals(3, maxDepth);
    }

    @Test
    public void maxDepthPostOrderByRecursion() {
        int maxDepth = binaryTreeTraversal.maxDepthPostOrderByRecursion(dataOne);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPostOrderByRecursion(dataTow);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPostOrderByRecursion(dataTow);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPostOrderByRecursion(dataThree);
        Assertions.assertEquals(2, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPostOrderByRecursion(dataFour);
        Assertions.assertEquals(3, maxDepth);
    }

    @Test
    public void maxDepthPerOrderByStack() {
        int maxDepth = binaryTreeTraversal.maxDepthPerOrderByStack(dataOne);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByStack(dataTow);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByStack(dataTow);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByStack(dataThree);
        Assertions.assertEquals(2, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByStack(dataFour);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByStack(dataFive);
        Assertions.assertEquals(3, maxDepth);

        maxDepth = binaryTreeTraversal.maxDepthPerOrderByStack(dataSix);
        Assertions.assertEquals(3, maxDepth);
    }

    @Test
    public void isSymmetric() {

         /*
                3
              /   \
             1     1
         */
        TreeNode data1 = new TreeNode(3);
        data1.left = new TreeNode(1);
        data1.right = new TreeNode(1);

        boolean symmetric = binaryTreeTraversal.isSymmetric(dataOne);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetric(dataTow);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetric(dataThree);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetric(dataFour);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetric(dataFive);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetric(dataSix);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetric(data1);
        Assertions.assertEquals(true, symmetric);

        /*
                  3
                 /  \
                9    9
              /  \   /  \
             8   7   7   8
         */
        TreeNode data2 = new TreeNode(3);
        data2.left = new TreeNode(9);
        data2.right = new TreeNode(9);
        data2.right.left = new TreeNode(7);
        data2.right.right = new TreeNode(8);
        data2.left.left = new TreeNode(8);
        data2.left.right = new TreeNode(7);
        symmetric = binaryTreeTraversal.isSymmetric(data2);
        Assertions.assertEquals(true, symmetric);

    }

    @Test
    public void isSymmetricV2() {

         /*
                3
              /   \
             1     1
         */
        TreeNode data1 = new TreeNode(3);
        data1.left = new TreeNode(1);
        data1.right = new TreeNode(1);

        boolean symmetric = binaryTreeTraversal.isSymmetricV2(dataOne);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetricV2(dataTow);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetricV2(dataThree);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetricV2(dataFour);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetricV2(dataFive);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetricV2(dataSix);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.isSymmetricV2(data1);
        Assertions.assertEquals(true, symmetric);

        /*
                  3
                 /  \
                9    9
              /  \   /  \
             8   7   7   8
         */
        TreeNode data2 = new TreeNode(3);
        data2.left = new TreeNode(9);
        data2.right = new TreeNode(9);
        data2.right.left = new TreeNode(7);
        data2.right.right = new TreeNode(8);
        data2.left.left = new TreeNode(8);
        data2.left.right = new TreeNode(7);
        symmetric = binaryTreeTraversal.isSymmetricV2(data2);
        Assertions.assertEquals(true, symmetric);

    }

    @Test
    public void hasPathSum(){

         /*
                3
              /   \
             1     1
         */
        TreeNode data1 = new TreeNode(3);
        data1.left = new TreeNode(1);
        data1.right = new TreeNode(1);

        boolean symmetric = binaryTreeTraversal.hasPathSum(dataOne,6);
        Assertions.assertEquals(true, symmetric);

        symmetric = binaryTreeTraversal.hasPathSum(dataTow,7);
        Assertions.assertEquals(true, symmetric);

        symmetric = binaryTreeTraversal.hasPathSum(data1,3);
        Assertions.assertEquals(false, symmetric);

        symmetric = binaryTreeTraversal.hasPathSum(data1,4);
        Assertions.assertEquals(true, symmetric);

         /*
                3
              /   \
             1     1
         */
        TreeNode data2 = new TreeNode(-2);
        data2.right = new TreeNode(-3);
        symmetric = binaryTreeTraversal.hasPathSum(data2,-5);
        Assertions.assertEquals(true, symmetric);
    }


}
