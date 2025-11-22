public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {2, 6, 7, 17, 29, 31, 67, 89, 101};
        int key = 8;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                System.out.println(key + " found at index " + i);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println(key + " not found in the array.");
    }
}
