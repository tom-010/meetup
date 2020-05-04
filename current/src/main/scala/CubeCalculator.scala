object CubeCalculator extends App {
  def cube(x: Int) = {
    x * x * x
  }
}

object Cell {
  def evolve(state: Boolean, neighbourCount: Int) = {
    if (state && neighbourCount == 2 || neighbourCount == 3) {
      true
    } else {
      false
    }
  } 
}

class InvalidBoard extends RuntimeException {
  
}

object Board {
  def create(rows: Int, columns: Int) = {
    Array.ofDim[Boolean](rows, columns)
  }

  def rows(board: Array[Array[Boolean]]) = {
    board.size
  }

  def cols(board: Array[Array[Boolean]]) = {
    if (rows(board) == 0)
      0
    else
      board(0).size
  }

  def evolve(board: Array[Array[Boolean]]) = {
    val newBoard = clone(board)
    val rowRange = 0 until board.size 
    val columnRange = 0 until board(0).size

    rowRange.foreach { r =>
      columnRange.foreach { c => 
        newBoard(r)(c) = Cell.evolve(newBoard(r)(c), countNeighbours(newBoard, r, c))
      }
    }
    newBoard
  }

  def makeAlive(board: Array[Array[Boolean]], row: Int, column: Int) = {
    val newBoard = clone(board)
    newBoard(row)(column) = true;
    newBoard
  }

  def isAlive(board: Array[Array[Boolean]], row: Int, column: Int) = {
    board(row)(column)  
  }

  def countNeighbours(board: Array[Array[Boolean]], row: Int, column: Int) = {
    val neighbours = getNeigbours(board, row, column) 
    neighbours.foldLeft(0)((res, cell) => if(cell) res + 1 else res)
    // val neighbours = getNeighbours(board: Array[Array[Boolean]], row, column) 
  }

  def getNeigbours(board: Array[Array[Boolean]], row: Int, column: Int) = {
    var result: Array[Boolean] = Array() 
    val rowRange = row - 1 until (row + 2)
    val columnRange = column -1 until (column + 2)

    rowRange.foreach { r =>
      columnRange.foreach { c => 
        if (isInBoard(board, r, c) && !(r == row && c == column)) {
          result = result :+ board(r)(c)
        }
      }
    }
    result
  }

  def isInBoard(board: Array[Array[Boolean]], row: Int, column: Int) = {
    row >= 0 && row < board.size && column >= 0 && column < board(0).size
  }


  def clone(board: Array[Array[Boolean]]) = {
    board.map(row => row.map(column => column))
  }

  def parse(boardString: String) = {
    var board: Array[Array[Boolean]] = Array()
    var rows: Array[String] = Array()
    val pattern = "[^\\.\\*\\n]".r
    pattern.findFirstMatchIn(boardString) match {
      case Some(_) => throw new InvalidBoard()
      case None => if (boardString == "") {
        board = create(0,0)
      } else {
        rows =  boardString.split('\n')
        board = create(rows.size, rows(0).size)
      }
    }
    initializeBoard(board, rows)
    board
  }

  def toString(board: Array[Array[Boolean]]) = {
    var result = ""
    if (board.size == 0) {
      result
    } else {
      (0 until board.size).foreach { r => 
        (0 until board(0).size).foreach { c => 
          result = result + (if(board(r)(c)) "*" else ".")
        }
        result = result + "\n"
      }
    }
    result
  }

  def initializeBoard(board: Array[Array[Boolean]], rows: Array[String]) = {
    (0 until board.size).foreach { r => 
      (0 until board(0).size).foreach { c => 
        board(r)(c) = if(rows(r)(c) == '*') true else false 
      }
    }
  }

  
  def equal(board: Array[Array[Boolean]], other: Array[Array[Boolean]]) = {
    true
  }

  // Map(0 -> "zero", 1 -> "one");
  // 
}