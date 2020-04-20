
from prod import Board

# Dimity
# Christian
# Koushik
# David
# Christoph

# UI
def test_can_print_board_with_init_player():
    board = Board()
    assert board.print() == \
"""
????????
????????
????????
????????
????????
????????
????????
O???????
"""
     

# option 1: print with trace
# option 2: print position without trace?
# option 3: just refactor (empty trace)

# def test_can_print_board_with_player_moved_up():
#     board = Board()
#     board.move_up()
#     assert board.print() == """
#         ????????
#         ????????
#         ????????
#         ????????
#         ????????
#         ????????
#         O???????
#         o???????
#         """

def test_can_create_board_with_player():
    board = Board()
    assert board.player_position() == (0, 0)

def test_player_can_move_up():
    board = Board()
    board.move_up()
    assert board.player_position() == (0, 1)

def test_player_can_move_up_twice():
    board = Board()
    board.move_up()
    board.move_up()
    assert board.player_position() == (0, 2)


def test_player_can_move_right():
    board = Board()
    board.move_right()
    assert board.player_position() == (1, 0)
 
def test_complex_movement_without_boder_condition():
    board = Board()

    board.move_up()    
    board.move_right()

    assert board.player_position() == (1, 1)

    board.move_down()
    assert board.player_position() == (1, 0)

    board.move_left()
    assert board.player_position() == (0, 0)


# TEST-LIST
# =========
# - display init board (without player)
# - show init position (with player) - 'O' current position
# - top / down / left / right (change of pos of player)
# - stay within the bounds of the board
# ---------

# Facts
# -----
# - 8x8 board
# - '?' unknown field
# - '*' mine
# - 'O' current position
# - 'o' known field


# Your task is to create a mine-sweeper style game.
# You start at the bottom of an 8x8 grid with 5 lives.
# You must move to the top whilst trying to avoid the hidden mines.
# You move up/down/left/right each turn.
# If you step on a mine:
#   o) the mine is revealed and shown as '*'
#   o) you lose a life
#   o) you do not move into the mine's square
#   o) the mine remains explosive
# Your current position is shown as 'O'
# Squares you have previously stepped on are shown as 'o'
# If you reach the top of the grid you win and the game ends.
# If you run out of lives you lose and the game ends.
# If you attempt to move off the grid:
#   o) you stay in the same square
#   o) you do not lose a life
# A parameter holds the likelihood each square contains a mine:
#   o) this parameter ranges from 0 (inclusive) to 1 (exclusive)
#   o) a value of 0 means no square contains a mine
#   o) a value of 0.5 means a 50% chance any square is mined
#   o) reject generated grids with no mine-free path to the top
#   o) in other words, each game must be winnable
# Your initial starting position at the bottom:
#   o) is randomly chosen
#   o) does not contain a mine
