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

#Make server
cd site
mvn clean
cd ..
cp -r site $DIST/site

#Make android
cd android
mvn clean
cd ..
cp -r android $DIST/android

#Make bin
cp -r bin $DIST/bin

#Zipping
cd dist
zip -r codebone-$VERSION.zip codebone-$VERSION
cd ..
ls -al dist

if [ "$1" == "deploy" ]; then
	echo "Start release flow : codebone-$VERSION"
	mvn clean
	cp dist/codebone-$VERSION.zip ../
	git checkout gh-pages
	git reset HEAD --hard
	rm -rf release/codebone-$VERSION.zip
	mv ../codebone-$VERSION.zip release
	git add .
	git commit -m "release codebone $VERSION"
	git push
	echo "End release flow : codebone-$VERSION"
fi