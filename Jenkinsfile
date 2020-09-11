pipeline {
    agent none

    stages {
        stage('Test') {
            parallel {
                stage('Unit test on macOS') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        sh 'mkdir -p build && cd build'
                        sh 'cmake .. -DCMAKE_BUILD_TYPE=Debug'
                        sh 'cmake --build . --config ${BUILD_TYPE}'
                    }
                }
            }
        }
        stage('Build') {
            parallel {
                stage('build-darwin-x86_64') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        sh 'mkdir -p build && cd build'
                        sh 'cmake .. -DCMAKE_BUILD_TYPE=Debug'
                        sh 'cmake --build . --config ${BUILD_TYPE}'
                    }
                }
                stage('build-ios-os') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        sh 'mkdir -p build && cd build'
                        sh 'cmake .. -DCMAKE_BUILD_TYPE=Debug -DCMAKE_TOOLCHAIN_FILE=../toolchains/iOS.toolchain.cmake -GXcode'
                        sh 'cmake --build . --config ${BUILD_TYPE}'
                    }
                }
                stage('build-ios-simulator64') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        sh 'mkdir -p build && cd build'
                        sh 'cmake .. -DCMAKE_BUILD_TYPE=Debug -DCMAKE_TOOLCHAIN_FILE=../toolchains/iOS.toolchain.cmake -GXcode'
                        sh 'cmake --build . --config ${BUILD_TYPE}'
                    }
                }
            }
        }
    }
}
