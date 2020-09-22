#!/usr/bin/env groovy

def gv = load "Jenkins.groovy"

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
                        def gv = load "Jenkins.groovy"
                        sh 'BUILD_TYPE=Release'
                        gv.buildMacOS()
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'build/librule-darwin-x86_64.a', fingerprint: true
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
                        //sh 'BUILD_TYPE=Release'
                        //gv.buildLinux()
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'build/librule-linux-amd64.a', fingerprint: true
                        }
                    }
                }
                stage('build-ios-os') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        //sh 'PLATFORM=OS'
                        //sh 'BUILD_TYPE=Release'
                        //gv.buildiOS()
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'build/librule-ios-OS.a', fingerprint: true
                        }
                    }
                }
                stage('build-ios-simulator64') {
                    agent {
                        label 'macos'
                    }
                    steps {
                        //sh 'PLATFORM=SIMULATOR64'
                        //sh 'BUILD_TYPE=Release'
                        //gv.buildiOS()
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: 'build/librule-ios-SIMULATOR64.a', fingerprint: true
                        }
                    }
                }
            }
        }
    }
}
