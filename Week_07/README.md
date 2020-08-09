### set与queue的区别与联系
### 红黑树的定义与特性

### Trie模板代码
```java
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}
```
### 并查集代码
```java
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}
```

###二维矩阵中的A*代码
```java
public class AStar {
    public final static int BAR = 1; // 障碍值
    public final static int PATH = 2; // 路径
    public final static int DIRECT_VALUE = 10; // 横竖移动代价
    public final static int OBLIQUE_VALUE = 14; // 斜移动代价
    
    Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
    List<Node> closeList = new ArrayList<Node>();
    
    /**
     * 开始算法
     */
    public void start(MapInfo mapInfo)
    {
        if(mapInfo==null) return;
        // clean
        openList.clear();
        closeList.clear();
        // 开始搜索
        openList.add(mapInfo.start);
        moveNodes(mapInfo);
    }


    /**
     * 移动当前结点
     */
    private void moveNodes(MapInfo mapInfo)
    {
        while (!openList.isEmpty())
        {
            Node current = openList.poll();
            closeList.add(current);
            addNeighborNodeInOpen(mapInfo,current);
            if (isCoordInClose(mapInfo.end.coord))
            {
                drawPath(mapInfo.maps, mapInfo.end);
                break;
            }
        }
    }
    
    /**
     * 在二维数组中绘制路径
     */
    private void drawPath(int[][] maps, Node end)
    {
        if(end==null||maps==null) return;
        System.out.println("总代价：" + end.G);
        while (end != null)
        {
            Coord c = end.coord;
            maps[c.y][c.x] = PATH;
            end = end.parent;
        }
    }


    /**
     * 添加所有邻结点到open表
     */
    private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
    {
        int x = current.coord.x;
        int y = current.coord.y;
        // 左
        addNeighborNodeInOpen(mapInfo,current, x - 1, y, DIRECT_VALUE);
        // 上
        addNeighborNodeInOpen(mapInfo,current, x, y - 1, DIRECT_VALUE);
        // 右
        addNeighborNodeInOpen(mapInfo,current, x + 1, y, DIRECT_VALUE);
        // 下
        addNeighborNodeInOpen(mapInfo,current, x, y + 1, DIRECT_VALUE);
        // 左上
        addNeighborNodeInOpen(mapInfo,current, x - 1, y - 1, OBLIQUE_VALUE);
        // 右上
        addNeighborNodeInOpen(mapInfo,current, x + 1, y - 1, OBLIQUE_VALUE);
        // 右下
        addNeighborNodeInOpen(mapInfo,current, x + 1, y + 1, OBLIQUE_VALUE);
        // 左下
        addNeighborNodeInOpen(mapInfo,current, x - 1, y + 1, OBLIQUE_VALUE);
    }


    /**
     * 添加一个邻结点到open表
     */
    private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int x, int y, int value)
    {
        if (canAddNodeToOpen(mapInfo,x, y))
        {
            Node end=mapInfo.end;
            Coord coord = new Coord(x, y);
            int G = current.G + value; // 计算邻结点的G值
            Node child = findNodeInOpen(coord);
            if (child == null)
            {
                int H=calcH(end.coord,coord); // 计算H值
                if(isEndNode(end.coord,coord))
                {
                    child=end;
                    child.parent=current;
                    child.G=G;
                    child.H=H;
                }
                else
                {
                    child = new Node(coord, current, G, H);
                }
                openList.add(child);
            }
            else if (child.G > G)
            {
                child.G = G;
                child.parent = current;
                openList.add(child);
            }
        }
    }


    /**
     * 从Open列表中查找结点
     */
    private Node findNodeInOpen(Coord coord)
    {
        if (coord == null || openList.isEmpty()) return null;
        for (Node node : openList)
        {
            if (node.coord.equals(coord))
            {
                return node;
            }
        }
        return null;
    }




    /**
     * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
     */
    private int calcH(Coord end,Coord coord)
    {
        return Math.abs(end.x - coord.x)
                + Math.abs(end.y - coord.y);
    }
    
    /**
     * 判断结点是否是最终结点
     */
    private boolean isEndNode(Coord end,Coord coord)
    {
        return coord != null && end.equals(coord);
    }


    /**
     * 判断结点能否放入Open列表
     */
    private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
    {
        // 是否在地图中
        if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
        // 判断是否是不可通过的结点
        if (mapInfo.maps[y][x] == BAR) return false;
        // 判断结点是否存在close表
        if (isCoordInClose(x, y)) return false;


        return true;
    }


    /**
     * 判断坐标是否在close表中
     */
    private boolean isCoordInClose(Coord coord)
    {
        return coord!=null&&isCoordInClose(coord.x, coord.y);
    }


    /**
     * 判断坐标是否在close表中
     */
    private boolean isCoordInClose(int x, int y)
    {
        if (closeList.isEmpty()) return false;
        for (Node node : closeList)
        {
            if (node.coord.x == x && node.coord.y == y)
            {
                return true;
            }
        }
        return false;
    }
}
```
### 双向BFS
```java
public class BidirectionBFS {
	/**
	 * 双向广度优先搜索 - 循环
	 */
	public void twoEndedBfsWithQueue(Object beginNode, Object endNode) {
		Set<Object> meets = new HashSet<>(); // nodes that may meet, will be given usually
		Queue<Object> beginQueue = new LinkedList<>(Collections.singleton(beginNode));
		Queue<Object> endQueue = new LinkedList<>(Collections.singleton(endNode));

		// terminator
		while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
			// always from less to more
			if (beginQueue.size() > endQueue.size()) {
				Queue<Object> swap = beginQueue;
				beginQueue = endQueue;
				endQueue = swap;
			}

			Queue<Object> nextBegin = new LinkedList<>();
			meets.removeAll(beginQueue);
			while (!beginQueue.isEmpty()) {
				Object begin = beginQueue.remove();

				// process
				this._process(begin);

				List<Object> children = this._getChildren(begin);
				for (Object child : children) {
					// pruning
					if (!meets.contains(child)) continue;
					if (endQueue.contains(child)) return; // meet, O(n) while looking up
					nextBegin.add(child);
				}
			}

			// drill down
			beginQueue = nextBegin;
			// reverse state
		}
	}

	/**
	 * 双向广度优先搜索 - 递归
	 */
	public void twoEndedBfsWithRecursion(Object beginNode, Object endNode) {
		Set<Object> meets = new HashSet<>(); // nodes that may meet, will be given usually
		Set<Object> beginSet = new HashSet<>(Collections.singleton(beginNode));
		Set<Object> endSet = new HashSet<>(Collections.singleton(endNode));

		this.bfs(0, beginSet, endSet, meets);
	}

	// bfs recursion
	private int bfs(int level, Set<Object> beginSet, Set<Object> endSet, Set<Object> meets) {
		// terminator
		if (beginSet.size() == 0 || endSet.size() == 0) return -1; // cannot find

		// process
		level++;
		// always from less to more
		if (beginSet.size() > endSet.size()) {
			Set<Object> swap = beginSet;
			beginSet = endSet;
			endSet = swap;
		}
		Set<Object> nextBegin = new HashSet<>();
		meets.removeAll(beginSet);
		for (Object begin : beginSet) {
			// process
			this._process(begin);

			List<Object> children = this._getChildren(begin);
			for (Object child : children) {
				// pruning
				if (!meets.contains(child)) continue;
				if (endSet.contains(child)) return level; // meet
				nextBegin.add(child);
			}
		}
		// drill down
		return this.bfs(level, nextBegin, endSet, meets);
		// reverse state
	}
}
```
### [红黑树](https://www.jianshu.com/p/e136ec79235c)
红黑树是一种含有红黑结点并能自平衡的二叉查找树。它必须满足下面性质：

- 性质1：每个节点要么是黑色，要么是红色。
- 性质2：根节点是黑色。
- 性质3：每个叶子节点（NIL）是黑色。
- 性质4：每个红色结点的两个子结点一定都是黑色。
- 性质5：任意一结点到每个叶子结点的路径都包含数量相同的黑结点。

从性质5又可以推出：
- 性质5.1：如果一个结点存在黑子结点，那么该结点肯定有两个子结点<br>
**值得提醒注意的是，在Java中，叶子结点是为null的结点。**
