#!/bin/bash

# notes
# $1 includes the command to run
# ${@:2} are all arguments to this script except the first -> pass trough
# inotifywait -r causes warning. Do not display

while true
do
    clear
    $1 ${@:2} && echo "everything looks good here - have a nice day ;-)" && echo ""
    inotifywait -r -e modify . > /dev/null 2> /dev/null
done