### 位运算

#### 基本运算
移出去的补0 
左移: << <br>
示例: 0011 => 0110 <br>
右移: << <br>
示例: 0110 => 0011 <br>

常用的位运算: <br>

按位或: | <br>
示例: 0011 <br>
        -----=> 1011 <br>
     1011 <br>  

按位与: & <br>
示例: 0011 <br>
        -----=> 0011 <br>
     1011 <br>  
按位取反: ~ <br>
示例: 0011 => 1100 <br>

按位异或(相同为0不同为1) : ^ <br>
示例: 0011 <br>
        -----=> 1000 <br>
     1011 <br> 
#### 异或操作的一些特点
1. x ^ 0 = x
2. x ^ 1s = ~x //注意1s = ~0，即1s代表的是全1
3. x ^ (~x) = 1s
4. x ^ x = 0
5. c = a ^b => a ^ c = b, b ^ c = a //交换两个数
6. a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c // associative

#### 指定位置的位运算
1. 将x最右边的n位清零: x & (~0 << n)
2. 获取x的第n位值(0或者1): (x >> n) & 1
3. 获取x的第n位的幂值: x & (1 << n)
4. 仅将第n位置为1: x | (1 << n)
5. 仅将第n位置为0: x & (~(1 << n))
6. 将x最高位至第n位(含)清零: x & ((1 << n) - 1)
7. 判断奇数偶数: (x & 1) == 1(奇数); (x & 1) == 0(偶数)
8. x >> 1 => x / 2
9. x = x & (x - 1) 清零最低位的1
10. x & -x => 得到最低位的1
11. x & ~x => 0 

### 布隆过滤器
### 归并排序
```java
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return;
    int mid = (left + right) >> 1; 

    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid)   temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(a, start1, b, start2, length)
    }
```
### 快排
```java
public static void quickSort(int[] array, int begin, int end) {
    if (end <= begin) return;
    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
}
static int partition(int[] a, int begin, int end) {
    // pivot: 标杆位置，counter: 小于pivot的元素的个数
    int pivot = end, counter = begin;
    for (int i = begin; i < end; i++) {
        if (a[i] < a[pivot]) {
            int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
            counter++;
        }
    }
    int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
    return counter;
}
```
