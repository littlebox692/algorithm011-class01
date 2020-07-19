object LemonadeChange {
  def main(args: Array[String]): Unit = {
      val payments = Array(5, 5, 10, 20)
      lemonadeChange(payments)
  }

  def lemonadeChange(bills: Array[Int]): Boolean = {
    var five = 0
    var ten = 0
    for (bill <- bills) {
      if (single == 5) {
        five = five + 1
      } else if (bill == 10) {
        five = five - 1
        ten = ten + 1
      } else {
        if (ten == 0) {
          five = five - 3
        } else {
          five = five - 1
          ten = ten -1
        }
      }
      if (five < 0) {
        return false
      }
    }
    return true
  }

}
