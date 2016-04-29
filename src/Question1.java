/**
 *
 * @author yahao.sun
 *
 */
public class Question1 {
    static int[][] getLockerDistanceGrid(int cityLength, int cityWidth, int[] lockerXCoordinates,
            int[] lockerYCoordinates) {
        // the spatial complexity of the algorithm is O(cityLength*cityWidth)
        // the time complexity of the algorithm is
        // O(lockerSize*cityLength*cityWidth)

        int[][] distanceGrid = new int[cityLength][cityWidth];

        for (int i = 0; i < cityLength; i++) {
            for (int j = 0; j < cityWidth; j++) {
                distanceGrid[i][j] = cityLength + cityWidth;
            }
        }
        int lockerSize = lockerXCoordinates.length;
        // loop for all the lockers
        for (int lck = 0; lck < lockerSize; lck++) {
            // get the locker location
            int lx = lockerXCoordinates[lck];
            int ly = lockerYCoordinates[lck];
            // recalculate the minimum distances
            for (int i = 0; i < cityLength; i++) {
                for (int j = 0; j < cityWidth; j++) {
                    int distance = Math.abs(i + 1 - lx) + Math.abs(j + 1 - ly);
                    // decide the minimum distance
                    distanceGrid[i][j] = distanceGrid[i][j] < distance ? distanceGrid[i][j] : distance;
                }
            }
        }
        return distanceGrid;
    }

    // could display the transformed matrix against the problem discription
    public static void print(int[][] data, int l, int w) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] a1 = Question1.getLockerDistanceGrid(3, 5, new int[] { 1 }, new int[] { 1 });
        Question1.print(a1, 3, 5);

        int[][] a2 = Question1.getLockerDistanceGrid(5, 7, new int[] { 2, 4 }, new int[] { 3, 7 });
        Question1.print(a2, 5, 7);
    }
}
