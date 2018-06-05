#!/bin/bash
find src/avaj/hangar/*.java src/avaj/main/*.java src/avaj/weatherSettings/*.java > sources.txt
javac -sourcepath ./src -d . @sources.txt
echo "Done"