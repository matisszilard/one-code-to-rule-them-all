#include "test.h"

#include "rule.h"

void test_get_the_meaning_of_life()
{
    EXPECT_EQ(41, get_the_meaning_of_life())
}

void run_tests () {
    test_get_the_meaning_of_life ();
    printf("[ALL TEST SUCCEEDED]\n");
}

int main () {
    run_tests();
}
