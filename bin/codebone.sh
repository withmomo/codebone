#!/bin/sh
# set JAVA
if [ -n "$JAVA_HOME" ]; then
    JAVA="$JAVA_HOME/bin/java"
else
    JAVA=java
fi

if [ -n "$CODEBONE_HOME" ]; then
	CODEBONE_HOME=.
else
	echo "ERROR: CODEBONE_HOME not found in your enviroment."
	echo "Please set the CODEBONE_HOME variable in your enviroment to match the location of your CODEBONE installation."
	exit 0;
fi

CODEBONE_SITE="$CODEBONE_HOME/site"
CODEBONE_TEMPLATE="$CODEBONE_HOME/template"
CMD="$JAVA -jar $CODEBONE_HOME/codebone-generator-*.jar"

if [ "$1" == "init" ]; then
	echo "Initialize project..."
	cp -r $CODEBONE_SITE/* .
	exit 0;
elif [ "$1" == "version" ]; then
	CLASSNAME="Version"
	CMD="$CMD $CLASSNAME"
	$CMD
	exit 0;
elif [ "$1" == "config" ]; then
	CLASSNAME="Config"
	CMD="$CMD $CLASSNAME ${@:2}"
else
	CLASSNAME="Codebone"
	CMD="$CMD $CLASSNAME $@"
fi

$CMD