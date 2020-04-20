const [Field, SerializableField] = require('./field')

// Dimity
// Zoltan
// Eli
// David
// Deniz
// Marawan



function fieldOf(fieldString) {
    return new SerializableField(ml(fieldString));
}

function clone(field) {
    return new SerializableField(field.serialize())
}

function nextStep(field) {
    const res = clone(field);

    for(let x=0; x<field.cols(); x++) {
        for(y=0; y<field.rows(); y++) {
            const cell = {x: x, y: y};
            let n = field.numberOfAliveNeighbours(cell);
            if(n < 2)  
                res.kill(cell);
            if(n == 3)
                res.makeAlive(cell)
        }
    }
    return res;

}

function ml(str) {
    return str.split(" ").join("");
}


module.exports = [nextStep, fieldOf]