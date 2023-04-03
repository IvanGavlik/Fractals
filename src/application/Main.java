package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *  https://fractalfoundation.org/OFC/OFC-2-1.html
 *  @author Ivan Gavlik
 */
public class Main extends Application {
	
	private static final int INIT_STEP = 0;
	private static int stepMax = 1;
	
	private static Group triangles = new Group();
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox vBox = new VBox();
			
			vBox.getChildren().add(getHeader());
			
			initTriangles();
			vBox.getChildren().add(triangles);
			
			Scene scene = new Scene(vBox, 600, 651);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Sierpinski Fractal");
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	private static HBox getHeader() {
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10));
		hBox.setSpacing(5);
		
		final String STEP_INFO_FORMAT = "Sierpinski Fractal Step: %d"; 
		Label infoLabel = new Label(String.format(STEP_INFO_FORMAT, stepMax));
		
		Button nextStep = new Button("Next");
		nextStep.setOnAction(el -> {
			stepMax += 1;
			Triangle root = initTriangles();
			sierpinskiFractals(root, INIT_STEP, stepMax);
			infoLabel.setText(String.format(STEP_INFO_FORMAT, stepMax));
		});
		
		Button previusStep = new Button("Previus");
		previusStep.setOnAction(el -> {
			stepMax -= 1;
			if(stepMax < 1) {
				stepMax += 1;
				return;
			}
			Triangle root = initTriangles();
			sierpinskiFractals(root, INIT_STEP, stepMax);
			infoLabel.setText(String.format(STEP_INFO_FORMAT, stepMax));			
		});
		
		hBox.getChildren().addAll(infoLabel, nextStep, previusStep);		

		return hBox;
	}


	private static Triangle initTriangles() {
		int pointAX=  1; 
		int pointAY = 600;
		Point pointA = new Point(pointAX, pointAY);

		int pointBX =  600;
		int pointBY = 600;
		Point pointB = new Point(pointBX, pointBY);
		
		int pointCX = 300;
		int pointCY = 1;
		Point pointC = new Point(pointCX, pointCY);

		Triangle root =  new Triangle(pointA, pointB, pointC, Fill.RED);
		triangles.getChildren().clear();
		triangles.getChildren().add(root.getView());
		
		return root;
	}
	

	private static void sierpinskiFractals(Triangle triangle, int step, int stepMax) {
		step += 1;
		if(step == stepMax) {
			return;
		}
		
		Fill fill = triangle.getFill();
		if(fill.equals(Fill.RED)) {
			fill = Fill.WHITE;
		} else {
			fill = Fill.RED;
		}
				
		int pointDX= ( triangle.getPointC().getX() + triangle.getPointA().getX() ) / 2; 
		int pointDY = ( triangle.getPointA().getY() + triangle.getPointC().getY() ) / 2;
		Point pointD = new Point(pointDX, pointDY);

		int pointEX = ( triangle.getPointB().getX() + triangle.getPointC().getX() ) / 2;
		int pointEY = ( triangle.getPointB().getY() + triangle.getPointC().getY() ) / 2;
		Point pointE = new Point(pointEX, pointEY);
		
		int pointFX = ( triangle.getPointB().getX() + triangle.getPointA().getX() ) / 2;
		int pointFY = ( triangle.getPointB().getY() + triangle.getPointA().getY() ) / 2;
		Point pointF = new Point(pointFX, pointFY);
		
		Triangle t = new Triangle(pointD, pointE, pointF, fill);
		triangles.getChildren().add(t.getView());
		
	
		Triangle t1 = new Triangle(triangle.getPointA(), pointF, pointD, triangle.getFill());
		Triangle t2 = new Triangle(pointF, triangle.getPointB(), pointE, triangle.getFill());
		Triangle t3 = new Triangle(pointD, pointE, triangle.getPointC(), triangle.getFill());
		
		sierpinskiFractals(t1, step, stepMax);
		sierpinskiFractals(t2, step, stepMax);
		sierpinskiFractals(t3, step, stepMax);
		
	}
	
}
