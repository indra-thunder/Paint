package Shapes;


import java.awt.Point;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import javafx.scene.shape.Line;
import paint2.FXMLDocumentController;

public class SLine extends Figure{
	private Line l  ;
        
	
	
	public SLine(){
            shape = new Line();
             
        }
	
	public SLine(int x1, int y1, int x2, int y2, Color sc){
            super();
            l = new Line(x1, y1, x2, y2);
            l.setStroke(sc);
            l.setOnMousePressed(canvasOnMousePressedEventHandler);
            l.setOnMouseDragged(canvasOnMouseDraggedEventHandler);
            shape = l;
            
            
	}
        
      
	
	public void setExtremity1(int x, int y){
		l.setStartX(x);
                l.setStartY(y);
		
	}
	
	public void setExtremity2(int x, int y){
		l.setEndX(x);
                l.setEndY(y);
	}
	
        
      
        
        public Point getExtremity1(){
            Point p = new Point((int)l.getStartX(),(int)l.getStartY());
            return p;
        }
        public Point getExtremity2(){
            Point p = new Point((int)l.getEndX(),(int)l.getEndY());
            return p;
        }
	
        
        
    EventHandler<MouseEvent> canvasOnMousePressedEventHandler = new EventHandler<MouseEvent>()
    {
        
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            orgSceneX = (int) mouseEvent.getSceneX();
            orgSceneY = (int) mouseEvent.getSceneY();
            if(FXMLDocumentController.actionMove){
                
                orgTranslateX = (int) ((Line)(mouseEvent.getSource())).getTranslateX();
                orgTranslateY = (int) ((Line)(mouseEvent.getSource())).getTranslateY();
            }
            

        }
    };

    EventHandler<MouseEvent> canvasOnMouseDraggedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            int x = (int) mouseEvent.getSceneX();
            int y = (int) mouseEvent.getSceneY();
            if(FXMLDocumentController.actionMove){
                

                if((x > FXMLDocumentController.canvasX) && (x < (FXMLDocumentController.canvasWidth + FXMLDocumentController.canvasX))){
                    int offsetX = x - orgSceneX;
                    int newTranslateX = orgTranslateX + offsetX;
                    ((Line)(mouseEvent.getSource())).setTranslateX(newTranslateX);
                }

                if((y > FXMLDocumentController.canvasY) && (y < (FXMLDocumentController.canvasHeight + FXMLDocumentController.canvasY))){
                    int offsetY = y - orgSceneY;
                    int newTranslateY = orgTranslateY + offsetY;
                    ((Line)(mouseEvent.getSource())).setTranslateY(newTranslateY);
                }
            }
        }
    };

    @Override
    public TableView Properties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

	
}
