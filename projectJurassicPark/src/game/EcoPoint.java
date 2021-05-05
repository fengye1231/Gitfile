package game;

public class EcoPoint {
    private static int ecoPoint = 0;

    public static int getEcoPoint() {
        return ecoPoint;
    }

    // Fail fast Principle;
    public static boolean setEcoPoint(int newEcoPoint) {

        if (newEcoPoint < 0) {
            // check if the remaining ecoPoints is enough for deleting
            if (newEcoPoint + ecoPoint < 0) {
                return false;
            }
        }
        ecoPoint += newEcoPoint;
        return true;
    }
}
