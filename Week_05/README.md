### 第一周
- 数组
  - 查询O(1),删除跟新增O(n)
- 链表(LinkedList)
  - 查询O(n),删除跟新增O(1)
- 跳表增删改查O(log(n))

- 栈<br>
  java Stack source code<br>
  LIFO,查询的时间复杂度O(n),删除跟新增是O(1)<br>
  一般用于DFS实现

- 队列
  java Queue source code
  FIFO,查询的时间复杂度O(n),删除跟新增是O(1)<br>
  一般用于BFS实现

  **PriorityQueue**<br>
  **Deque**<br>
  Deque是Queue的子接口，是双端队列。常用的双端队列有ArrayDeque以及LinkedList

### 第二周
- 哈希表、映射、结合的实现与特性
  - 哈希表(Hash table)，也叫散列表，是根据关键码值(key value)而直接进行访问的数据结构。它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。
  - 这个映射函数叫作散列函数(Hash Function)，存放记录的数组叫作哈希表(或散列表)
  - java常用的有HashSet与HashMap
  hash table的insert search delete都是O(1)的时间复杂度
- 树、二叉树、二叉搜索树(简单理解树是由链表(LinkedList)演化而来)
  - 树的代码定义(二叉树)<br>
  ```
  public class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
          this.val = val;
          this.left = null;
          this.right = null;
      }
  }
  ```
  - 树的遍历
    - 前序(pre-order): 根-左-右
    ```python
    def preorder(self, root):
        if root: 
            self.traverse_path.append(root.val) 
            self.preorder(root.left) 
            self.preorder(root.right)
    ```
    - 中序(in-order): 左-根-右
    ```python
    def preorder(self, root):
        if root: 
            self.preorder(root.left) 
            self.traverse_path.append(root.val) 
            self.preorder(root.right)
    ```
    - 后序(post-order): 左-右-根
    ```python
    def preorder(self, root):
        if root: 
            self.preorder(root.left) 
            self.preorder(root.right)
            self.traverse_path.append(root.val) 
    ```
  - 二叉搜索树(有序二叉树: Ordered Binary Tree; 排序二叉树: Sorted Binary Tree)
    1. 左子树上**所有结点**的值均**小于**它的根结点的值
    2. 右子树上**所有结点**的值均**大于**它的根节点的值
    3. 以此类推: 左右子树也分别为二叉搜索树(这就是重复性)<br>
    Note: 二叉搜索树的中序遍历其实也就是升序遍历<br>
    二叉搜索树的insert search delete时间复杂度都是O(log(n))

- 堆、二叉堆、图<br>
  1. heap: 可以迅速找到一堆数中的最大或者最小的数据结构<br>
    大顶堆(大根堆): 根节点最大<br>
    小顶堆(小根堆): 根节点最小<br>
    常见的Heap有二叉堆、斐波拉契堆等<br>
    e.g(假设是大顶堆):<br>
    find-max: O(1)<br>
    delete-max: O(1)<br>
    insert(create): O(log(n)) or O(1)  ---> 看不同的实现
  2. 二叉堆<br>
   - 特性: a. 是一棵完全树  b. 树中任意节点的值总是 >= 其子节点的值
   - 实现细节
     - 二叉堆一般通过数组来实现
     - 假设**第一个元素**在数组中的索引为0的话，则父子节点的位置关系如下:<br>
     (01) 索引为i的左孩子的索引是(2 * i + 1)  
     (02) 索引为i的右孩子的索引是(2 * i + 2)  
     (03) 索引为i的父节点的索引是floor((i - 1)/2)  
     - insert(O(log(n))): 新元素一律先插入到堆尾，再依次向上调整整个堆的结构，直到根(HeapifyUp)
     - 删除堆顶操作delete max(O(log(n))): 将堆尾元素替换到顶部,再依次向下调整整个堆的结构，直到堆尾(HeapifyDown)
   
  3. 常见的二叉堆(PriorityQueue)
   
