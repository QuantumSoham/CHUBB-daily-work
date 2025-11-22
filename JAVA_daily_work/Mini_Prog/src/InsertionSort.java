public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {1002,110,23445,6789,9807,6455,75432};

        System.out.println("Before Sorting:");
        printArray(arr);

        insertionSort(arr);

        System.out.println("After Sorting:");
        printArray(arr);
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        // Start from index 1 because the first element is already "sorted"
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // current element to insert
            int j = i - 1;

            // Move elements greater than 'key' one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            // Place key at its correct position
            arr[j + 1] = key;
        }
    }

    // Utility function to print array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
