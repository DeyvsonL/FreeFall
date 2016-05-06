package application.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ff.physics.AirResistancePhysics;

import java.io.IOException;

public class VerticalLauncherDisplayController {

    //-- FXML Buttons --//

    @FXML
    private Button simulateButton;

    //-- FXML properties --//

    @FXML
    private Slider massSlider;
    @FXML
    private TextField massField;

    @FXML
    private Slider launchVelocitySlider;
    @FXML
    private TextField launchVelocityField;

    @FXML
    private Slider heightSlider;
    @FXML
    private TextField heightField;

    @FXML
    private Slider gravitySlider;
    @FXML
    private TextField gravityField;

    @FXML
    private Slider airResistanceSlider;
    @FXML
    private TextField airResistanceField;

    @FXML
    private ComboBox<AirResistancePhysics.DragMode> dragModeComboBox;

    // Charts
    @FXML
    private LineChart<Double, Double> positionOverTimeChart;
    @FXML
    private LineChart<Double, Double> velocityOverTimeChart;
    @FXML
    private LineChart<Double, Double> accelerationOverTimeChart;

    //-- FXML Linked properties --//

    private XYChart.Series<Double, Double> positionOverTimeAirResistanceSeries;
    private XYChart.Series<Double, Double> positionOverTimeNonAirResistanceSeries;
    private XYChart.Series<Double, Double> velocityOverTimeAirResistanceSeries;
    private XYChart.Series<Double, Double> velocityOverTimeNonAirResistanceSeries;
    private XYChart.Series<Double, Double> accelerationOverTimeAirResistanceSeries;
    private XYChart.Series<Double, Double> accelerationOverTimeNonAirResistanceSeries;

    //-- Internal properties --//

    private double mass;
    private double launchVelocity;
    private double gravity;
    private double airResistance;
    private double height;
    private AirResistancePhysics.DragMode DragMode;

    //-- Property constraints --//

    private static final double MIN_MASS = 0.1;
    private static final double MAX_MASS = 100;

    private static final double MIN_LAUNCH_VELOCITY = -200;
    private static final double MAX_LAUNCH_VELOCITY = 200;

    private static final double MIN_HEIGHT = 0;
    private static final double MAX_HEIGHT = 1000;

    private static final double MIN_GRAVITY = -1;
    private static final double MAX_GRAVITY = -100;

    private static final double MIN_AIR_RESISTANCE = 0;
    private static final double MAX_AIR_RESISTANCE = 1;

    //-- Default property values --//

    private static final double DEFAULT_MASS = 10;
    private static final double DEFAULT_HEIGHT = 10;
    private static final double DEFAULT_LAUNCH_VELOCITY = 100;
    private static final double DEFAULT_GRAVITY = -9.81;
    private static final double DEFAULT_AIR_RESISTANCE = 0.05;

    //-- physics values (and chart resolution) --//

    private static final double PHYSICS_STEP_TIME = 0.001;
    private static final int CHART_SKIP_PHYSICS_STEPS = 10;

    //-- Methods --//

    @FXML
    private void initialize() {
        configureFXMLProperties();
        configureFXMLCharts();
        clearFXMLChartSeries();
    }

