#!/bin/sh
cd src
javac *.java

if [ $? -eq 0 ]
then
	java GraphTemplate ./example.txt
fi