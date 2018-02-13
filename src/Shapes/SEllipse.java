package Shapes;



import Dialog.Paire;
import java.awt.Point;
import java.lang.Math;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import paint2.FXMLDocumentController;

public class SEllipse extends Figure {
    private Point pos = new Point();
    private Ellipse e ;
    private int rx;
    private int ry;


    public SEllipse(){
        e = new Ellipse();
    }

    public SEllipse( int x, int y, int rx, int ry , Color fc, Color sc){
        e = new Ellipse(x,y,rx,ry);
        e.setOnMousePressed(canvasOnMousePressedEventHandler);
        e.setOnMouseDragged(canvasOnMouseDraggedEventHandler);
        shape = e;
        setColor(fc, sc);
    }

    public void setLocation(int y, int x){
            e.setCenterX(x);
            e.setCenterY(y);
    }

    public void setRayonX(int r){
            e.setRadiusX(r);
    }
    public void setRayonY(int r){
            e.setRadiusY(r);
    }

    public int getX(){

            return (int) e.getCenterX();

    }
    public int getY(){

            return (int) e.getCenterY();

    }
    public int getRayonY(){
            return (int) e.getRadiusY();
    }

    public int getRayonX(){
            return (int) e.getRadiusX();
    }



    public Shape getobj(){
        return shape;
    }

    EventHandler<MouseEvent> canvasOnMousePressedEventHandler = new EventHandler<MouseEvent>()
    {
        
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            if(FXMLDocumentController.actionMove){
                orgSceneX = (int) mouseEvent.getSceneX();
                orgSceneY = (int) mouseEvent.getSceneY();
                orgTranslateX = (int) ((Ellipse)(mouseEvent.getSource())).getTranslateX();
                orgTranslateY = (int) ((Ellipse)(mouseEvent.getSource())).getTranslateY();
            }
            

        }
    };

    EventHandler<MouseEvent> canvasOnMouseDraggedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            if(FXMLDocumentController.actionMove){
                int x = (int) mouseEvent.getSceneX();
                int y = (int) mouseEvent.getSceneY();

                if((x > FXMLDocumentController.canvasX) && (x < (FXMLDocumentController.canvasWidth + FXMLDocumentController.canvasX))){
                    int offsetX = x - orgSceneX;
                    int newTranslateX = orgTranslateX + offsetX;
                    ((Ellipse)(mouseEvent.getSource())).setTranslateX(newTranslateX);
                }

                if((y > FXMLDocumentController.canvasY) && (y < (FXMLDocumentController.canvasHeight + FXMLDocumentController.canvasY))){
                    int offsetY = y - orgSceneY;
                    int newTranslateY = orgTranslateY + offsetY;
                    ((Ellipse)(mouseEvent.getSource())).setTranslateY(newTranslateY);
                }
            }
        }
    };
    
    
    
    @Override
    public TableView Properties() {
        
        
        
        return null;
    }
}
