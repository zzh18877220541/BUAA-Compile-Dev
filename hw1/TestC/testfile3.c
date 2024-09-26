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
const int const_array_int_1[3] = {0, 1, 2};  // 引入全局常量数组

/* global variable declarations */
int var_array_int_1[3] = {1, 2, 3}; // 全局变量数组

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

/* for_no_lack ans is Sum(array) */
int func6(int a[], int b) {
    int c = 0;
    int i;
    for (i = 0; i < b; i++) {
        c += a[i];
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
    int a[6] = {1, 2, 3, 4, 5, 6};  // 引入数组
    int b = getint(); // 97
    printf("22371187\n"); // 输出1

    /* 简化的 if_else 结构 */
    if (b < 50) {
        b = 100;
    } else {
        b = 7;
    }
    printf("b is %d\n", b); // 输出2

    int c;
    // c = (1 + 10) * (2 - 10) + 2 + 1 - 0 + 22 - 1 * 2 = -64
    c = (1 + b) * (2 - b) + const_array_int_1[1] + const_dec_int_2 - const_dec_int_1 + 22 - 1 * 2;
    printf("c is %d\n", c); // 输出3

    char d = getchar(); // 0
    if (d == 0) {
        d = 1;
    }
    if (d >= 0) {
        d = 2;
    }
    printf("%d\n", d); // 输出4

    int ans1 = func1(); // 0
    char ans2 = func2(); // 0
    func3();
    int ans4 = func4(c, 2); // 依赖于c的结果
    char ans5 = func5(1, d); // 3
    int ans6 = func6(a, 6);  // Sum of array a[6]: 21

    // 打印输出10次
    printf("%d\n", ans1); // 输出5
    printf("%d\n", ans2); // 输出6
    printf("%d\n", ans4); // 输出7
    printf("%d\n", ans5); // 输出8
    printf("%d\n", ans6); // 输出9

    int ans7 = func6(var_array_int_1, 3);  // Sum of var_array_int_1: 6
    printf("%d\n", ans7); // 输出10

    return 0;
}
