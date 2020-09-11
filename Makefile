.PHONY: all

all:
	echo "Do nothing"

CMAKE = `which cmake`
IOS_CMAKE_COMMAND = ${CMAKE} .. -DCMAKE_TOOLCHAIN_FILE=../toolchains/iOS.toolchain.cmake -GXcode
PREPARE_BUILD= `mkdir -p build && cd build`

BUILD_TYPE?=Release
PLATFORM?=OS
build-ios: clean
	${PREPARE_BUILD}; ${IOS_CMAKE_COMMAND} -DCMAKE_BUILD_TYPE=${BUILD_TYPE} -DPLATFORM=${PLATFORM}; cmake --build . --config ${BUILD_TYPE}

clean:
	rm -rf build
