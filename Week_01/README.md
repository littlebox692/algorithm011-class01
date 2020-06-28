学习笔记
## Queue接口分析(java)
- Queue的接口内容很少，总共只有3对，源码如下：
```java
public interface Queue<E> extends Collection<E> {
    boolean add(E var1);
    boolean offer(E var1);

    E remove();
    E poll();

    E element();
    E peek();
}
```
- 详细对比如下表格：
  
~&nbsp; &nbsp; &nbsp; |抛出异常&nbsp; &nbsp; &nbsp; |返回特殊值(E)
--|:--:|--:
插入|add(e)|offer(e)
移除|remove()|poll()
检查|element()|peek()

以上移除跟检查都会在queue为空的抛出异常，而对于插入操作，add方法还会在queue capacity不足的时候抛出异常。
<br>

## PriorityQueue源码分析(java)
- PriorityQueue继承体系
  ```java
    public class PriorityQueue<E> extends AbstractQueue<E> implements java.io.Serializable {

        public E peek() {
            return (size == 0) ? null : (E) queue[0];
        }

        public E poll() {
            if (size == 0)
                return null;
            int s = --size;
            modCount++;
            E result = (E) queue[0];
            E x = (E) queue[s];
            queue[s] = null;
            if (s != 0)
                siftDown(0, x);
            return result;
        }

        public boolean add(E e) {
            return offer(e);
        }

        public boolean offer(E e) {
            if (e == null)
                throw new NullPointerException();
            modCount++;
            int i = size;
            if (i >= queue.length)
                grow(i + 1);
            size = i + 1;
            if (i == 0)
                queue[0] = e;
            else
                siftUp(i, e);
            return true;
        }
    }

    public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> {
        public E element() {
            E x = peek();
            if (x != null)
                return x;
            else
                throw new NoSuchElementException();
        }

        public E remove() {
            E x = poll();
            if (x != null)
                return x;
            else
                throw new NoSuchElementException();
        }
        ...
    }
  ```
- add方法是通过offer实现的，包装了一次而已，对于add方法做了以下几件事，
  判断元素是否为空，判断queue容量够不够，不够就增加，然后调用siftUp进行堆排序
  对于siftUp会判断是有有自定义的comparator，没有就按照自然规则排序。
  下图仅分析默认排序规则(自定义的比较器一样的)

  ```java
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }
    ...
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
  ```
  将要插入的节点与父节点进行比较，如果更小(基于比较器的比较规则)，就将父节点往下，然后继续向上比较，如果大于等于，就放在当前的位置。

- remove方法会调用poll方法，对了一个抛出异常的处理，pull方法与offer方法正好相反，会调用一个siftDwon的方法
  ```java
    private void siftDownComparable(int k, E x) {
            Comparable<? super E> key = (Comparable<? super E>)x;
            int half = size >>> 1;        // loop while a non-leaf
            while (k < half) {
                int child = (k << 1) + 1; // assume left child is least
                Object c = queue[child];
                int right = child + 1;
                if (right < size &&
                    ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                    c = queue[child = right];
                if (key.compareTo((E) c) <= 0)
                    break;
                queue[k] = c;
                k = child;
            }
            queue[k] = key;
    }
  ```
  poll是从堆顶，也就是queue[0]那个位置取出元素，但这样一来，queue[0]就空出来，我们必须把这个空补上，这个有点难办了，如果是左孩子比较小，那么我们把左孩子放到这个空格里以后，堆就不再是一个完全二叉树了,因为留有空位。这个时候就需要用一个小技巧，**把最后一个元素换到头上，然后使它沉下去**。后面的比较以及堆重新调整的过程就可以顺利的进行下去了。
- element与peek方法就没有什么很特别的，就只是获取queue[0]这个元素


## 第一周学习心得
#### 收获
1. 能通过视频学习到常用的链表，数组，跳表之间的差异以及使用场景，并能针对不同的题目选择不同的数据结构进行解决
2. 对于栈和队列能理解并了解其区别，应用的差异，并能结合视频讲解的题目加深印象 
#### 建议
1. 希望能讲解一下应该怎么去评价自己的代码是否够好，当然可能通常的方式就是分析时间与空间复杂度，但是怎么能比较大概的估算时间复杂度，空间复杂度，这些都没有怎么仔细的讲，只是有个结论。当然本身以数学的角度来看，证明是非常繁复的，但是可以举例子，告诉我们应该怎么去应用已经有的结论来判定自己的代码的时间复杂度。
2. 怎么样将某些题目归类到应该用哪种算法去解决，怎么样学会分析应该用什么算法，就比如课后习题，能不能给我们简单分析一下，而不仅仅只是看答案，记答案。对于实际工作来说，没人会给现成的答案你，需要的是如何学会分析。












