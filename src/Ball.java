public class Ball {

    Vector2 pos;
    Ball parent;
    double vel;
    double acc;
    int mass;
    double angle;
    int distance = 200;

    Ball(Vector2 pos, int mass, double angle){
        this.pos = pos;
        this.parent = null;
        this.mass = mass;
        this.angle = angle;
        this.vel = 0;
        this.acc = 0;
    }

    Ball(Ball parent, int mass, double angle){
        this.parent = parent;
        this.mass = mass;
        this.angle = angle;
        this.vel = 0;
        this.acc = 0;

        calculatePos();
    }

    public void calculatePos() {
        Vector2 _pos = new Vector2((int) (distance * Math.sin(angle)),(int) (distance * Math.cos(angle)));
        this.pos = Vector2.add(parent.pos, _pos);

    }
}
