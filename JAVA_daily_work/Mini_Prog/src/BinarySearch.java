public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 6, 7, 17, 29, 31, 67, 89, 101};
        int key = 101;
        int left = 0, right = arr.length - 1;
        boolean found = false;

        while (left <= right) {
            int mid = left + (right - left) / 2; 

            if (arr[mid] == key) {
                System.out.println(key + " found at index " + mid);
                found = true;
                break;
            } else if (arr[mid] < key) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }

        if (!found)
            System.out.println(key + " not found in the array.");
    }
}
