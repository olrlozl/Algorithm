import java.io.*;
import java.util.*;

public class Main {

    public static int cnt = 0; // arr에 수가 저장된 횟수

    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void mergeSort (int[] arr, int[] sortedArr, int left, int right, int K) throws Exception {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, sortedArr, left, mid, K);
            mergeSort(arr, sortedArr, mid + 1, right, K);
            merge(arr, sortedArr, left, mid, right, K);
        }
    }

    public static void merge (int[] arr, int[] sortedArr, int left, int mid, int right, int K) throws Exception {
        int L = left;
        int R = mid + 1;
        int idx = left;

        while (L <= mid && R <= right) {
            if (arr[L] < arr[R]) sortedArr[idx++] = arr[L++];
            else sortedArr[idx++] = arr[R++];
        }

        if (L <= mid) for (int i = L; i <= mid; i++) sortedArr[idx++] = arr[i];
        else for (int i = R; i <= right; i++) sortedArr[idx++] = arr[i];

        for (int i = left; i <= right; i++) {
            arr[i] = sortedArr[i];
            if (++cnt == K) bw.write(arr[i] + "");
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 배열 크기
        int K = Integer.parseInt(st.nextToken()); // K번째 저장되는 수 구하기

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N]; // 배열
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] sortedArr = new int[N]; // 정렬된 배열
        mergeSort(arr, sortedArr,0, N-1, K);

        if (cnt < K) bw.write("-1");
        bw.close();
    }
}