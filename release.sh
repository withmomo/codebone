#!/bin/sh
VERSION=0.2.0
DIST="dist/codebone-$VERSION"

#Clean
rm -rf $DIST

#Build
mvn clean package

#Make dirs.
mkdir -p $DIST

#Make generator
cp -r generator/target/codebone-*.jar $DIST
cp -r generator/target/lib $DIST/lib

#Make template
cp -r generator/template $DIST/template

#Make site
cd site
mvn clean
cd ..
cp -r site $DIST/site

#Make bin
cp -r bin $DIST/bin

#Zipping
cd dist
zip -r codebone-$VERSION.zip codebone-$VERSION

ls -al