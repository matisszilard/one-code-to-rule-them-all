#ifndef TEST_H
#define TEST_H

#include <stdio.h>
#include <stdlib.h>

#define EXPECT_EQ(a, b) if (a != b) {printf("TEST FAIL: [%d] is not equal with [%d]\n", a, b); exit(1);}

#endif // TEST_H