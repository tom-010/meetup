class Field {
  constructor(field) {
    this.field = field; 
  }

  _isInField(coord) {
    if(coord.x < 0 || coord.y < 0)
        return false;
    if(coord.x >= this.cols() || coord.y >= this.rows()) {
        return false;
    }
    return true;
  }
  
  isAlive(coordinate2D) {
    if(!this._isInField(coordinate2D)) {
        return false;
    }
    return this.field[coordinate2D.y][coordinate2D.x] === "*";
  }
  
  makeAlive(coordinate2D) {
    this.field[coordinate2D.y][coordinate2D.x] = "*";
  }
  
  kill(coordinate2D) {
    this.field[coordinate2D.y][coordinate2D.x] = ".";
  }

  
  numberOfAliveNeighbours(coordinate2D) {
    let neighbourCoordinates = [
        {x: -1, y: -1},
        {x: -1, y: 0},
        {x: -1, y: 1},

        {x: 0,  y: 1},
        {x: 0,  y: -1},
        
        {x: 1,  y: -1},
        {x: 1,  y: 0},
        {x: 1,  y: 1}]

    return neighbourCoordinates 
        .filter((c) => this.isAlive({
            x: coordinate2D.x+c.x, 
            y: coordinate2D.y+c.y }))
        .map(c => 1)
        .reduce((accumulator, currentValue) => accumulator + currentValue, 0)
  }
    
  cols() {
    if(this.rows(this.field) < 1) {
      return 0;
    }
    return this.field[0].length;
  }
    
  rows() {
    return this.field.length; 
  } 
}

class SerializableField extends Field {
  
  constructor(fieldString) {
    super(SerializableField.deserialize(fieldString));
  }
  
  serialize() {
    return this.field
      .map(row => row.join(''))
      .join("\n");
  }
  
  static deserialize(fieldString) {
    return fieldString
      .split("\n")
      .map(line => line.split(''));
  }
}

module.exports = [Field, SerializableField]