### 第三周
- 泛型递归
  ```java
  public void recur(int level, int param) {
      // terminator
      if (level > MAX_VALUE) {
          //process result
          return;
      }
      // process current logic
      process(level, param);
      // drill down 
      recur(level + 1, newParam);
      // restore current status
  }
  ```
- 树的递归(前中后序遍历模板)
- 分治
  ```python
  def divide_conquer(problem, param1, param2, ...):
    # recursion terminator
    if problem is None:
        print_result
        return
    # prepare data
    data = prepare_data(problem)
    subproblems = split_problem(problem, data)
    # conquer
    subresult1 = self.divide_conquer(subproblems[0], p1, ...)
    subresult2 = self.divide_conquer(subproblems[1], p1, ...)
    subresult3 = self.divide_conquer(subproblems[2], p1, ...)
    # process and generate the final result
    result = process_result(subresult1, sub
    result2, subresult3, ...)
    # revert the current level states
  ```
- 回溯

### 第四周
- BFS
  - 非递归
  ```java
  public class TreeNode {
      int val;
      TreeNode left,right;
      TreeNode(int x) {
          val = x
      }
  }
  public List<TreeNode> traversal(TreeNode root) {
      if (root == null) {
          return null;
      }
      return bfs(root);
  }
  // 第一种方式
  private List<TreeNode> bfs(TreeNode root) {
      int curNum = 1;
      int nextNum = 0;
      Queue<TreeNode> queue = new LinkedList<>();
      List<TreeNode> res = new ArrayList<>();
      queue.offer(root);
      while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        res.add(node);
        curNum--;
        if (node.left != null) {
            queue.add(node.left);
            nextNum++;
        }
        if (node.right != null) {
            queue.add(node.right);
            nextNum++;
        }
        if (curNum == 0) {
            curNum = nextNum;
            nextNum = 0;
        }
      }
  }

  // 第二种方式
  private List<TreeNode> bfs2(TreeNode root) {
    Queue<TreeNode> tempQueue = new LinkedList<>();
    List<TreeNode> res = new LinkedList<>();
    if (root == null) {
      return null;
    }
    temQueue.add(root);
    while (!temQueue.isEmpty()) {
      int len = tempQueue.size();
      for (int i = 0; i < len; i++) {
        TreeNode curNode = tempQueue.peek();
        if (curNode.left != null) {
          tempQueue.add(curNode.left)
        }
        if (curNode.right != null) {
          tempQueue.add(curNode.right)
        }
        res.add(tempQueue.poll());
      }
    }
    return res;
  }
  ```
  - 递归
- DFS
  - 递归写法
    ```java
    List<TreeNode> mRes = new ArrayList<>();

    public List<TreeNode> traversal(TreeNode root) {
        dfs(root, mRes);
        return mRes;
    }
    private void dfs(TreeNode root, List<TreeNode> results) {
        if (root == null) {
          return results;
        }
        mRes.add(root);
        dfs(root.left);
        dfs(root.right);
    }
    ```
  - 非递归写法(stack)
  ```java
  private List<TreeNode> dfs2(TreeNode root) {
      List<TreeNode> res = new ArrayList<>();
      Stack<TreeNode> stack = new Stack<>();
      stack.add(root);
      whild (!stack.empty()) {
          TreeNode node = stack.peek();
          res.add(node);
          stack.pop();
          if (node.right != null) {
              stack.push(node.right);
          }
          if (node.left != null) {
              stack.push(node.left);
          }
      }
      return res;
  }
  ```
- 贪心算法
- 二分查找
  // 3 4 5 6 7 8 9 10
  ```java
  public int binarySearch(int[] array, int target) {
    int low = 0;
    int high = a.length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (a[mid] > target)
            high = mid - 1;
        else if (a[mid] < target)
            low = mid + 1;
        else
            return mid;
    }
    return -1;
  }
  ```