public class Location {
    private static final long SerialVersionUID = 1111L;
    private final float x;
    private final int y; //Поле не может быть null
    private final Float z;

    public Location(float x, int y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}