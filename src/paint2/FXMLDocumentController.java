/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2;

import Dialog.MyDialog;
import Dialog.Paire;
import Shapes.Figure;

import Shapes.SEllipse;
import Shapes.SLine;
import Shapes.SRectangle;

import dom.XmlReader;
import dom.XmlWriter;
import java.awt.Point;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 *
 * @author CHABBAB
 */
public class FXMLDocumentController implements Initializable {
    
    private String draw = "";
    public static final int canvasWidth = 720;
    public static final int canvasHeight = 600;
    public static final int canvasX = 110;
    public static final int canvasY = 20;
    int orgSceneX, orgSceneY;
    int orgTranslateX, orgTranslateY;
   
    SLine line ;
    
    private Boolean actionCursor =true ;
    private Boolean actionDraw = false;
    public static Boolean actionMove  = false;
    private ArrayList<Figure> ListFig = new ArrayList<Figure>();
    
    
   
   
    @FXML
    private AnchorPane mycanvas;
    
    @FXML
    private AnchorPane prop;
    
    @FXML
    private Button cursor;
    
    @FXML
    private Button move;
        
    @FXML
    private Button line_btn;

    @FXML
    private Button rect_btn;

    @FXML
    private Button ellipse_btn;

    @FXML
    private ColorPicker cpLine;

    @FXML
    private ColorPicker cpFill;
    
    
    
  
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Figure.canvas = mycanvas;
        Rectangle r = new Rectangle( 720,600);
        r.setFill(Color.BLACK);
        mycanvas.setClip(r);
        mycanvas.setOnMouseClicked(canvasOnMouseClickedEventHandler);
        mycanvas.setOnMouseDragged(canvasOnMouseDraggedEventHandler);
        mycanvas.setOnMousePressed(canvasOnMousePressedEventHandler);
       
        
        
    }    
    
   
    
    /**
     * Drawing the shapes
     */
    @FXML
    private void drawRect(ActionEvent event) {
        System.out.println("Draw Rect!");
        draw = "rectangle";
        actionDraw = true;
        actionMove = false;

        mycanvas.setCursor(Cursor.CROSSHAIR);
    }
    @FXML
    private void drawEllipse(ActionEvent event) {
        System.out.println("Draw Ellipse!");
        draw = "ellipse";
        actionDraw = true;
        actionMove = false;

        mycanvas.setCursor(Cursor.CROSSHAIR);
      
    }
    
    @FXML
    private void export(ActionEvent event) {
        System.out.println("Cursor!");
        //XmlPrinter printer = new XmlPrinter(listRect, listLine, listEll);
        
        
        FileChooser fileChooser = new FileChooser();

         //Set extension filter
         FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
         fileChooser.getExtensionFilters().add(extFilter);

         //Show save file dialog
         File file = fileChooser.showSaveDialog(mycanvas.getScene().getWindow());

         if(file != null){
             XmlWriter printer = new XmlWriter(ListFig, file);
             printer.print();
         }
        
    }
    
    
    @FXML
    private void Move(ActionEvent event) {
        actionCursor = false;
        actionDraw = false;
        actionMove = true;
        mycanvas.setCursor(Cursor.MOVE);
        
    }
    
    @FXML
    private void importFile(ActionEvent event) {
        //XmlReader reader = new XmlReader();
        
        FileChooser fileChooser = new FileChooser();

         //Set extension filter
         FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
         fileChooser.getExtensionFilters().add(extFilter);

         //Show save file dialog
         File file = fileChooser.showOpenDialog(mycanvas.getScene().getWindow());

         if(file != null){
             XmlReader reader = new XmlReader( file);
             ListFig = reader.read();
             for(Figure f : ListFig){
                 f.draw();
             }
         }
    }
    
    @FXML
    private void cursor(ActionEvent event) {
        System.out.println("Cursor!");
        actionCursor = true;
        actionMove = false;
        mycanvas.setCursor(Cursor.DEFAULT);
      
    }
    
    @FXML
    private void drawLine(ActionEvent event) {
        System.out.println("Draw Line!");
        draw = "line";
        actionDraw = true;
        actionMove = false;
        mycanvas.setCursor(Cursor.CROSSHAIR);
      
    }
    
    
    
    @FXML
    private void draw(ActionEvent event) {
        System.out.println("Draw !");
        for(int i = 0; i < ListFig.size() ;  i++){
            System.out.println("Draw : "+i);
            ListFig.get(i).draw();
            
        }
      
      
    }
    
     EventHandler<MouseEvent> canvasOnMouseClickedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent ev)
        {
            
            if(actionDraw){
                if(draw.equals("rectangle")){
                    MyDialog d = new MyDialog("Width", "Height");
                    d.setTitle("New Rectangle");
                    d.setHeaderText("Enter the 'Width' and the 'Height' :");
                    List<Paire> l = d.show();
                    
                    if(l != null){
                       
                        int w = 0,h = 0;
                        for(Paire p : l){
                            if(p.getKey().equals("Width")){
                                w = p.getValue();
                                System.out.println("Width : "+w);
                            }else if(p.getKey().equals("Height")){
                                h = p.getValue();
                                System.out.println("Height : "+h);
                            }
                        }
                        SRectangle r = new SRectangle((int)ev.getX(), (int)ev.getY(), w, h, cpFill.getValue(), cpLine.getValue());
                        ListFig.add(r);
                        r.draw();
                               
                    }
                } else if(draw.equals("ellipse")){
                    MyDialog d = new MyDialog("Radius X", "Radius Y");
                    d.setTitle("New Ellipse");
                    d.setHeaderText("Enter the 'Radius' for both X and Y: \n(in case of Circle X and Y have the same value)");
                    List<Paire> l  = d.show();
                    
                    if(l != null){
                        
                        int rx = 0,ry = 0;
                        for(Paire p : l){
                            if(p.getKey().equals("Radius X")){
                                rx = p.getValue();
                                System.out.println("Width : "+rx);
                            }else if(p.getKey().equals("Radius Y")){
                                ry = p.getValue();
                                System.out.println("Height : "+ry);
                            }
                        }
                       
                        SEllipse e = new SEllipse((int)ev.getX(), (int)ev.getY(), rx, ry, cpFill.getValue(), cpLine.getValue());
                        ListFig.add(e);
                        e.draw();
                               
                    } 
                }
            }

        }
    };

    EventHandler<MouseEvent> canvasOnMousePressedEventHandler = new EventHandler<MouseEvent>()
    {
        
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            orgSceneX = (int) mouseEvent.getSceneX() - canvasX;
            orgSceneY = (int) mouseEvent.getSceneY() - canvasY;
            if(actionDraw){
                if(draw.equals("line")){
                    line = new SLine(orgSceneX, orgSceneY, orgSceneX, orgSceneY, cpLine.getValue());
                    line.draw();
                    ListFig.add(line);
                }
                
            }
            

        }
    };

    EventHandler<MouseEvent> canvasOnMouseDraggedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            int x = (int) mouseEvent.getSceneX() - canvasX;
            int y = (int) mouseEvent.getSceneY() - canvasY;
            if(actionDraw){
                if(draw.equals("line")){
                

                    if((x > 0) && (x < (canvasWidth))){
                        line.setExtremity2(x,line.getExtremity2().y);
                    }

                    if((y > 0) && (y < (canvasHeight))){
                        line.setExtremity2(line.getExtremity2().x,y);
                    }
                }
            }
        }
    };
    
    
}
