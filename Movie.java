import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Movie {
    public static void main(String[] args) {
        int[][] seats = new int[10][20];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                // 1 Represents an empty seat
                seats[i][j] = 1;

            }
        }
        ArrayList<String> currRes = new ArrayList<String>();
        String[] row = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        try {
            Scanner scan = new Scanner(System.in);
            while (scan.hasNextLine()) {
                String currline = scan.nextLine();

                if (currline.isEmpty()) {
                    System.out.println();
                    System.exit(0);
                } else {
                    String[] res = currline.split(" ");
                    if (res.length == 2) {
                        String resID = res[0];
                        int occ = Integer.parseInt(res[1]);
                        boolean seated = false;
                        boolean capacity = true;
                        int samerow = -1;
                        for (int k = 0; k < seats.length; k++) {
                            for (int l = 0; l < seats[k].length; l++) {
                                if (seats[k][l] == 1 && occ > 0 && seats[k].length - l >= occ && !seated) {
                                    capacity = false;
                                    seats[k][l] = 0;
                                    occ--;
                                    currRes.add(row[k] + Integer.toString(l));
                                    samerow = k;
                                } else if (occ == 0 && !seated && samerow == k) {
                                    int rem = seats[k].length - l;
                                    if (rem >= 3) {
                                        seats[k][l] = -1;
                                        seats[k][l + 1] = -1;
                                        seats[k][l + 2] = -1;
                                    } else if (rem == 2) {
                                        seats[k][l] = -1;
                                        seats[k][l + 1] = -1;
                                    } else if (rem == 1) {
                                        seats[k][l] = -1;
                                    }
                                    seated = true;
                                } else if (occ <= 0 || occ > 20) {
                                    System.out.print(resID + " Cannot be seated");
                                    continue;
                                    
                            }
                        }
                        System.out.println();
                        if (capacity) {
                            System.out.print(resID + " Cannot be seated");
                        } else {
                            System.out.print(resID + " ");
                            boolean found = true;
                            for (String s : currRes) {
                                if (found == true) {
                                    System.out.print(s); // no comma
                                    found = false;
                                } else {
                                    System.out.print(", " + s);
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid Reservation");
                        continue;
                    }
                    currRes.clear();
                }
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Invalid Reservation");
        }
    }
}
