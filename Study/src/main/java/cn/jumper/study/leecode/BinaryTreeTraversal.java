package cn.jumper.study.leecode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Jumper
 * 2020/1/11
 */
public class BinaryTreeTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorderResult = new LinkedList<>();
        if (root == null) {
            return preorderResult;
        }

        Deque<TreeNode> treeNodeDeque = new LinkedList<>();
        treeNodeDeque.addLast(root);

        while (!treeNodeDeque.isEmpty()) {
            TreeNode currentTreeNode = treeNodeDeque.removeLast();
            preorderResult.add(currentTreeNode.val);
            if (currentTreeNode.right != null) {
                treeNodeDeque.offer(currentTreeNode.right);
            }
            if (currentTreeNode.left != null) {
                treeNodeDeque.offer(currentTreeNode.left);
            }
        }
        return preorderResult;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderResult = new LinkedList<>();
        if (root == null) {
            return inorderResult;
        }
        Deque<TreeNode> treeNodeDeque = new LinkedList<>();
        TreeNode currentNode = root;
        do {
            if (currentNode != null) {
                treeNodeDeque.offer(currentNode);
                currentNode = currentNode.left;
                continue;
            }
            currentNode = treeNodeDeque.removeLast();
            inorderResult.add(currentNode.val);
            currentNode = currentNode.right;
        } while (!treeNodeDeque.isEmpty() || currentNode != null);
        return inorderResult;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> inorderResult = new LinkedList<>();
        if (root == null) {
            return inorderResult;
        }
        Deque<TreeNode> treeNodeDeque = new LinkedList<>();
        TreeNode currentNode = root;
        while (currentNode != null || !treeNodeDeque.isEmpty()) {
            while (currentNode != null) {
                treeNodeDeque.offer(currentNode);
                currentNode = currentNode.left != null ? currentNode.left : currentNode.right;
            }
            currentNode = treeNodeDeque.removeLast();
            TreeNode parentTreeNode = treeNodeDeque.peekLast();
            inorderResult.add(currentNode.val);
            if (parentTreeNode != null && parentTreeNode.left != null && parentTreeNode.left == currentNode) {
                currentNode = parentTreeNode.right;
            } else {
                currentNode = null;
            }
        }
        return inorderResult;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new LinkedList<>();
        if (root == null) {
            return results;
        }
        Queue<TreeNode> treeNodesQueue = new LinkedList<>();
        treeNodesQueue.add(root);

        while (!treeNodesQueue.isEmpty()) {
            List<Integer> result = new LinkedList<>();
            int size = treeNodesQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = treeNodesQueue.poll();
                if (poll.left != null) {
                    treeNodesQueue.offer(poll.left);
                }
                if (poll.right != null) {
                    treeNodesQueue.offer(poll.right);
                }
                result.add(poll.val);
            }
            results.add(result);
        }
        return results;
    }

    public int maxDepthPerOrderByRecursion(TreeNode root) {
        if (root != null) {
            int leftDepth = maxDepthPerOrderByRecursion(root.left);
            int rightDepth = maxDepthPerOrderByRecursion(root.right);
            return leftDepth >= rightDepth ? leftDepth + 1 : rightDepth + 1;
        }
        return 0;
    }

    private void maxDepthPerOrderByRecursionHelper(TreeNode node, int depth) {
        if (node != null) {
            ++depth;
            if (node.left == null && node.right == null) {
                maxDepth = maxDepth > depth ? maxDepth : depth;
                return;
            }
            maxDepthPerOrderByRecursionHelper(node.left, depth);
            maxDepthPerOrderByRecursionHelper(node.right, depth);
        }
    }

    private static int maxDepth = 0;

    public int maxDepthPostOrderByRecursion(TreeNode root) {
        maxDepth = 0;
        maxDepthPerOrderByRecursionHelper(root, 0);
        return maxDepth;
    }

    public int maxDepthPerOrderByStack(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(root);
        int depth = 0;
        while (!treeNodeQueue.isEmpty()) {
            int nodeCountCurrentLevel = treeNodeQueue.size();
            ++depth;
            while (nodeCountCurrentLevel > 0) {
                TreeNode poll = treeNodeQueue.poll();
                --nodeCountCurrentLevel;
                if (poll.left != null) {
                    treeNodeQueue.offer(poll.left);
                }
                if (poll.right != null) {
                    treeNodeQueue.offer(poll.right);
                }
            }
        }

        return depth;
    }

    public int maxDepthPostOrderByStack(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(root);

        while (!treeNodeQueue.isEmpty()) {
            TreeNode peek = treeNodeQueue.peek();
            if (peek.right != null || peek.left != null) {
                treeNodeQueue.offer(peek.left == null ? peek.right : peek.left);
                continue;
            }


        }

        return 0;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> treeNodeDeque = new LinkedList<>();
        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left != null && root.right != null && root.left.val == root.right.val) {
            treeNodeDeque.offerLast(root.left);
            treeNodeDeque.offerLast(root.right);
        } else {
            return false;
        }

        while (!treeNodeDeque.isEmpty()) {
            TreeNode poolFirst = treeNodeDeque.pollFirst();
            TreeNode pollLast = treeNodeDeque.pollLast();

            if ((poolFirst.left != null && poolFirst.right != null && pollLast.left != null && pollLast.right != null && poolFirst.left.val == pollLast.right.val && poolFirst.right.val == pollLast.left.val) ||
                    (poolFirst.left != null && poolFirst.right == null && pollLast.left == null && pollLast.right != null && poolFirst.left.val == pollLast.right.val) ||
                    (poolFirst.left == null && poolFirst.right == null && pollLast.left == null && pollLast.right == null) ||
                    (poolFirst.left == null && poolFirst.right != null && pollLast.left != null && pollLast.right == null && poolFirst.right.val == pollLast.left.val)) {
                if (poolFirst.left != null) {
                    int index = treeNodeDeque.size() == 0 ? 0 : treeNodeDeque.size() / 2;
                    treeNodeDeque.add(index, pollLast.right);
                    treeNodeDeque.add(index, poolFirst.left);
                }
                if (poolFirst.right != null) {
                    int index = treeNodeDeque.size() == 0 ? 0 : treeNodeDeque.size() / 2;
                    treeNodeDeque.add(index, pollLast.left);
                    treeNodeDeque.add(index, poolFirst.right);
                }
            } else {
                return false;
            }

        }
        return true;
    }

    public boolean isSymmetricV2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right) && isSymmetricHelper(root.right, root.left);
    }

    public boolean isSymmetricHelper(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        } else if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null)) {
            return false;
        } else {
            return leftNode.val != rightNode.val ? false :
                    isSymmetricHelper(leftNode.left, rightNode.right) && isSymmetricHelper(leftNode.right, rightNode.left);
        }
    }


    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> matchedChain = new LinkedList<>();
        Deque<TreeNode> treeNodeQueue = new LinkedList<>();
        int currentSum = 0;
        treeNodeQueue.add(root);
        while (!treeNodeQueue.isEmpty()) {
            TreeNode poll = treeNodeQueue.pollLast();
            TreeNode peek = matchedChain.peekLast();
            while (peek != null && poll != peek.left && poll != peek.right) {
                TreeNode treeNode = matchedChain.removeLast();
                currentSum-=treeNode.val;
                peek = matchedChain.peekLast();
            }
            matchedChain.offerLast(poll);
            currentSum+=poll.val;
//            int tempSum = matchedChain.stream().mapToInt(treeNode -> treeNode.val).sum();
            if (currentSum == sum && poll.left == null && poll.right == null) {
                return true;
            }
            if (poll.left != null) {
                treeNodeQueue.offer(poll.left);
            }
            if (poll.right != null) {
                treeNodeQueue.offer(poll.right);
            }
        }
        return false;
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "val:" + val;
    }
}
