#/bin/bash

mvn package

pushd ../
mkdir -p ./builds
popd

cd target
cp  server-sdk-0.118.200901.jar ../../builds/server-sdk-0.118.200901.jar
