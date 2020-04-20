const [Field, SerializableField] = require('./field')

function fieldOf(fieldString) {
    return new SerializableField(ml(fieldString));
}

describe("The field", () => {
    const fieldString = ml(
        `*...
         .*..
         ..*.
         ...*`
    )

    test("serialize/deserialize", () => {
        expect(new SerializableField(fieldString).serialize())
            .toEqual(fieldString);
    });

    test("test alivenesss", () => {
        const field = new SerializableField(fieldString);
        expect(field.isAlive({x: 0, y: 0})).toBeTruthy();
        expect(field.isAlive({x: 1, y: 0})).toBeFalsy();
    })

    test("killing", () => {
        const field = new SerializableField(fieldString);
        field.kill({x: 0, y: 0});
        expect(field.isAlive({x: 0, y: 0})).toBeFalsy();
    })

    test("make alive", () => {
        const field = new SerializableField(fieldString);
        field.makeAlive({x: 1, y: 0});
        expect(field.isAlive({x: 1, y: 0})).toBeTruthy();
    })

    test("size of the board", () => {
        const field = new SerializableField(fieldString);
        expect(field.cols()).toEqual(4);
        expect(field.rows()).toEqual(4);
    });

    test("cells surrounded by dead cells has zero alive neighbours", () => {
        const field = fieldOf(
            `...
             .*.
             ...`);
        expect(field.numberOfAliveNeighbours({x: 1, y: 1}))
            .toEqual(0);
    });

    test("one neighbour is alive", () => {
        const field = fieldOf(
            `...
             **.
             ...`);
        expect(field.numberOfAliveNeighbours({x: 1, y: 1}))
             .toEqual(1);
    });

    test("multiple neighbours is alive", () => {
        const field = fieldOf(
            `*..
             **.
             *.*`);
        expect(field.numberOfAliveNeighbours({x: 1, y: 1}))
             .toEqual(4);
    });

    test("isAlive outside grid is false", () => {
        const field = fieldOf(fieldString);
        expect(field.isAlive({x: 20, y: 20})).toBeFalsy();
    });

    test("multiple neighbours is alive", () => {
        const field = fieldOf(
            `...
             **.
             ...`);
        expect(field.numberOfAliveNeighbours({x: 0, y: 1}))
             .toEqual(1);
    });


    /* Language
    Field: 2D arrangement of Cells
    Cell is Dead or Alive
    Outside Cells are dead
    */
});


function ml(str) {
    return str.split(" ").join("");
}
