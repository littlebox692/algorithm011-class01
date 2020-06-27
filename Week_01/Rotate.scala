object Rotate {
    def main(args: Array[String]): Unit = {
        val originArray = Array(1, 2, 4, 5)
        val count = rotate1(originArray, 2)
        originArray.map(ele => println(s"current element is: ${ele}"))

    }

    /**
      * 暴力解法
      *
      * @param nums
      * @param k
      */
    def rotate1(nums: Array[Int], k: Int) = {
        for (i <- 0 until k) {
            for (j <- 0 until nums.length - 1) {
                nums(j) = nums(j) + nums(nums.length - 1)
                nums(nums.length - 1) = nums(j) - nums(nums.length - 1)
                nums(j) = nums(j) - nums(nums.length - 1)
            }
        }
    }

    /**
    * 参考官方例子环状替代
    * @param nums
    * @param k
    */
    def rotate2(nums: Array[Int], k: Int) = {
        val len = nums.length
        val kRemainder = k % len
        var count = 0
        var start = 0
        while (count < nums.length) {
        var current = start
        var prev = nums(start)
        do {
            val next = (current + kRemainder) % len
            nums(next) = nums(next) + prev
            prev = nums(next) - prev
            nums(next) = nums(next) - prev
            current = next
            count = count + 1
        } while (start != current)
        start = start + 1
        }
    }

    /**
    * scala api直接分割数组再拼接
    * @param nums
    * @param k
    * @return
    */
    def rotate3(nums: Array[Int], k: Int) = {
        val (left, right) = nums.splitAt(nums.length - (k % nums.length))
        right ++ left
    }
}