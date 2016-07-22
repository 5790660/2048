package com.aaron;

import java.util.Random;

/**
 * Created by 创宇 on 2016/7/22.
 */
public class Matrix {

    public static final int GESTURE_UP=0;

    public static final int GESTURE_DOWN=1;

    public static final int GESTURE_LEFT=2;

    public static final int GESTURE_RIGHT=3;

    public static final int NUM = 4;

    private int mat[][] = new int[NUM + 1][NUM + 1];

    public Matrix() {
    }

    public int[][] getMat() {
        return mat;
    }

    public boolean fillGap(int dir) {
        boolean flag = false;
        if (dir == GESTURE_LEFT || dir == GESTURE_UP) {
            for (int i = 0; i < NUM; i++)
                for (int j = 0; j < NUM; j++) {
                    if (mat[i][j] == 0)
                        continue;

                    if (dir == GESTURE_LEFT) {
                        int k = j;
                        int pos = j;
                        while (k-- > 0) {
                            if (mat[i][k] == 0) {
                                pos = k;
                                flag = true;
                            } else {
                                break;
                            }
                        }
                        if (pos != j) {
                            mat[i][pos] = mat[i][j];
                            mat[i][j] = 0;
                        }
                    } else {
                        int k = i;
                        int pos = i;
                        while (k-- > 0) {
                            if (mat[k][j] == 0) {
                                pos = k;
                                flag = true;
                            } else {
                                break;
                            }
                        }
                        if (pos != i) {
                            mat[pos][j] = mat[i][j];
                            mat[i][j] = 0;
                        }
                    }
                }
        } else if (dir == GESTURE_RIGHT || dir == GESTURE_DOWN) {
            for (int i = NUM - 1; i >= 0; i --)
                for (int j = NUM - 1; j >= 0; j --) {
                    if (mat[i][j] == 0)
                        continue;

                    if (dir == GESTURE_RIGHT) {
                        int k = j;
                        int pos = j;
                        while (++k < NUM) {
                            if (mat[i][k] == 0) {
                                pos = k;
                                flag = true;
                            } else {
                                break;
                            }
                        }
                        if (pos != j) {
                            mat[i][pos] = mat[i][j];
                            mat[i][j] = 0;
                        }
                    } else {
                        int k = i;
                        int pos = i;
                        while (++k < NUM) {
                            if (mat[k][j] == 0) {
                                pos = k;
                                flag = true;
                            } else {
                                break;
                            }
                        }
                        if (pos != i) {
                            mat[pos][j] = mat[i][j];
                            mat[i][j] = 0;
                        }
                    }
                }
        }
        return flag;
    }

    public int mergeSameNum(int dir) {
        int score = 0;
        if (dir == GESTURE_LEFT || dir == GESTURE_UP ) {
            for (int i = 0; i < NUM; i ++)
                for (int j = 0; j < NUM; j ++) {
                    if (mat[i][j] == 0)
                        continue;
                    if (dir == GESTURE_LEFT && j < NUM - 1) {
                        if (mat[i][j] == mat[i][j + 1]) {
                            mat[i][j] *= 2;
                            mat[i][j + 1] = 0;
                            score +=mat[i][j];
                        }
                    } else if (dir == GESTURE_UP && i < NUM - 1) {
                        if (mat[i][j] == mat[i + 1][j]) {
                            mat[i][j] *= 2;
                            mat[i + 1][j] = 0;
                            score +=mat[i][j];
                        }
                    }
                }
        } else if (dir == GESTURE_RIGHT || dir == GESTURE_DOWN) {
            for (int i = NUM - 1; i >= 0; i --)
                for (int j = NUM - 1; j >= 0; j --) {
                    if (mat[i][j] == 0)
                        continue;
                    if (dir == GESTURE_RIGHT && j < NUM - 1) {
                        if (mat[i][j] == mat[i][j + 1]) {
                            mat[i][j + 1] *= 2;
                            mat[i][j] = 0;
                            score +=mat[i][j+1];
                        }
                    } else if (dir == GESTURE_DOWN && i < NUM - 1) {
                        if (mat[i][j] == mat[i + 1][j]) {
                            mat[i + 1][j] *= 2;
                            mat[i][j] = 0;
                            score +=mat[i+1][j];
                        }
                    }
                }
        }
        return score;
    }


    public void placeRandomNum() {
        while (true) {
            Random random = new Random();
            int x = random.nextInt(NUM);
            int y = random.nextInt(NUM);
            if (mat[x][y] == 0) {
                mat[x][y] = 2;
                break;
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < NUM; i ++)
            for (int j = 0; j < NUM; j ++) {
                if (mat[i][j] == 0)
                    return false;
                if (mat[i][j] == mat[i][j + 1] || mat[i][j] == mat[i + 1][j]) {
                    return false;
                }
            }
        return true;
    }
}
