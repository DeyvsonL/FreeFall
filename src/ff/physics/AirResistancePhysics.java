package ff.physics;

/**
 * Created by Deyv on 19/04/2016.
 */
public class AirResistancePhysics {
    private double height;
    private double currentVelocity;
    private double currentAcceleration;
    private double mass;
    private double airResistanceConstant;
    private double deltaTime;
    private double time;
    private final double gravity;
    private DragMode dragMode;

    private double ascendTime;
    private double descendTime;
    private double maxHeight;

    public AirResistancePhysics(double height, double currentVelocity, double mass, double airResistanceConstant, double deltaTime, double gravity, DragMode dragMode) {
        this.height = height;
        this.currentVelocity = currentVelocity;
        currentAcceleration = gravity;
        this.mass = mass;
        this.airResistanceConstant = airResistanceConstant;
        this.deltaTime = deltaTime;
        this.gravity = gravity;
        this.dragMode = dragMode;

        ascendTime = 0;
        descendTime = 0;
        maxHeight = height;
    }

    public double getHeight() {
        return height;
    }

    public double getCurrentVelocity() {
        return currentVelocity;
    }

    public double getCurrentAcceleration() {
        return currentAcceleration;
    }

    public double getTime() {
        return time;
    }

    public void calculateNextStep() {
        double currentHeight = height;
        double currentAcceleration = this.currentAcceleration;

        switch (dragMode) {
            case LINEAR:
                this.currentAcceleration = gravity - (airResistanceConstant * currentVelocity) / mass;
                break;
            case SQUARE:
                this.currentAcceleration = gravity - (airResistanceConstant * Math.abs(currentVelocity) * currentVelocity) / mass;
                break;
        }

        currentVelocity = currentVelocity + currentAcceleration * deltaTime;
        height += currentVelocity * deltaTime;
        time += deltaTime;

        if (height > currentHeight) {
            ascendTime += deltaTime;
            maxHeight = height;
        } else if (height < currentHeight) {
            descendTime += deltaTime;
        }
    }

    public double getAscendTime() {
        return ascendTime;
    }

    public double getDescendTime() {
        return descendTime;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    @Override
    public String toString() {
        return "Height: " + height + "\nVelocity: " + currentVelocity + "\nAcceleration" + currentAcceleration + "\n";
    }

    public enum DragMode {
        LINEAR,
        SQUARE
    }
}