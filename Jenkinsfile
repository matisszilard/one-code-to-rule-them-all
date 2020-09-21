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
                            label 'linux-amd64'
                            defaultContainer 'builder'
                            yamlFile 'linux.yaml'
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
                    post {
                        success {
                            archiveArtifacts artifacts: 'build/librule.a', fingerprint: true
                        }
                    }
                }
                stage('build-linux-amd64') {
                    agent {
                        kubernetes {
                            label 'linux-amd64'
                            defaultContainer 'builder'
                            yamlFile 'linux.yaml'
                        } // kubernetes
                    }
                    steps {
                        sh 'make clean && mkdir -p build'
                        sh 'cd build && cmake .. -DCMAKE_BUILD_TYPE=Debug && cmake --build . --config Debug'
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'build/librule.a', fingerprint: true
                        }
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
                post {
                    success {
                        archiveArtifacts artifacts: 'build/librule.a', fingerprint: true
                    }
                }
            }
        }
    }
}
