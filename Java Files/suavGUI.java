//Imports
	import javafx.application.Application;
	import javafx.scene.image.Image;
	import javafx.scene.image.ImageView;
	import javafx.scene.image.*;
	import javafx.scene.paint.Color;
	import javafx.scene.paint.CycleMethod;
	import javafx.scene.paint.LinearGradient;
	import javafx.scene.paint.Stop;
	import javafx.scene.shape.Rectangle;
	import javafx.scene.text.Font;
	import javafx.scene.text.FontWeight;
	import javafx.scene.text.Text;
	import javafx.scene.chart.LineChart;
	import javafx.scene.chart.NumberAxis;
	import javafx.scene.chart.XYChart;
	import javafx.beans.value.ChangeListener;
	import javafx.beans.value.ObservableValue;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.scene.control.*;
	import javafx.stage.*;
	import javafx.scene.*;
	import javafx.scene.layout.*;
	import javafx.scene.control.*;
	import javafx.geometry.*;
	import javafx.scene.shape.*;
	import javafx.scene.effect.*;
	import javafx.scene.paint.*;
	import javafx.scene.text.*;

	import java.util.ArrayList;

public class suavGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		//Layout
			BorderPane window = new BorderPane();
			window.setPrefSize(900, 650);

			//Top Portion
				BorderPane titleBox = new BorderPane();

					Text titleLabel = new Text("Aircraft Designer");
					titleLabel.setFont(new Font("Verdana", 50));
						DropShadow ds = new DropShadow();
						ds.setOffsetY(3.0f);
						ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

						//titleLabel.setEffect(ds); //Uncomment to add a drop shadow effect
						titleLabel.setCache(true);
						titleLabel.setX(10.0f);
						titleLabel.setY(270.0f);
						titleLabel.setFill(Color.web("#23297A"));
						titleLabel.setFont(Font.font(null, FontWeight.BOLD, 60));

					VBox instructions = new VBox();

						Button instructButton = new Button("Instructions");
						instructButton.setPrefSize(150, 40);

					instructions.getChildren().add(instructButton);
					instructions.setAlignment(Pos.CENTER);

				titleBox.setCenter(titleLabel);
				titleBox.setRight(instructions);
				titleBox.setPadding(new Insets(10, 10, 10, 10));
				titleBox.setStyle("-fx-background-color: #daa520;");

			window.setTop(titleBox);
			window.setAlignment(titleBox, Pos.TOP_RIGHT);

			//Left Portion (Inputs)
				VBox inputsBox = new VBox(30);

					int fontSize = 20;

					Label inputsBoxTitle = new Label("Design Inputs");
					inputsBoxTitle.setFont(new Font("Courier", 30));

					HBox dragBox = new HBox(10);

						Label dragLabel = new Label("Counts of Drag:      ");
						dragLabel.setFont(new Font("Courier", fontSize));
						NumberTextField dragField = new NumberTextField();
						dragField.setPromptText("Cd,o");

					dragBox.getChildren().addAll(dragLabel, dragField);
					dragBox.setAlignment(Pos.CENTER);
					dragBox.setPadding(new Insets(5, 5, 5, 5));

					HBox speedBox = new HBox(10);

						Label speedLabel = new Label("Cruise Speed (mph):");
						speedLabel.setFont(new Font("Courier", fontSize));
						NumberTextField speedField = new NumberTextField();
						speedField.setPromptText("Speed in mph");

					speedBox.getChildren().addAll(speedLabel, speedField);
					speedBox.setAlignment(Pos.CENTER);
					speedBox.setPadding(new Insets(5, 5, 5, 5));

					HBox weightBox = new HBox(10);

						Label weightLabel = new Label("Weight (pounds):    ");
						weightLabel.setFont(new Font("Courier", fontSize));
						NumberTextField weightField = new NumberTextField();
						weightField.setPromptText("Weight (pounds)");

					weightBox.getChildren().addAll(weightLabel, weightField);
					weightBox.setAlignment(Pos.CENTER);
					weightBox.setPadding(new Insets(5, 5, 5, 5));

					HBox reynoldsBox = new HBox(10);

						Label reynoldsLabel = new Label("Reynolds Number:  ");
						reynoldsLabel.setFont(new Font("Courier", fontSize));
						NumberTextField reynoldField = new NumberTextField();
						reynoldField.setPromptText("Reynolds No.");

					reynoldsBox.getChildren().addAll(reynoldsLabel, reynoldField);
					reynoldsBox.setAlignment(Pos.CENTER);
					reynoldsBox.setPadding(new Insets(5, 5, 5, 5));

					HBox spanBox = new HBox(10);

						Label spanLabel = new Label("Wing Span (feet):   ");
						spanLabel.setFont(new Font("Courier", fontSize));
						NumberTextField spanField = new NumberTextField();
						spanField.setPromptText("Wing Span (ft.)");

					spanBox.getChildren().addAll(spanLabel, spanField);
					spanBox.setAlignment(Pos.CENTER);
					spanBox.setPadding(new Insets(5, 5, 5, 5));

					Button calculateStatic = new Button("Calculate");
					calculateStatic.setPrefSize(200, 40);

				inputsBox.getChildren().addAll(inputsBoxTitle, dragBox, speedBox,
					weightBox, reynoldsBox, spanBox, calculateStatic);
				inputsBox.setAlignment(Pos.CENTER);
				inputsBox.setPadding(new Insets(20, 20, 20, 20));
			window.setLeft(inputsBox);

			//Right Portion (Graphs)
				VBox rightSide = new VBox(10);

					VBox rightTitleBox = new VBox(10);

						Label rightTitleLabel = new Label("Performance Graphs");
						rightTitleLabel.setFont(new Font("Courier", 30));

						HBox comboBox = new HBox(10);

							Label varyLabel = new Label("Vary");
							varyLabel.setFont(new Font("Courier", 15));

							Label byLabel = new Label("by");
							byLabel.setFont(new Font("Courier", 15));

							ComboBox yBox = new ComboBox();
							yBox.getItems().addAll("Cd,o", "Speed", "Weight", "Wingspan");
							yBox.setPromptText("Y-Value");
							ComboBox xBox = new ComboBox();
							xBox.getItems().addAll("Cd,o", "Speed", "Weight", "Wingspan");
							xBox.setPromptText("X-Value");

							Label taken = new Label("");

						comboBox.getChildren().addAll(varyLabel, yBox, byLabel, xBox);
						comboBox.setAlignment(Pos.CENTER);
						comboBox.setPadding(new Insets(10, 10, 10, 10));

					rightTitleBox.getChildren().addAll(rightTitleLabel, comboBox);
					rightTitleBox.setAlignment(Pos.CENTER);
					rightTitleBox.setPadding(new Insets(5, 5, 5, 5));

					VBox graphBox = new VBox(20);

						NumberAxis xAxisFake = new NumberAxis();
						NumberAxis yAxisFake = new NumberAxis();

						LineChart<Number, Number> lineChartFake =
							new LineChart<Number, Number>(xAxisFake, yAxisFake);

					graphBox.getChildren().addAll(lineChartFake);
					graphBox.setAlignment(Pos.CENTER);
					graphBox.setPadding(new Insets(10, 10, 10, 10));

				rightSide.getChildren().addAll(rightTitleBox, graphBox);

			window.setRight(rightSide);

			Scene scene = new Scene(window);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

			//Instructions
				final Stage instructionStage = new Stage();
					FlowPane instructionWindow = new FlowPane();
					instructionWindow.setPrefSize(400, 400);

						HBox messageBox = new HBox();

							Text message = new Text();
							message.setText("Aircraft Designer is a performance evaluation tool used to guide the design of conceptual aircraft.\n\n\n\nThe left side of the main menu serves as a static calculator. If you are able to fill in 4 or the 5 inputs, the fifth one can be calculated for you.\nThe inputs are:\n\nCounts of Drag (Cd,o): the parasitic drag coefficient in counts\n 	Cd,o is the drag coefficient at 0 angle of attack, AKA the parasitic drag coefficient. This number is obtained from the polar data of an airfoil\n 		and will only occur at a specific Reynolds Number (another input).  Counts of drag correspond to the drag coefficient in thousandths.\n  			e.g.- a Cd,o of 0.018 has 18 counts of drag.\n\nCruise Speed: the desired cruise speed (note: not the take off speed)\n\nWeight: The weight of the aircraft in pounds. Make sure to over-estimate by ~10% if you plan to build this thing.\n\nReynolds Number: A dimensionless ratio of the inertial to viscous forces of a given test condition.\n 	The Reynolds number indicates the type of flight condition your aircraft will be in. Every wing operates differently at different\n 	Reynolds Numbers, so when you are finding airfoils, make sure you write down the reynolds number corresponding to the polars you eventually use.\n  		Reynolds Number for UAV's tends to be around ~[50,000 - 200,000].\n  			The design influence this figure has is on the length of your wing's chord (trapezoidal height).\n\nWingspan: The spanwise length of the wing in feet.  Pretty straightforward.\n\n\nOnce every parameter has been filled out, the right side of the menu can be accessed.  Using the combo Boxes above, you can visually see the impact each\nvariable has on one another and use their relationships to guide your design.\n\n\nIt is important to note that, as with all design, optimization must be done with respect to a certain set of parameters.\nBecause Aircraft Designer is most accurate with unmanned vehicles, all parameters are optimized for MAX PERFORMANCE AT MINIMUM POWER.\n No factors of stability or handling are considered as they contribute less to the conceptual shaping of the vehicle. Fuel dispersion, v-n-loading, and\nother post-concept design features are also not handled... it's too risky to make those decisions for you ;)\n\n\nFor any technical questions, contact jackson.merkl@gatech.edu");
							message.setFont(new Font("Courier", 12));

						messageBox.getChildren().add(message);
						messageBox.setPadding(new Insets(10, 10, 10, 10));
					instructionWindow.getChildren().add(messageBox);

					Scene instructionScene = new Scene(instructionWindow);
					instructionStage.setScene(instructionScene);
					instructionStage.setResizable(false);

		//Functionality
			//Instructions
				
				instructButton.setOnAction(e -> {
					instructionStage.show();
				});

			//Static Calculation
				calculateStatic.setOnAction(e -> {
					SolarUAV suav = new SolarUAV();

					String cdo = dragField.getText();
					String weight = weightField.getText();
					String reynolds = reynoldField.getText();
					String wingspan = spanField.getText();
					String speed = speedField.getText();

					if (cdo.equals("")) {
						suav.setSpan(Double.parseDouble(wingspan));
						suav.setWeight(Double.parseDouble(weight));
						suav.setReynold(Double.parseDouble(reynolds));
						suav.setSpeed(Double.parseDouble(speed));

						dragField.setText(Double.toString(suav.calcDragCoeff()));
					} else if (weight.equals("")) {
						suav.setReynold(Double.parseDouble(reynolds));
						suav.setSpeed(Double.parseDouble(speed));
						suav.setCdo(Double.parseDouble(cdo));
						suav.setSpan(Double.parseDouble(wingspan));

						weightField.setText(Double.toString(suav.calcWeight()));
					} else if (reynolds.equals("")) {
						suav.setSpeed(Double.parseDouble(speed));
						suav.setCdo(Double.parseDouble(cdo));
						suav.setSpan(Double.parseDouble(wingspan));
						suav.setWeight(Double.parseDouble(weight));

						reynoldField.setText(Double.toString(suav.calcReynolds()));
					} else if (wingspan.equals("")) {
						suav.setReynold(Double.parseDouble(reynolds));
						suav.setSpeed(Double.parseDouble(speed));
						suav.setCdo(Double.parseDouble(cdo));
						suav.setWeight(Double.parseDouble(weight));

						spanField.setText(Double.toString(suav.calcSpan()));
					} else if (speed.equals("")) {
						suav.setReynold(Double.parseDouble(reynolds));
						suav.setCdo(Double.parseDouble(cdo));
						suav.setWeight(Double.parseDouble(weight));
						suav.setSpan(Double.parseDouble(wingspan));

						speedField.setText(Double.toString(suav.calcCruiseSpeed()));
					}
				});
					
			//Graph Calculation
				//Combo Box
					yBox.valueProperty().addListener(new ChangeListener<String>() {
						@Override public void changed(ObservableValue ov, String t, String t1) {
							
							SolarUAV suav = new SolarUAV();



							xBox.getItems().add(taken.getText());
							xBox.getItems().remove("");
							xBox.getItems().remove(t1);
							taken.setText(t1);

							VBox rightSide = new VBox(10);

								VBox graphBox = new VBox(10);
							
									NumberAxis xAxis = new NumberAxis();
									NumberAxis yAxis = new NumberAxis();

									LineChart<Number, Number> lineChart =
										new LineChart<Number, Number>(xAxis, yAxis);

									lineChart.setCreateSymbols(false);

									int i;

									if (xBox.getValue().equals("X-Value")
										|| (!yBox.getValue().equals("Y-Value") && xBox.getValue().equals("X-Value"))) {
										graphBox.getChildren().clear();
										lineChart = new LineChart<Number, Number>(xAxis, yAxis);
										graphBox.getChildren().add(lineChart);
									} else if (yBox.getValue().equals("Cd,o")) {
										if (xBox.getValue().equals("Speed")) {

											xAxis.setLabel("Speed in mph");
											yAxis.setLabel("Cd,o");

											XYChart.Series series = new XYChart.Series();
											series.setName("Cd,o Vs. Speed");

											double upper = 2.0 * Double.parseDouble(speedField.getText());
											double lower = 0.5 * Double.parseDouble(speedField.getText());
											double increment = Double.parseDouble(speedField.getText()) / 50.0;

											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double speed = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpeed(speed);
												series.getData().add(new XYChart.Data(speed, suav.calcDragCoeff()));
												speed = speed + increment;
											}

											lineChart.getData().add(series);

										} else if (xBox.getValue().equals("Weight")) {
											
											xAxis.setLabel("Weight in pounds");
											yAxis.setLabel("Cd,o");

											XYChart.Series series = new XYChart.Series();
											series.setName("Cd,o Vs. Weight");

											double upper = 2.0 * Double.parseDouble(weightField.getText());
											double lower = 0.5 * Double.parseDouble(weightField.getText());
											double increment = Double.parseDouble(weightField.getText()) / 50.0;

											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double weight = lower;

											for (i = 0; i < 100; i++) {
												suav.setWeight(weight);
												series.getData().add(new XYChart.Data(weight, suav.calcDragCoeff()));
												weight = weight + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Wingspan")) {
											
											xAxis.setLabel("Wingspan in feet");
											yAxis.setLabel("Cd,o");

											XYChart.Series series = new XYChart.Series();
											series.setName("Cd,o Vs. Wingspan");

											double upper = 2.0 * Double.parseDouble(spanField.getText());
											double lower = 0.5 * Double.parseDouble(spanField.getText());
											double increment = Double.parseDouble(spanField.getText()) / 50.0;

											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double span = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpan(span);
												series.getData().add(new XYChart.Data(span, suav.calcDragCoeff()));
												span = span + increment;
											}

											lineChart.getData().add(series);
										}
									} else if (yBox.getValue().equals("Speed")) {
										if (xBox.getValue().equals("Cd,o")) {
											
											xAxis.setLabel("Cd,o");
											yAxis.setLabel("Speed in mph");

											XYChart.Series series = new XYChart.Series();
											series.setName("Speed Vs. Cd,o");

											double upper = 2.0 * Double.parseDouble(dragField.getText());
											double lower = 0.5 * Double.parseDouble(dragField.getText());
											double increment = Double.parseDouble(dragField.getText()) / 50.0;

											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double coeff = lower;

											for (i = 0; i < 100; i++) {
												suav.setCdo(coeff);
												series.getData().add(new XYChart.Data(coeff, suav.calcCruiseSpeed()));
												coeff = coeff + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Weight")) {

											xAxis.setLabel("Weight in pounds");
											yAxis.setLabel("Speed in mph");

											XYChart.Series series = new XYChart.Series();
											series.setName("Speed Vs. Weight");

											double upper = 2.0 * Double.parseDouble(weightField.getText());
											double lower = 0.5 * Double.parseDouble(weightField.getText());
											double increment = Double.parseDouble(weightField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double weight = lower;

											for (i = 0; i < 100; i++) {
												suav.setWeight(weight);
												series.getData().add(new XYChart.Data(weight, suav.calcCruiseSpeed()));
												weight = weight + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Wingspan")) {

											xAxis.setLabel("Wingspan in feet");
											yAxis.setLabel("Speed in mph");

											XYChart.Series series = new XYChart.Series();
											series.setName("Speed Vs. Wingspan");

											double upper = 2.0 * Double.parseDouble(spanField.getText());
											double lower = 0.5 * Double.parseDouble(spanField.getText());
											double increment = Double.parseDouble(spanField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double span = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpan(span);
												series.getData().add(new XYChart.Data(span, suav.calcCruiseSpeed()));
												span = span + increment;
											}

											lineChart.getData().add(series);
										}
									} else if (yBox.getValue().equals("Weight")) {
										if (xBox.getValue().equals("Cd,o")) {

											xAxis.setLabel("Cd,o");
											yAxis.setLabel("Weight in Pounds");

											XYChart.Series series = new XYChart.Series();
											series.setName("Weight Vs. Cd,o");

											double upper = 2.0 * Double.parseDouble(dragField.getText());
											double lower = 0.5 * Double.parseDouble(dragField.getText());
											double increment = Double.parseDouble(dragField.getText()) / 50.0;

											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double coeff = lower;

											for (i = 0; i < 100; i++) {
												suav.setCdo(coeff);
												series.getData().add(new XYChart.Data(coeff, suav.calcWeight()));
												coeff = coeff + increment;
											}

											lineChart.getData().add(series);
										}	
										} else if (xBox.getValue().equals("Speed")) {

											xAxis.setLabel("Speed in mph");
											yAxis.setLabel("Weight in Pounds");

											XYChart.Series series = new XYChart.Series();
											series.setName("Weight Vs. Speed");

											double upper = 2.0 * Double.parseDouble(speedField.getText());
											double lower = 0.5 * Double.parseDouble(speedField.getText());
											double increment = Double.parseDouble(speedField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double speed = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpeed(speed);
												series.getData().add(new XYChart.Data(speed, suav.calcWeight()));
												speed = speed + increment;
											}

											lineChart.getData().add(series);

										} else if (xBox.getValue().equals("Wingspan")) {

											xAxis.setLabel("Wingspan in Feet");
											yAxis.setLabel("Weight in Pounds");

											XYChart.Series series = new XYChart.Series();
											series.setName("Weight Vs. Wingspan");

											double upper = 2.0 * Double.parseDouble(spanField.getText());
											double lower = 0.5 * Double.parseDouble(spanField.getText());
											double increment = Double.parseDouble(spanField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double span = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpan(span);
												series.getData().add(new XYChart.Data(span, suav.calcWeight()));
												span = span + increment;
											}

											lineChart.getData().add(series);

									} else if (yBox.getValue().equals("Wingspan")) {
										if (xBox.getValue().equals("Cd,o")) {
											
											xAxis.setLabel("Cd,o");
											yAxis.setLabel("Wingspan in Feet");

											XYChart.Series series = new XYChart.Series();
											series.setName("Wingspan Vs. Cd,o");

											double upper = 2.0 * Double.parseDouble(dragField.getText());
											double lower = 0.5 * Double.parseDouble(dragField.getText());
											double increment = Double.parseDouble(dragField.getText()) / 50.0;

											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double coeff = lower;

											for (i = 0; i < 100; i++) {
												suav.setCdo(coeff);
												series.getData().add(new XYChart.Data(coeff, suav.calcSpan()));
												coeff = coeff + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Speed")) {

											xAxis.setLabel("Speed in mph");
											yAxis.setLabel("Wingspan in Feet");

											XYChart.Series series = new XYChart.Series();
											series.setName("Wingspan Vs. Speed");

											double upper = 2.0 * Double.parseDouble(speedField.getText());
											double lower = 0.5 * Double.parseDouble(speedField.getText());
											double increment = Double.parseDouble(speedField.getText()) / 50.0;

											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double speed = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpeed(speed);
												series.getData().add(new XYChart.Data(speed, suav.calcSpan()));
												speed = speed + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Weight")) {
											xAxis.setLabel("Weight in pounds");
											yAxis.setLabel("Wingspan in Feet");

											XYChart.Series series = new XYChart.Series();
											series.setName("Wingspan Vs. Weight");

											double upper = 2.0 * Double.parseDouble(weightField.getText());
											double lower = 0.5 * Double.parseDouble(weightField.getText());
											double increment = Double.parseDouble(weightField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double weight = lower;

											for (i = 0; i < 100; i++) {
												suav.setWeight(weight);
												series.getData().add(new XYChart.Data(weight, suav.calcSpan()));
												weight = weight + increment;
											}

											lineChart.getData().add(series);
										}
									}
								graphBox.getChildren().add(lineChart);
							rightSide.getChildren().addAll(rightTitleBox, graphBox);
							window.setRight(rightSide);
							xBox.requestFocus();
						}
					});

					xBox.valueProperty().addListener(new ChangeListener<String>() {
						@Override public void changed(ObservableValue ov, String t, String t1) {
							
							SolarUAV suav = new SolarUAV();

							VBox rightSide = new VBox(10);

								VBox graphBox = new VBox(10);
							
									NumberAxis xAxis = new NumberAxis();
									NumberAxis yAxis = new NumberAxis();

									LineChart<Number, Number> lineChart =
										new LineChart<Number, Number>(xAxis, yAxis);

									lineChart.setCreateSymbols(false);

									int i;

									if (xBox.getValue() == null) {
										graphBox.getChildren().add(lineChart);
									} else if (yBox.getValue().equals("Cd,o")) {
										if (xBox.getValue().equals("Speed")) {

											xAxis.setLabel("Speed in mph");
											yAxis.setLabel("Cd,o");

											XYChart.Series series = new XYChart.Series();
											series.setName("Cd,o Vs. Speed");

											double upper = 2.0 * Double.parseDouble(speedField.getText());
											double lower = 0.5 * Double.parseDouble(speedField.getText());
											double increment = Double.parseDouble(speedField.getText()) / 50.0;

											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double speed = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpeed(speed);
												series.getData().add(new XYChart.Data(speed, suav.calcDragCoeff()));
												speed = speed + increment;
											}

											lineChart.getData().add(series);

										} else if (xBox.getValue().equals("Weight")) {
											
											xAxis.setLabel("Weight in pounds");
											yAxis.setLabel("Cd,o");

											XYChart.Series series = new XYChart.Series();
											series.setName("Cd,o Vs. Weight");

											double upper = 2.0 * Double.parseDouble(weightField.getText());
											double lower = 0.5 * Double.parseDouble(weightField.getText());
											double increment = Double.parseDouble(weightField.getText()) / 50.0;

											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double weight = lower;

											for (i = 0; i < 100; i++) {
												suav.setWeight(weight);
												series.getData().add(new XYChart.Data(weight, suav.calcDragCoeff()));
												weight = weight + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Wingspan")) {
											
											xAxis.setLabel("Wingspan in feet");
											yAxis.setLabel("Cd,o");

											XYChart.Series series = new XYChart.Series();
											series.setName("Cd,o Vs. Wingspan");

											double upper = 2.0 * Double.parseDouble(spanField.getText());
											double lower = 0.5 * Double.parseDouble(spanField.getText());
											double increment = Double.parseDouble(spanField.getText()) / 50.0;

											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double span = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpan(span);
												series.getData().add(new XYChart.Data(span, suav.calcDragCoeff()));
												span = span + increment;
											}

											lineChart.getData().add(series);
										}
									} else if (yBox.getValue().equals("Speed")) {
										if (xBox.getValue().equals("Cd,o")) {
											
											xAxis.setLabel("Cd,o");
											yAxis.setLabel("Speed in mph");

											XYChart.Series series = new XYChart.Series();
											series.setName("Speed Vs. Cd,o");

											double upper = 2.0 * Double.parseDouble(dragField.getText());
											double lower = 0.5 * Double.parseDouble(dragField.getText());
											double increment = Double.parseDouble(dragField.getText()) / 50.0;

											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double coeff = lower;

											for (i = 0; i < 100; i++) {
												suav.setCdo(coeff);
												series.getData().add(new XYChart.Data(coeff, suav.calcCruiseSpeed()));
												coeff = coeff + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Weight")) {

											xAxis.setLabel("Weight in pounds");
											yAxis.setLabel("Speed in mph");

											XYChart.Series series = new XYChart.Series();
											series.setName("Speed Vs. Weight");

											double upper = 2.0 * Double.parseDouble(weightField.getText());
											double lower = 0.5 * Double.parseDouble(weightField.getText());
											double increment = Double.parseDouble(weightField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double weight = lower;

											for (i = 0; i < 100; i++) {
												suav.setWeight(weight);
												series.getData().add(new XYChart.Data(weight, suav.calcCruiseSpeed()));
												weight = weight + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Wingspan")) {

											xAxis.setLabel("Wingspan in feet");
											yAxis.setLabel("Speed in mph");

											XYChart.Series series = new XYChart.Series();
											series.setName("Speed Vs. Wingspan");

											double upper = 2.0 * Double.parseDouble(spanField.getText());
											double lower = 0.5 * Double.parseDouble(spanField.getText());
											double increment = Double.parseDouble(spanField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double span = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpan(span);
												series.getData().add(new XYChart.Data(span, suav.calcCruiseSpeed()));
												span = span + increment;
											}

											lineChart.getData().add(series);
										}
									} else if (yBox.getValue().equals("Weight")) {
										if (xBox.getValue().equals("Cd,o")) {

											xAxis.setLabel("Cd,o");
											yAxis.setLabel("Weight in Pounds");

											XYChart.Series series = new XYChart.Series();
											series.setName("Weight Vs. Cd,o");

											double upper = 2.0 * Double.parseDouble(dragField.getText());
											double lower = 0.5 * Double.parseDouble(dragField.getText());
											double increment = Double.parseDouble(dragField.getText()) / 50.0;

											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double coeff = lower;

											for (i = 0; i < 100; i++) {
												suav.setCdo(coeff);
												series.getData().add(new XYChart.Data(coeff, suav.calcWeight()));
												coeff = coeff + increment;
											}

											lineChart.getData().add(series);
	
										} else if (xBox.getValue().equals("Speed")) {

											xAxis.setLabel("Speed in mph");
											yAxis.setLabel("Weight in Pounds");

											XYChart.Series series = new XYChart.Series();
											series.setName("Weight Vs. Speed");

											double upper = 2.0 * Double.parseDouble(speedField.getText());
											double lower = 0.5 * Double.parseDouble(speedField.getText());
											double increment = Double.parseDouble(speedField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setSpan(Double.parseDouble(spanField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double speed = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpeed(speed);
												series.getData().add(new XYChart.Data(speed, suav.calcWeight()));
												speed = speed + increment;
											}

											lineChart.getData().add(series);

										} else if (xBox.getValue().equals("Wingspan")) {

											xAxis.setLabel("Wingspan in Feet");
											yAxis.setLabel("Weight in Pounds");

											XYChart.Series series = new XYChart.Series();
											series.setName("Weight Vs. Wingspan");

											double upper = 2.0 * Double.parseDouble(spanField.getText());
											double lower = 0.5 * Double.parseDouble(spanField.getText());
											double increment = Double.parseDouble(spanField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double span = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpan(span);
												series.getData().add(new XYChart.Data(span, suav.calcWeight()));
												span = span + increment;
											}

											lineChart.getData().add(series);
										}

									} else if (yBox.getValue().equals("Wingspan")) {
										if (xBox.getValue().equals("Cd,o")) {
											
											xAxis.setLabel("Cd,o");
											yAxis.setLabel("Wingspan in Feet");

											XYChart.Series series = new XYChart.Series();
											series.setName("Wingspan Vs. Cd,o");

											double upper = 2.0 * Double.parseDouble(dragField.getText());
											double lower = 0.5 * Double.parseDouble(dragField.getText());
											double increment = Double.parseDouble(dragField.getText()) / 50.0;

											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double coeff = lower;

											for (i = 0; i < 100; i++) {
												suav.setCdo(coeff);
												series.getData().add(new XYChart.Data(coeff, suav.calcSpan()));
												coeff = coeff + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Speed")) {

											xAxis.setLabel("Speed in mph");
											yAxis.setLabel("Wingspan in Feet");

											XYChart.Series series = new XYChart.Series();
											series.setName("Wingspan Vs. Speed");

											double upper = 2.0 * Double.parseDouble(speedField.getText());
											double lower = 0.5 * Double.parseDouble(speedField.getText());
											double increment = Double.parseDouble(speedField.getText()) / 50.0;

											suav.setWeight(Double.parseDouble(weightField.getText()));
											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double speed = lower;

											for (i = 0; i < 100; i++) {
												suav.setSpeed(speed);
												series.getData().add(new XYChart.Data(speed, suav.calcSpan()));
												speed = speed + increment;
											}

											lineChart.getData().add(series);
										} else if (xBox.getValue().equals("Weight")) {
											xAxis.setLabel("Weight in pounds");
											yAxis.setLabel("Wingspan in Feet");

											XYChart.Series series = new XYChart.Series();
											series.setName("Wingspan Vs. Weight");

											double upper = 2.0 * Double.parseDouble(weightField.getText());
											double lower = 0.5 * Double.parseDouble(weightField.getText());
											double increment = Double.parseDouble(weightField.getText()) / 50.0;

											suav.setCdo(Double.parseDouble(dragField.getText()));
											suav.setSpeed(Double.parseDouble(speedField.getText()));
											suav.setReynold(Double.parseDouble(reynoldField.getText()));

											double weight = lower;

											for (i = 0; i < 100; i++) {
												suav.setWeight(weight);
												series.getData().add(new XYChart.Data(weight, suav.calcSpan()));
												weight = weight + increment;
											}

											lineChart.getData().add(series);
										}
									}
								graphBox.getChildren().add(lineChart);
							rightSide.getChildren().addAll(rightTitleBox, graphBox);
							window.setRight(rightSide);
							yBox.requestFocus();
						}
					});
	}
}