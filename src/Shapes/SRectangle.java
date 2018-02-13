package Shapes;


import java.awt.Point;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import paint2.FXMLDocumentController;

/**
 *
 * @author CHABBAB
 */
public class SRectangle extends Figure {

    private Rectangle r ;



    public SRectangle(){
        shape = new Rectangle();
    }

    public SRectangle(int x, int y, int w, int h, Color fc,Color sc){
            r = new Rectangle(x, y, w, h);
            r.setOnMousePressed(canvasOnMousePressedEventHandler);
            r.setOnMouseDragged(canvasOnMouseDraggedEventHandler);
            shape = r;
            setColor(fc, sc);
    }



    public void setLocation(int x, int y){
            r.setX(x);
            r.setY(y);
    }


    public void setDimension(int w, int h){
            r.setWidth(w);
            r.setHeight(h);
    }


    public int getX(){
        return (int) r.getX();
    }

    public int getY(){
        return (int) r.getY();
    }

    public int getWidth(){
            return (int) r.getWidth();
    }

    public int getHeight(){
            return (int) r.getHeight();
    }
    
    EventHandler<MouseEvent> canvasOnMousePressedEventHandler = new EventHandler<MouseEvent>()
    {
        
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            if(FXMLDocumentController.actionMove){
                orgSceneX = (int) mouseEvent.getSceneX();
                orgSceneY = (int) mouseEvent.getSceneY();
                orgTranslateX = (int) ((Rectangle)(mouseEvent.getSource())).getTranslateX();
                orgTranslateY = (int) ((Rectangle)(mouseEvent.getSource())).getTranslateY();
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
                    ((Rectangle)(mouseEvent.getSource())).setTranslateX(newTranslateX);
                }

                if((y > FXMLDocumentController.canvasY) && (y < (FXMLDocumentController.canvasHeight + FXMLDocumentController.canvasY))){
                    int offsetY = y - orgSceneY;
                    int newTranslateY = orgTranslateY + offsetY;
                    ((Rectangle)(mouseEvent.getSource())).setTranslateY(newTranslateY);
                }
            }
        }
    };

    @Override
    public TableView Properties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
