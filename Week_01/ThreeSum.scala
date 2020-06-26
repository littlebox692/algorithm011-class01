object ThreeSum {
    /**
      * 三数求和
      *
      * @param args
      */
    def main(args: Array[String]): Unit = {
        val result: List[List[Int]] = threeSum(List(3, 2, 1).sorted)
    }

    def threeSum(nums: Array[Int]): List[List[Int]] = {
        val sortedArray: Array[Int] = nums.sorted
        for (k <- 0 until (sortedArray.length - 2)) {
            //较小数指针
            var i = k + 1
            //较大数指针
            val j = sortedArray.length - 1
            // temp sum result = 
            val tempSum = nums[k] + nums[i] + nums[j]
            if (tempSum < 0) {
                val tempI = i + 1
                while(i < j && nums[i] == nums[tempI] ) {
                    i = tempI
                };
            } else if (tempSum > 0) {

            }

        }

    }

    
}