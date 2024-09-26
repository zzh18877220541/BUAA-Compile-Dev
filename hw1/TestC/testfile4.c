#include <stdio.h>

int getchar() {
    char c;
    scanf("%c", &c);
    return (int)c;
}

int getint() {
    int t;
    scanf("%d", &t);
    while(getchar() != '\n');
    return t;
}

/* global constant values */
const int const_dec_int_1 = 0;
const int const_dec_int_2 = 1, const_dec_int_3 = 2, const_dec_int_4 = 3;
const char const_dec_char_1 = 0;
const char const_dec_char_2 = 1, const_dec_char_3 = 2, const_dec_char_4 = 3;

/* global variable declarations */
int var_dec_int_1;
int var_dec_int_2 = 1;
int var_dec_int_3 = 2, var_dec_int_4 = 3, var_dec_int_5 = 4;
char var_dec_char_1;
char var_dec_char_2 = 1;
char var_dec_char_3 = 2, var_dec_char_4 = 3, var_dec_char_5 = 4;

/* function declarations */

int func1() {
    return 0;
}

char func2() {
    return 0;
}

void func3() {
    return;
}

int func4(int a, int b) {
    return a + b;
}

char func5(char a, char b) {
    return a + b;
}

/* for_no_lack ans is Sum(a) */
int func6(int a, int b) {
    int c = 0;
    int i;
    for (i = 0; i < b; i++) {
        c += a;
    }
    return c;
}

// if_else
int func7(int a) {
    if (a > 0) {
        return a;
    } else {
        return -a;
    }
}

int main() {
    int b = getint(); // 97
    printf("22371187\n");

    /* Simplified if_else, no complex conditions */
    if (b < 50) {
        b = 100;
    } else {
        b = 7;
    }

    printf("b is %d\n", b);

    int c;
    // c = (1 + 10) * (2 - 10) + 2 + 1 - 0 + 22 - 1 * 2 = -64
    c = (1 + b) * (2 - b) + const_dec_char_2 + const_dec_int_2 - const_dec_int_1 + 22 - 1 * 2;
    printf("c is %d\n", c);

    char d = getchar(); // 0
    if (d == 0) {
        d = 1;
    }
    if (d >= 0) {
        d = 2;
    }
    printf("%d\n", d);

    int ans1 = func1(); // 0
    char ans2 = func2(); // 0
    func3();
    int ans4 = func4(c, 2); // result depends on c
    char ans5 = func5(1, d); // 3
    int ans6 = func6(5, 3); // simplified: sum of repeated number (5 * 3)

    printf("%d\n", ans1);
    printf("%d\n", ans2);
    printf("%d\n", ans4);
    printf("%d\n", ans5);
    printf("%d\n", ans6);
    printf("%d\n", func7(1)); // 1
    return 0;
}
