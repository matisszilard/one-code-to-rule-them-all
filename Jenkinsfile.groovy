
def buildLinux(buildType = "Release") {
    sh 'echo BUILD_TYPE [${buildType}]'
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${buildType} && cmake --build . --config ${buildType}'
    sh 'mv ./build/librule.a librule-linux-amd64.a'
}

def buildMacOS(buildType = "Release") {
    sh 'echo BUILD_TYPE [${BUILD_TYPE}]'
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${buildType} && cmake --build . --config ${buildType}'
    sh 'mv ./build/librule.a librule-darwin-x86_64.a'
}

def buildiOS(platform, buildType = "Release") {
    sh 'echo BUILD_TYPE [${BUILD_TYPE}]'
    sh 'make clean && mkdir -p build'
    sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=${buildType} -DCMAKE_TOOLCHAIN_FILE=../toolchains/iOS.toolchain.cmake -GXcode -DPLATFORM=${platform} && cmake --build . --config ${buildType}'
    sh 'mv ./build/librule.a librule-ios-${platform}.a'
}

return this
