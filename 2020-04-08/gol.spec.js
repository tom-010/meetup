const [nextStep, fieldOf] = require('./gol')


describe("Conway's game of life", () => {

    test("field of death cells remain the same", () => {
        const field = fieldOf(
            `...
             ...
             ...`);

        expect(nextStep(field)).toEqual(field);
    });

    
    test("one alive cell in the middle, all dead", () => {
        const field = fieldOf(
            `...
             .*.
             ...`);
        const expectedField = fieldOf(
            `...
             ...
             ...`);
        expect(nextStep(field).serialize()).toEqual(expectedField.serialize());
    });

    test("four alive cells in the top left corner remain alive", () => {
        const field = fieldOf(
            `**.
             **.
             ...`);
        expect(nextStep(field)).toEqual(field);
    });

    test("four alive cells in the right bottom corner remain alive", () => {
        const field = fieldOf(
            `...
             .**
             .**`);
        expect(nextStep(field)).toEqual(field);
    });


    test("born", () => {

        const field = fieldOf(
            `...
             .**
             .*.`);
        const expectedField = fieldOf(
            `...
             .**
             .**`);
        expect(nextStep(field).serialize()).toEqual(expectedField.serialize());


    });


    /* 
    IDEAS

    - 3x4
    - 2 alive 2 dead
    - test("entiere field of live cells would be all dead")

    */
    
    

    
});
















describe("tmp", () => {
    test("some expects", () => {
        expect(1).toEqual(1);
        expect("abc").toEqual("abc");
    });

    test("multiline string", () => {
        expect(
            "....\n"+
            "....\n"+
            "...."
        ).toEqual(ml(
            `....
             ....
             ....`
            ));
    });
})

function ml(str) {
    return str.split(" ").join("");
}
