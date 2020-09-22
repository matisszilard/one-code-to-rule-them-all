#!/usr/bin/env groovy

def gv

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
                        script {
                            BUILD_TYPE="Release"
                            sh 'echo BUILD_TYPE [${BUILD_TYPE}]'
                            gv = load "Jenkinsfile.groovy"
                            gv.buildMacOS()
                        }
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'librule-darwin-x86_64.a', fingerprint: true
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
                        script {
                            BUILD_TYPE="Release"
                            gv.buildLinux()
                        }
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'librule-linux-amd64.a', fingerprint: true
                        }
                    }
                }
                stage('build-ios-os') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        script {
                            BUILD_TYPE="Release"
                            PLATFORM=OS
                            gv.buildiOS()
                        }
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'librule-ios-OS.a', fingerprint: true
                        }
                    }
                }
                stage('build-ios-simulator64') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        script {
                            BUILD_TYPE="Release"
                            PLATFORM=SIMULATOR64
                            gv.buildiOS()
                        }
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'librule-ios-SIMULATOR64.a', fingerprint: true
                        }
                    }
                }
            }
        }
    }
}
