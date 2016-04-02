import java.util.*;

public class Assignment1 {

    static Random random = new Random();
    static int numberOfCases = 10;
    public static void main(String[] args) {

        // problem 1-------
        for (int ik = 0; ik < numberOfCases; ik++) {
            System.out.print("Problem 1 (Case "+ik+"): ");
            int[] m = new int[Math.abs(random.nextInt(1000000)) + 1];
            int[] n = new int[m.length];
            for (int i = 0; i < m.length; i++) {
                m[i] = random.nextInt(500000);
                n[i] = m[i];
            }

            Arrays.sort(n);
            int index = Math.abs(random.nextInt(n.length));


            if (problem1(m, 0, m.length - 1, index) == n[index]) {
                System.out.println("Correct");
            } else {
                System.out.println("Wrong---------------");
            }
        }
        // problem 1-------

        // problem 2-------
        for (int ik = 0; ik < numberOfCases; ik++) {
            System.out.print("Problem 2 (Case " + ik + "): ");

            Integer[] arr = new Integer[Math.abs(random.nextInt(1000000))+1];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt();
            }

            int index = Math.abs(random.nextInt(arr.length));

            // to generate test cases i am doing nlog(n) sorting which slows my overall performance down.
            Arrays.sort(arr, 0, index + 1);
            Arrays.sort(arr, index, arr.length, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    Integer a1 = Integer.parseInt(o1.toString());
                    Integer a2 = Integer.parseInt(o2.toString());
                    return a2.compareTo(a1);

                }
            });

            int r = problem2(arr, 0, arr.length);
            if (r == arr[index]) {
                System.out.println("Correct");
            } else {
                System.out.println("Wrong---------------");
            }
        }
        // problem 2-------

    }

    public static int problem1(int[] arr, int l, int r, int ind) {
        if (l == r) {
            return arr[l];
        }

        int pivotIndex = Math.abs(random.nextInt(r-l));

        int tmp = arr[pivotIndex+l];
        arr[pivotIndex+l] = arr[r];
        arr[r] = tmp;

        int left = l;
        for (int i = l; i <r; i++) {
            if (arr[i] < arr[r]) {
                tmp = arr[i];
                arr[i] = arr[left];
                arr[left] = tmp;
                left++;
            }
        }

        tmp = arr[r];
        arr[r] = arr[left];
        arr[left] = tmp;

        if (left == ind) {
            return arr[left];
        }

        if (left > ind) {
            return problem1(arr, l, left-1, ind);
        } else {
            return problem1(arr, left+1, r, ind);
        }
    }

    public static int problem2(Integer[] arr, int l, int r){
        if (r == l || r - l == 1) return arr[l];

        if (r-l == 2) {
            if (arr[l] > arr[l+1]) return arr[l];
            else return arr[l+1];
        }

        int index = (r - l) / 2 + l;
        if (arr[index] > arr[index - 1] && arr[index] > arr[index + 1]) {
            return arr[index];
        } else if (arr[index - 1] < arr[index] && arr[index] < arr[index + 1]) {
            return problem2(arr, index + 1, r);
        } else {
            return problem2(arr, l, index);
        }
    }
}
