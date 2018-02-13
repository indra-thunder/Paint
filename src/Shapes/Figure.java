package Shapes;



import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



public abstract class Figure {
        public static AnchorPane canvas = null;
        
        protected Shape shape;
	protected Color FillColor ;
        protected Color StrokeColor ;
        protected final int StrokeWidth = 3;
        int orgSceneX, orgSceneY;
        int orgTranslateX, orgTranslateY;
       
	protected String name;
	public Figure(){
        }
      
       
	public void setColor(Color fc, Color sc){
		shape.setFill(fc); 
                shape.setStroke(sc);
                
                
	}
	public String getName(){
		return name;
	}
	public Color getFillColor(){
		return (Color) shape.getFill();
	}
        public Color getStrokeColor(){
		return (Color) shape.getStroke();
	}
        public void setStrokeColor(Color sc){
		 shape.setStroke(sc);
	}
        public void setFillColor(Color fc){
		shape.setFill(fc); 
	}
        
        public int getStrokeWidth(){
            return StrokeWidth;
        }
	public void draw(){
           
            shape.setStrokeWidth(StrokeWidth);
           
            canvas.getChildren().add(shape);
        }
	
	public abstract TableView Properties();

    
}
