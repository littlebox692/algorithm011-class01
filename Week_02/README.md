##### 本周总结
1. 对于hash
   hash表，hash函数，冲突处理
2. 树
   树的定义，种类，遍历方式
3. heap的作用及常见的heap

### 自我理解
*对于视频中的内容不做太多的总结，仅就题目遇到的一个知识点总结*

**morris遍历**

1. 理解morris遍历，需要先了解下线索二叉树，知道predecessor以及seccessor是如何来定义的。[线索二叉树](https://www.bilibili.com/video/av328072532/)，这个视频以中序线索二叉树为例进行了讲解。简单来说就是将空指针利用起来，遍历过程中记录当前节点的前继节点，以及后继节点，即二叉树的线索化。

2. morris遍历(中序)定义(与线索二叉树的区别，个人理解，并没有记录后继节点)：

> 1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
> 
> 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
> 
>    a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
> 
>    b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
> 
> 3. 重复以上1、2直到当前节点为空。

**以下图为例,中序遍历结果: [1, 2, 3, 4, 5, 6, 8, 9, 7]**

   ![morris](/Week_02/morris.jpg)

   java代码实现：
   ```
   class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
   ```



