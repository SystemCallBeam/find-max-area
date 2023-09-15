import java.util.Arrays;
import java.util.Scanner;

class Lab7_max_square_and_edit_distance {
    private static int[][] matrix;
    private static int rows;
    private static int cols;
    private static int max_so_far_for_recursion = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine(), str2 = sc.nextLine();
        sc.close();
        int data[][] = { { 0, 1, 1, 0, 1 },
                { 1, 1, 0, 1, 0 },
                { 0, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0 } };

        int ans = find_max_area_recurse_entry(data);
        System.out.println(ans);

        System.out.println(q2_string_edit_distance(str1, str2));
    }

    public static int find_max_area_recurse_entry(int[][] mat) {
        // begin preparation
        matrix = mat;
        rows = matrix.length;
        cols = matrix[0].length;
        // end preparation
        q1_1_find_max_area_recurse(matrix, 0, 0);
        return max_so_far_for_recursion * max_so_far_for_recursion;
    }

    private static int q1_1_find_max_area_recurse(int[][] mat, int r, int c) {
        if (r == rows || c == cols)
            return 0;
        // your code

        int size = 0;

        if (mat[r][c] == 1) {
            size = 1;
        }

        int right = q1_1_find_max_area_recurse(mat, r, c + 1);
        int down = q1_1_find_max_area_recurse(mat, r + 1, c);
        int rdown = q1_1_find_max_area_recurse(mat, r + 1, c + 1);
        int tmp = (right < down ? right : down);
        size += (rdown < tmp ? rdown : tmp);

        max_so_far_for_recursion = size > max_so_far_for_recursion ? size : max_so_far_for_recursion;
        return size;
    }

    private static int q1_2_find_max_area_recurse(int[][] mat, int r, int c) {
        return 0;
    }

    private static int q2_string_edit_distance(String str1, String str2) {
        // begin preparation
        int[][] cache = new int[str1.length() + 1][str2.length() + 1];
        for (int[] row : cache)
            Arrays.fill(row, Integer.MAX_VALUE);
        int r;
        for (r = 0; r < cache.length; r++) {
            cache[r][str2.length()] = str1.length() - r; // str2 -> str1
        }
        for (int c = 0; c < cache[0].length; c++) {
            cache[str1.length()][c] = str2.length() - c; // str1 -> str2
        }
        // end preparation
        for (int i = str1.length() - 1; i >= 0; i--) {
            for (int j = str2.length() - 1; j >= 0; j--) {
                // your code
                if (str1.charAt(i) == str2.charAt(j)) {
                    cache[i][j] = cache[i + 1][j + 1];
                } else {
                    cache[i][j] = 1 + Math.min(cache[i + 1][j], Math.min(cache[i][j + 1], cache[i + 1][j + 1]));
                }
            }
        }
        return cache[0][0];
    }

}