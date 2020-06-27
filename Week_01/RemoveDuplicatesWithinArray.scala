object RemoveDuplicatesWithinArray {
    def main(args: Array[String]): Unit = {
        val originArray = Array(1, 2, 3, 3, 3, 4, 4, 5, 5, 6)
        val count = removeDuplicates(originArray)
        originArray.map(ele => println(s"final array's size is: ${count}, and current element is: ${ele}"))
    }

    def removeDuplicates(originArray: Array[Int]): Int = {
        def operation = (tuple: (Int, Int), current: Int) => {
            val (uniqueCount, previous) = tuple
            if (current == previous) {
                (uniqueCount, current)
            } else {
                originArray(uniqueCount) = current
                (uniqueCount + 1, current)
            }
        }
        if (originArray.isEmpty) {
            0
        } else {
            originArray.foldLeft((1, originArray(0)))(operation)._1
        }
    }
}
