package com.jxshen.example.algorithm.sort;

public class HeapSort {

    public static void main(String[] args) {
        int[] array = {4,3,2,1};
        maxHeapSort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    public static void maxHeapSort(int[] array) {
        // 建堆，从最后一个非叶子节点开始，自底向上，自右向左遍历建堆
        for (int i = array.length/2 - 1; i >= 0; i--) {
            adjustMaxHeap(array, i, array.length -1);
        }
        // 交换头尾元素并调整堆
        for (int i = array.length - 1; i > 0 ; i--) {
            swap(array, 0, i); // 交换元素
            adjustMaxHeap(array, 0, i -1);
        }
    }

    public static void adjustMaxHeap(int[] array, int begin, int end) {
        int leftIndex = 2 * begin + 1; // 左儿子索引
        int rightIndex = 2 * begin + 2; // 有儿子索引
        boolean hasLeftChild = leftIndex <= end; // 是否左儿子
        boolean hasRightChild = rightIndex <= end; // 是否有右儿子
        if (hasLeftChild && hasRightChild) {
            // 同时存在左右儿子时，左儿子比右儿子和父节点都要大
            if (array[leftIndex] >= array[rightIndex] && array[leftIndex] > array[begin]) {
                swap(array, begin, leftIndex); // 左儿子上浮
                adjustMaxHeap(array, leftIndex, end); //调整左子树堆
            }
            // 同时存在左右儿子时，右儿子比左儿子和父节点都要大
            else if (array[rightIndex] >= array[leftIndex] && array[rightIndex] > array[begin]) {
                swap(array, begin, rightIndex); // 右儿子上浮
                adjustMaxHeap(array, rightIndex, end); // 调整右子树堆
            }
        }
        // 只存在左儿子而且大于父节点
        else if (hasLeftChild && array[leftIndex] > array[begin]) {
            // 左儿子上浮，此时不用递归调整左子树，因为堆是完全二叉树，左右儿子不满时说明这是最后一个父节点
            swap(array, begin, leftIndex);
        }
    }

    // 交换函数，原理：a ^ a = 0, a ^ 0 = a
    private static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
