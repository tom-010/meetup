actor1 := Object clone
actor2 := Object clone
actor3 := Object clone

actor1 := Object clone
actor1 greeting := "hello2"
actor1 start := method(
    loop(
        greeting println
        wait(1)
    )
    wait(2); 
    println("slowly")
)

actor1 @@start


wait(3)

// actor1 @@start

# actor3 @@start method(actor2 @@start)

# actor1 @@start ; actor2 @@start

// wait(3) 


// actor2 start := method(
//     wait(1); 
//     writeln("quickly")
// )

// actor3 start := method(fn,
//     wait(1);
//     fn();