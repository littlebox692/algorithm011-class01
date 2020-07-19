object MineSweeper {

  def updateBoard(board: Array[Array[Char]], click: Array[Int]): Array[Array[Char]] = {
    val dirs = Array(Array(1, 0), Array(1, -1), Array(0, -1), Array(-1, -1),
      Array(-1, 0), Array(-1, 1), Array(0, 1), Array(1, 1))
    val row = click(0)
    val col = click(1)
    // if 'M' or 'X'
    if (board(row)(col) == 'M' || board(row)(col) == 'X') {
      board(row)(col) = 'X'
      return board
    }
    // if mines around
    var nums = 0
    for (cell <- dirs) {
      val newRow = cell(0) + row
      val newCol = cell(1) + col
      if (newRow >= 0 && newCol >= 0 && newRow < board.length && newCol < board(0).length && board(newRow)(newCol) == 'M') {
        nums = nums + 1
      }
    }
    if (nums > 0) {
      board(row)(col) = (nums + '0').asInstanceOf[Char]
      return board
    }
    // no mines around
    board(row)(col) = 'B'
    for (cell <- dirs) {
      val newRow = cell(0) + row
      val newCol = cell(1) + col
      if (newRow >= 0 && newCol >= 0 && newRow < board.length && newCol < board(0).length && board(newRow)(newCol) == 'E') {
        updateBoard(board, Array(newRow, newCol))
      }
    }
    return board
  }
}
