import org.scalatest.FunSuite;


class CubeCalculatorTest extends org.scalatest.FunSuite {
  test("CubeCalculator.cube") {
    assert(CubeCalculator.cube(3) === 27)
  }

  test("dead cell, no neighbour, stays dead") {

    assert(Cell.evolve(false, 0) === false)
  }

  test("alive cell, no neighbour, dies") {
    assert(Cell.evolve(true, 0) === false)
  }

  test("alive cell, one neigbour, dies") {
    assert(Cell.evolve(true, 1) === false)
  }  

  test("alive cell, two neigours, stays alive") {
    assert(Cell.evolve(true, 2) === true)
  }

  test("dead cell, two neigbours, stays dead") {
    assert(Cell.evolve(false, 2) === false)
  }

  test("Any live cell with more than three live neighbours dies, as if by overcrowding") {
    assert(Cell.evolve(true, 4) === false)
  }

  test("Any live cell with two live neighbours lives on to the next generation.") {
    assert(Cell.evolve(true, 2) === true)
  }

  test("Any live cell with three live neighbours lives on to the next generation.") {
    assert(Cell.evolve(true, 3) === true)
  }

  test("Any dead cell with exactly three live neighbours becomes a live cell.") {
    assert(Cell.evolve(false, 3) === true)
  }
}

class NeigbourCountingTests extends org.scalatest.FunSuite {
  
  test("init") {
    val board = Board.create(5, 5)
    assert(board.size === 5)
    assert(board(0).size === 5)
  }

  test("default board is all dead") {
    val board = Board.create(5, 5)
    board.foreach { row => 
      row.foreach { cell => 
        assert(cell == false)
      }
    }
  }

  test("one living cell in the center") {
    var board = Board.create(5, 5)
    board = Board.makeAlive(board, 2,2)
    assert(Board.isAlive(board, 2,2) === true)
  }

  test("board is immuatable in makeAlive") {
    var board = Board.create(5, 5)
    Board.makeAlive(board, 2,2)
    assert(Board.isAlive(board, 2,2) === false)
  }

  test("one live cell with no neigbours, couting") {
    var board = Board.create(5, 5)
    board = Board.makeAlive(board, 2,2)
    assert(Board.countNeighbours(board, 2,2) == 0)
  }

  test("one neigbour") {
    var board = Board.create(5, 5)
    board = Board.makeAlive(board, 2,1)
    board = Board.makeAlive(board, 2,2)
    assert(Board.countNeighbours(board, 2,2) == 1)
  }

  test("all neighbours in center") {
    var board = Board.create(5, 5)
    board = Board.makeAlive(board, 2,2)
    
    board = Board.makeAlive(board, 1,1)
    board = Board.makeAlive(board, 2,1)
    board = Board.makeAlive(board, 1,2)
    board = Board.makeAlive(board, 3,1)
    board = Board.makeAlive(board, 1,3)
    board = Board.makeAlive(board, 3,2)
    board = Board.makeAlive(board, 2,3)
    board = Board.makeAlive(board, 3,3) 

    assert(Board.countNeighbours(board, 2,2) == (3*3-1))
  }

  test("asking for out of boundary") {
    var board = Board.create(5, 5)
    assert(Board.countNeighbours(board, -10, -20) === 0)
  }
}

class MoveTests extends org.scalatest.FunSuite {
  test("integration") {
    var board = Board.create(5, 5)
    board = Board.makeAlive(board, 2, 2)
    board = Board.makeAlive(board, 1, 2)
    board = Board.makeAlive(board, 3, 2)
    board = Board.evolve(board)
    assert(Board.isAlive(board, 2, 2) === false)
    assert(Board.isAlive(board, 1, 2) === false)
  }
}

class Parsing extends org.scalatest.FunSuite {

  test("0 board") {
    var board = Board.parse("")
    assert(Board.rows(board) === 0)
    assert(Board.cols(board) === 0)
  }

  test("all dead") {
    var board = Board.parse("...\n...\n...\n")
    assert(Board.rows(board) === 3)
    assert(Board.cols(board) === 3)
  }

  test("bad input") {
    assertThrows[InvalidBoard] { // Result type: Assertion
      var board = Board.parse("abcdadsf\n.asdfasdf.\n.asdf\n")
    }
  }

  test("one is alive") {
    var board = Board.parse("...\n.*.\n..*\n")
    assert(Board.isAlive(board, 1, 1) === true)
    assert(Board.isAlive(board, 2, 2) === true)
  }

}

class PrintingTest extends org.scalatest.FunSuite {
  test("print empty") {
    var board = Board.create(0,0)
    assert(Board.toString(board) === "")
  }

  test("all dead") {
    var board = Board.create(3,3)
    assert(Board.toString(board) === "...\n...\n...\n")
  }

  test("some alive") {
    var board = Board.create(3,3)
    board = Board.makeAlive(board, 1, 1)
    board = Board.makeAlive(board, 2, 2)
    println("here") 
    println(Board.toString(board))
    assert(Board.toString(board) === "...\n.*.\n..*\n")
  }
}
