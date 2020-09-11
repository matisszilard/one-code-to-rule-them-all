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
                        sh 'make clean && mkdir -p build'
                        sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=Debug -DTESTING_ENABLED=ON && cmake --build . --config Debug && ./test_rule'
                    }
                }
                stage('Unit test on linux') {
                    agent {
                        kubernetes {
                        label 'linux-x86_64'
                        defaultContainer 'builder'
                        yaml """
kind: Pod
metadata:
  name: msz-golang
spec:
  containers:
  - name: builder
    image: mszg/docker-ubuntu-dev:
    imagePullPolicy: Always
    tty: true"""
                        } // kubernetes
                    } // agent
                    steps {
                        sh 'make clean && mkdir -p build'
                        sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=Debug -DTESTING_ENABLED=ON && cmake --build . --config Debug && ./test_rule'
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
                        sh 'make clean && mkdir -p build'
                        sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=Debug && cmake --build . --config Debug'
                    }
                }
                stage('build-ios-os') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        sh 'make clean && mkdir -p build'
                        sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=Debug -DCMAKE_TOOLCHAIN_FILE=../toolchains/iOS.toolchain.cmake -GXcode -DPLATFORM=OS && cmake --build . --config Debug'
                    }
                }
                stage('build-ios-simulator64') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        sh 'make clean && mkdir -p build'
                        sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=Debug -DCMAKE_TOOLCHAIN_FILE=../toolchains/iOS.toolchain.cmake -GXcode -DPLATFORM=SIMULATOR64 && cmake --build . --config Debug'
                    }
                }
            }
        }
    }
}
