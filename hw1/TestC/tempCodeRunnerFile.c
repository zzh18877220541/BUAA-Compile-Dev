// global constant values

const int const_dec_int_1 = 0;
const int const_dec_int_2 = 1, const_dec_int_3 = 2, const_dec_int_4 = 3;
const char const_dec_char_1 = 0;
const char const_dec_char_2 = 1, const_dec_char_3 = 2, const_dec_char_4 = 3;
const int const_array_int_1[3] = {0, 1, 2};
const int const_array_int_2[3] = {0, 1, 2}, const_array_int_3[3] = {0, 1, 2};
const char const_array_char_1[3] = {0, 1, 2};
const char const_array_char_2[3] = {0, 1, 2}, const_array_char_3[3] = {0, 1, 2};

/* global variable declarations */

int var_dec_int_1;
int var_dec_int_2 = 1;
int var_dec_int_3 = 2, var_dec_int_4 = 3, var_dec_int_5 = 4;
char var_dec_char_1;
char var_dec_char_2 = 1;
char var_dec_char_3 = 2, var_dec_char_4 = 3, var_dec_char_5 = 4;
int var_array_int_1[3];
int var_array_int_2[3] = {0, 1, 2};
int var_array_int_3[3] = {0, 1, 2}, var_array_int_4[3] = {0, 1, 2};
char var_array_char_1[3];
char var_array_char_2[3] = {0, 1, 2};
char var_array_char_3[3] = {0, 1, 2}, var_array_char_4[3] = {0, 1, 2};

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
int func6(int a[], int b) {
    int c = 0;
    int i;
    for (i = 0; i < b; i++) {
        c += a[i];
    }
    return c;
}

// for_one_lack ans is Sum(a)
int func7(int a[], int b) {
    int c = 0;
    int i = 0;
    int j;
    int k;
    for (; i < b; i++) {
        c += a[i];
    }
    for (j = b - 1; ; j--) {
        if (j < 0) {
            break;
        }
        c -= a[j];
    }
    for (k = 0; k < b;) {
        c += a[k];
        k++;
    }
    return c;
}

// for_two_lack ans is Sum(a)
int func8(int a[], int b) {
    int c = 0;
    int i = 0;
    int j;
    for (; ; i++) {
        if (i >= b) {
            break;
        }
        c += a[i];
    }
    for (j = b - 1; ;) {
        if (j < 0) {
            break;
        }
        c -= a[j];
        j--;
    }
    int k = 0;
    for (; k < b;) {
        c += a[k];
        k++;
    }
    return c;
}

// for_three_lack ans is Sum(a)
int func9(int a[], int b) {
    int c = 0;
    int i = 0;
    for (; ;) {
        if (i >= b) {
            break;
        }
        c += a[i];
        i++;
    }
    return c;
}

// if_else
int func10(int a) {
    if (a > 0) {
        return a;
    } else {
        return -a;
    }
    return 0;
}

// continue_break
int func11(int a[], int b) {
    int c = 0;
    int i;
    for (i = 0; i < b; i++) {
        if (a[i] % 2 == 0) {
            continue;
        }
        c += a[i];
        if (c > 4) {
            break;
        }
    }
    return c;
}

int main() {
    int a[6] = {1, 2, 3, 4, 5, 6};
    char s[5] = "abcd";
    int b;
    b = getint(); // 97


    printf("22371187\n");

    /* if_else and || && */
    if (b < 50 && b > 0) {
        b = 100;
    } else if (b > 'a') {
        b = 7 * '\a';
    } else if (b > '.' || b < 0) {
        b = '\n'; // finally b = 10
    } else {
        b = 0;
    }
    printf("b is %d\n", b);

    int c;
    // c = ((1 + 10) * (2 - 10) + 2 + 1 - 0 + 22 - 1 * 2) * 2 = -130
    c = ((a[0] + b) * (a[1] - b) + const_array_char_1[2] + const_dec_char_2 - const_dec_int_1 + 22 - 1 * 2) * 2;
    printf("c is %d\n", c);

    char d;
    d = getchar(); // 0
    if (d == 0) {
        d = 1;
    }
    if (d >= 0) {
        d = 2;
    }
    if (d <= 2) {
        d = 3;
    }
    if (d != 4) {
        d = 4;
    }
    if (d == 4) {
        d = const_array_int_1[0] + const_array_int_2[1];
    }
    printf("%c\n", d);
    
    int len = 6;
    int ans1 = func1(); // 0
    char ans2 = func2(); // 0
    func3();
    int ans4 = func4(c, 2); // -154
    char ans5 = func5(1, d); // 2
    int ans6 = func6(a, len); // 21
    int ans7 = func7(a, 6); // 21
    int ans8 = func8(a, len); // 21
    int ans9 = func9(a, 6); //21
    int ans10 = func10(-1); // 1
    int ans11 = func11(a, 6); // 9
    /*
    0
    0
    -154
    2
    21 21 21 21
    1
    */
    printf("%d\n", ans1);
    printf("%c\n", ans2);
    printf("%d\n", ans4);
    printf("%c\n", ans5);
    printf("%d %d %d %d\n", ans6, ans7, ans8, ans9);
    printf("%d %d\n", ans10, ans11);
    return 0;
}