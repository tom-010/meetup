tests := List clone 

TestResult := Object clone 
TestResult passed := 0
TestResult failed := 0
TestResult printReport := method(
)

runTests := method(
    green := 0
    red := 0

    tests foreach(i, test, (method 
        
        e := try(
            test at(1) call
            green = green + 1
        ) 
        e catch(Exception,
            "Test: " print
            test at(0) println
            "-----" println
            e error println
            "\n======\n" println
            red = red + 1
        )
    ))

    "Passed: #{green}" interpolate println
    "Failed: #{red}" interpolate println
    "Total: #{green + red}" interpolate println


)

test := method(name, callable, 
    args := call message argsEvaluatedIn(call sender)
    tests append(list(name, args at (1)))
)

/////////////////// Asserts /////////////////////////////////////////

assert := clone Object
assert equal := method(expected, given,
    (expected == given) ifFalse(
        Exception raise("expected: #{expected}\ngiven: #{given}" interpolate)))

assert greaterEqual := method(expected, given,
    (expected >= given) ifFalse(
        Exception raise("expected:\n #{expected} >= #{given}" interpolate)))


/////////////////////////////////////////////////////////////////////
/////////////////// End of testing framework ////////////////////////
/////////////////////////////////////////////////////////////////////


test("some meaningful description here", method(
    assert equal(2, 2)
))

test("some other test", method(
    assert greaterEqual(4, 3)
))

runTests