    private void configureFXMLProperties() {
        massSlider.setMin(MIN_MASS);
        massSlider.setMax(MAX_MASS);
        massSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            massField.setText(newValue.toString());
            mass = newValue.doubleValue();
        });
        massField.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Float.parseFloat(newValue);
            if (value < MIN_MASS) {
                value = MIN_MASS;
            } else if (value > MAX_MASS) {
                value = MAX_MASS;
            }
            massSlider.setValue(value);
            mass = value;
        });
        massSlider.setValue(DEFAULT_MASS);

        heightSlider.setMin(MIN_HEIGHT);
        heightSlider.setMax(MAX_HEIGHT);
        heightSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            heightField.setText(newValue.toString());
            height = newValue.doubleValue();
        });
        heightField.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Float.parseFloat(newValue);
            if (value < MIN_HEIGHT) {
                value = MIN_HEIGHT;
            } else if (value > MAX_HEIGHT) {
                value = MAX_HEIGHT;
            }
            heightSlider.setValue(value);
            height = value;
        });
        heightSlider.setValue(DEFAULT_MASS);

        gravitySlider.setMin(MAX_GRAVITY);
        gravitySlider.setMax(MIN_GRAVITY);
        gravitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            gravityField.setText(newValue.toString());
            gravity = newValue.doubleValue();
        });
        gravityField.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Float.parseFloat(newValue);
            if (value < MAX_GRAVITY) {
                value = MAX_GRAVITY;
            } else if (value > MIN_GRAVITY) {
                value = MIN_GRAVITY;
            }
            gravitySlider.setValue(value);
            gravity = value;
        });
        gravitySlider.setValue(DEFAULT_GRAVITY);

        launchVelocitySlider.setMin(MIN_LAUNCH_VELOCITY);
        launchVelocitySlider.setMax(MAX_LAUNCH_VELOCITY);
        launchVelocitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            launchVelocityField.setText(newValue.toString());
            launchVelocity = newValue.doubleValue();
        });
        launchVelocityField.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Float.parseFloat(newValue);
            if (value < MIN_LAUNCH_VELOCITY) {
                value = MIN_LAUNCH_VELOCITY;
            } else if (value > MAX_LAUNCH_VELOCITY) {
                value = MAX_LAUNCH_VELOCITY;
            }
            launchVelocitySlider.setValue(value);
            launchVelocity = value;
        });
        launchVelocitySlider.setValue(DEFAULT_LAUNCH_VELOCITY);

        launchVelocitySlider.setMin(MIN_LAUNCH_VELOCITY);
        launchVelocitySlider.setMax(MAX_LAUNCH_VELOCITY);
        launchVelocitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            launchVelocityField.setText(newValue.toString());
            launchVelocity = newValue.doubleValue();
        });
        launchVelocityField.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Float.parseFloat(newValue);
            if (value < MIN_LAUNCH_VELOCITY) {
                value = MIN_LAUNCH_VELOCITY;
            } else if (value > MAX_LAUNCH_VELOCITY) {
                value = MAX_LAUNCH_VELOCITY;
            }
            launchVelocitySlider.setValue(value);
            launchVelocity = value;
        });
        launchVelocitySlider.setValue(DEFAULT_LAUNCH_VELOCITY);

        heightSlider.setMin(MIN_HEIGHT);
        heightSlider.setMax(MAX_HEIGHT);
        heightSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            heightField.setText(newValue.toString());
            height = newValue.doubleValue();
        });
        heightField.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Float.parseFloat(newValue);
            if (value < MIN_HEIGHT) {
                value = MIN_HEIGHT;
            } else if (value > MAX_HEIGHT) {
                value = MAX_HEIGHT;
            }
            heightSlider.setValue(value);
            height = value;
        });
        heightSlider.setValue(DEFAULT_LAUNCH_VELOCITY);


        airResistanceSlider.setMin(MIN_AIR_RESISTANCE);
        airResistanceSlider.setMax(MAX_AIR_RESISTANCE);
        airResistanceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            airResistanceField.setText(newValue.toString());
            airResistance = newValue.doubleValue();
        });
        airResistanceField.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Float.parseFloat(newValue);
            if (value < MIN_AIR_RESISTANCE) {
                value = MIN_AIR_RESISTANCE;
            } else if (value > MAX_AIR_RESISTANCE) {
                value = MAX_AIR_RESISTANCE;
            }
            airResistanceSlider.setValue(value);
            airResistance = value;
        });
        airResistanceSlider.setValue(DEFAULT_AIR_RESISTANCE);

        dragModeComboBox.getItems()
                .addAll(AirResistancePhysics.DragMode.values());
        dragModeComboBox.valueProperty().addListener(((observable, oldValue, newValue) -> {
            DragMode = newValue;
        }));
        dragModeComboBox.getSelectionModel()
                .select(AirResistancePhysics.DragMode.LINEAR);
    }

    private void configureFXMLCharts() {

        positionOverTimeChart.setCreateSymbols(false);
        velocityOverTimeChart.setCreateSymbols(false);
        accelerationOverTimeChart.setCreateSymbols(false);

        positionOverTimeChart.setAnimated(false);
        velocityOverTimeChart.setAnimated(false);
        accelerationOverTimeChart.setAnimated(false);

        positionOverTimeChart.getData().clear();
        velocityOverTimeChart.getData().clear();
        accelerationOverTimeChart.getData().clear();

        positionOverTimeAirResistanceSeries = new XYChart.Series<>();
        positionOverTimeNonAirResistanceSeries = new XYChart.Series<>();
        positionOverTimeAirResistanceSeries.setName("Air resistance");
        positionOverTimeNonAirResistanceSeries.setName("Non air resistance");
        positionOverTimeChart.getData().addAll(
                positionOverTimeAirResistanceSeries, positionOverTimeNonAirResistanceSeries);

        velocityOverTimeAirResistanceSeries = new XYChart.Series<>();
        velocityOverTimeNonAirResistanceSeries = new XYChart.Series<>();
        velocityOverTimeAirResistanceSeries.setName("Air resistance");
        velocityOverTimeNonAirResistanceSeries.setName("Non air resistance");
        velocityOverTimeChart.getData().addAll(
                velocityOverTimeAirResistanceSeries, velocityOverTimeNonAirResistanceSeries);

        accelerationOverTimeAirResistanceSeries = new XYChart.Series<>();
        accelerationOverTimeNonAirResistanceSeries = new XYChart.Series<>();
        accelerationOverTimeAirResistanceSeries.setName("Air resistance");
        accelerationOverTimeNonAirResistanceSeries.setName("Non air resistance");
        accelerationOverTimeChart.getData().addAll(
                accelerationOverTimeAirResistanceSeries,
                accelerationOverTimeNonAirResistanceSeries);
    }

    private void clearFXMLChartSeries() {
        positionOverTimeAirResistanceSeries.getData().clear();
        positionOverTimeNonAirResistanceSeries.getData().clear();
        velocityOverTimeAirResistanceSeries.getData().clear();
        velocityOverTimeNonAirResistanceSeries.getData().clear();
        accelerationOverTimeAirResistanceSeries.getData().clear();
        accelerationOverTimeNonAirResistanceSeries.getData().clear();
    }

    private void calculateFXMLChartData() {

        // Launcher Physics

        AirResistancePhysics airResistanceLauncher = new AirResistancePhysics(
                height, launchVelocity, mass, airResistance, PHYSICS_STEP_TIME, gravity, DragMode);

        AirResistancePhysics nonAirResistanceLauncher = new AirResistancePhysics(
                height, launchVelocity, mass, 0, PHYSICS_STEP_TIME, gravity, DragMode);

        while (nonAirResistanceLauncher.getTime() == 0
                || nonAirResistanceLauncher.getHeight() >= 0) {

            double launcherTime = nonAirResistanceLauncher.getTime();

            double nonAirResistancePosition = nonAirResistanceLauncher.getHeight();
            double nonAirResistanceVelocity = nonAirResistanceLauncher.getCurrentVelocity();
            double nonAirResistanceAcceleration = nonAirResistanceLauncher.getCurrentAcceleration();

            positionOverTimeNonAirResistanceSeries.getData().add(new XYChart.Data<>(
                    launcherTime, nonAirResistancePosition
            ));
            velocityOverTimeNonAirResistanceSeries.getData().add(new XYChart.Data<>(
                    launcherTime, nonAirResistanceVelocity
            ));
            accelerationOverTimeNonAirResistanceSeries.getData().add(new XYChart.Data<>(
                    launcherTime, nonAirResistanceAcceleration
            ));

            // Jumping steps
            for (int i = 0; i < CHART_SKIP_PHYSICS_STEPS; i++) {
                nonAirResistanceLauncher.calculateNextStep();
            }
        }

        while (airResistanceLauncher.getTime() == 0
                || airResistanceLauncher.getHeight() >= 0) {

            double launcherTime = airResistanceLauncher.getTime();

            double airResistancePosition = airResistanceLauncher.getHeight();
            double airResistanceVelocity = airResistanceLauncher.getCurrentVelocity();
            double airResistanceAcceleration = airResistanceLauncher.getCurrentAcceleration();

            positionOverTimeAirResistanceSeries.getData().add(new XYChart.Data<>(
                    launcherTime, airResistancePosition
            ));
            velocityOverTimeAirResistanceSeries.getData().add(new XYChart.Data<>(
                    launcherTime, airResistanceVelocity
            ));
            accelerationOverTimeAirResistanceSeries.getData().add(new XYChart.Data<>(
                    launcherTime, airResistanceAcceleration
            ));

            // Jumping steps
            for (int i = 0; i < CHART_SKIP_PHYSICS_STEPS; i++) {
                airResistanceLauncher.calculateNextStep();
            }
        }

    }

    //-- Button handles --//

    @FXML
    private void simulateButtonHandler() {
        clearFXMLChartSeries();
        calculateFXMLChartData();
    }

    //-- Loader --//

    public static VerticalLauncherDisplayController load(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
                VerticalLauncherDisplayController.class.getResource("VerticalLauncherDisplay.fxml"));
        VerticalLauncherDisplayController controller = loader.getController();
        stage.setScene(new Scene(loader.load()));
        return controller;
    }
}
