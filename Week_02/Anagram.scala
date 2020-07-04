object Anagram {
    def main(args: Array[String]): Unit = {
        val s = "anagram"
        val t = "nagaram"
        println(s"result is: ${isAnagram2(s, t)}")
    }


    /**
      * 暴力求解
      *
      * @param s
      * @param t
      * @return
      */
    def isAnagram(s: String, t: String): Boolean = {
        val sCharArray = s.toCharArray()
        val tCharArray = t.toCharArray()
        val sStringResult = sCharArray.sorted.foldLeft("")((result, currentString) => result + currentString)
        val tStringResult = tCharArray.sorted.foldLeft("")((result, currentString) => result + currentString)
        sStringResult.equals(tStringResult)
    }

    /**
      * 新建一个包含26个字母size的数组
      *
      * @param s
      * @param t
      * @return
      */
    def isAnagram2(s: String, t: String): Boolean = {
        if (s.length() != t.length()) {
            return false
        }
        val asciiTable = Array.ofDim[Int](26)
        for (i <- 0 until s.length()) {
            val tempDValue = s.charAt(i) - 'a'
            asciiTable(tempDValue) = asciiTable(tempDValue) + 1
        }
        for (i <- 0 until t.length()) {
            val tempDValue = t.charAt(i) - 'a'
            asciiTable(tempDValue) = asciiTable(tempDValue) - 1
            if (asciiTable(tempDValue) < 0) {
                return false
            }
        }
        true
    }
}
