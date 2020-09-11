cmake_minimum_required(VERSION 3.16)

project (rule)

# Setup general variables
set (RULE_INCLUDE_DIR ${CMAKE_CURRENT_SOURCE_DIR}/include)
set (RULE_SOURCE_DIR ${CMAKE_CURRENT_SOURCE_DIR}/src)
set (TEST_RULE_SOURCE_DIR ${CMAKE_CURRENT_SOURCE_DIR}/test)

# Setup library
include_directories (${RULE_INCLUDE_DIR})

add_library (${PROJECT_NAME} ${RULE_SOURCE_DIR}/rule.c)

# Setup unit testing

if (TESTING_ENABLED)
    add_executable (test_${PROJECT_NAME} ${TEST_RULE_SOURCE_DIR}/test_rule.c)
    target_link_libraries (test_${PROJECT_NAME} ${PROJECT_NAME})
endif()
