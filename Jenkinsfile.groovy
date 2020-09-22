
def buildLinux() {
    sh 'echo BUILD_TYPE [${BUILD_TYPE}]'
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${BUILD_TYPE} && cmake --build . --config ${BUILD_TYPE}'
    sh 'mv ./build/librule.a librule-linux-amd64.a'
}

def buildMacOS() {
    sh 'echo BUILD_TYPE [${BUILD_TYPE}]'
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${BUILD_TYPE} && cmake --build . --config ${BUILD_TYPE}'
    sh 'mv ./build/librule.a librule-darwin-x86_64.a'
}

def buildiOS() {
    sh 'echo BUILD_TYPE [${BUILD_TYPE}]'
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${BUILD_TYPE} -DCMAKE_TOOLCHAIN_FILE=../toolchains/iOS.toolchain.cmake -GXcode -DPLATFORM=${PLATFORM} && cmake --build . --config ${BUILD_TYPE}'
    sh 'mv ./build/librule.a librule-ios-${PLATFORM}.a'
}

return this