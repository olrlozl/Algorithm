import java.io.*;
import java.util.*;

public class Solution {

    public static int[] sortedArr = new int[1000000];

    public static void mergeSort (int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid +1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge (int[] arr, int left, int mid, int right) {
        int L = left;
        int R = mid + 1;
        int idx = left;

        while (L <= mid && R <= right) {
            if (arr[L] <= arr[R]) sortedArr[idx++] = arr[L++];
            else sortedArr[idx++] = arr[R++];
        }

        if (L <= mid) {
            for (int i = L; i <= mid; i++) sortedArr[idx++] = arr[i];
        }
        else {
            for (int i = R; i <= right; i++) sortedArr[idx++] = arr[i];
        }

        for (int i = left; i <= right; i++) arr[i] = sortedArr[i];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[1000000];

        for (int i = 0; i < 1000000; i++) arr[i] = Integer.parseInt(st.nextToken());

        mergeSort(arr, 0, 999999);

        bw.write(arr[500000] + "");
        bw.close();
    }
}