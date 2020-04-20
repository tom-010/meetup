class Board():

    def __init__(self):
        self._player_position = (0,0)

    def print(self):
        arr = [
        "????????",
        "????????",
        "????????",
        "????????",
        "????????",
        "????????",
        "????????",
        "????????"]
        arr[-1] = 'O' + arr[-1][1:]
        return "\n"+ "\n".join(arr)+"\n"
        

    def player_position(self):
        return self._player_position

    def move_up(self):
        self._player_position = (
            self._player_position[0],
            self._player_position[1]+1)

    def move_right(self):
        self._player_position = (
            self._player_position[0]+1, 
            self._player_position[1])
    
    def move_down(self):
        self._player_position = (
            self._player_position[0],
            self._player_position[1]-1)

    def move_left(self):
        self._player_position = (
            self._player_position[0]-1, 
            self._player_position[1])


# Milestone
# =========
# - movement: being able to move on a board
# - board: as datastructure
# - displaying the current state of the board (outside in)


######################################
# split into cenceptual features
# - generating a random mine-field=board? -> can be hard coded in the beginning
#   - board rejection
# - counting remaining lives
# - checking the win-condition
# - start with movement only
# - mine