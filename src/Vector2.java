// Java didn't have a built-in vector2 class (or i could not find it) so I implemented it myself
// 2-dimensional vector class
public class Vector2 {

    public double x, y;

    Vector2(){
        this.x = 0;
        this.y = 0;
    }

    Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }
}
