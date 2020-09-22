#!/usr/bin/env groovy

buildLinux() {
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${BUILD_TYPE} && cmake --build . --config ${BUILD_TYPE}'
    sh 'mv ./build/librule.a librule-linux-amd64.a'
}

buildMacOS() {
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${BUILD_TYPE} && cmake --build . --config ${BUILD_TYPE}'
    sh 'mv ./build/librule.a librule-darwin-x86_64.a'
}

buildiOS() {
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${BUILD_TYPE} -DCMAKE_TOOLCHAIN_FILE=../toolchains/iOS.toolchain.cmake -GXcode -DPLATFORM=${PLATFORM} && cmake --build . --config ${BUILD_TYPE}'
    sh 'mv ./build/librule.a librule-ios-${PLATFORM}.a'
}
