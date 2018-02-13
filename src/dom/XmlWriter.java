/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom;

import Shapes.SEllipse;
import Shapes.Figure;
import Shapes.SLine;
import Shapes.SRectangle;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author CHABBAB
 */
public class XmlWriter {
    private Element rootFigures;
    private List<Figure> listFigure;
    private File myFile;
   
    private DocumentBuilderFactory docFactory ;
    private DocumentBuilder docBuilder ;
    private Document doc ;
    
    public XmlWriter(){
        
    }
    
    public XmlWriter( List<Figure> listFigure, File f  ){
        
            myFile = f;
            this.listFigure = listFigure;
            
           
    }
    
    
    public void printRectangle(SRectangle rect){
        
        
        
            Element figure = doc.createElement("figure");
            figure.setAttribute("type","rectangle");
            Element rectangle = doc.createElement("rectangle");

            
            rectangle.setAttribute("x",(int)rect.getX()+"");
            rectangle.setAttribute("y",(int)rect.getY()+"");
           

           
            rectangle.setAttribute("width",(int)rect.getWidth()+"");
            rectangle.setAttribute("height",(int)rect.getHeight()+"");
            

           
            rectangle.setAttribute("fill",(rect.getFillColor()+""));
            rectangle.setAttribute("stroke",(rect.getStrokeColor()+""));
            rectangle.setAttribute("stroke-width",rect.getStrokeWidth()+"");
         

            figure.appendChild(rectangle);
            rootFigures.appendChild(figure);
        
        
        
           
    }
    
    
    public void printEllipse(SEllipse el){
       
       
      
            Element figure = doc.createElement("figure");
            figure.setAttribute("type","ellipse");
            Element ellipse = doc.createElement("ellipse");

          
            ellipse.setAttribute("cx",(int)el.getX()+"");
            ellipse.setAttribute("cy",(int)el.getY()+"");
        

            
            ellipse.setAttribute("rx",(int)el.getRayonX()+"");
            ellipse.setAttribute("ry",(int)el.getRayonY()+"");
          
           
            ellipse.setAttribute("fill",(el.getFillColor()+""));
            ellipse.setAttribute("stroke",(el.getStrokeColor()+""));
            ellipse.setAttribute("stroke-width",el.getStrokeWidth()+"");
           


            figure.appendChild(ellipse);
            rootFigures.appendChild(figure);
        
        
        
           
    }
    
    
    
    public void printLine(SLine l){
       
       
        
            Element figure = doc.createElement("figure");
            figure.setAttribute("type","line");
            Element line = doc.createElement("line");

           
            
            line.setAttribute("x1",(int)l.getExtremity1().x+"");
            line.setAttribute("y1",(int)l.getExtremity1().y+"");
            
            line.setAttribute("x2",(int)l.getExtremity2().x+"");
            line.setAttribute("y2",(int)l.getExtremity2().y+"");
            
        
            
            

            
            line.setAttribute("stroke",(l.getStrokeColor()+""));
            line.setAttribute("stroke-width",l.getStrokeWidth()+"");
           

            figure.appendChild(line);
        
        
            rootFigures.appendChild(figure);
           
    }
    
    public void init(){
        try {
                  
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            
            rootFigures = doc.createElement("figures");
            doc.appendChild(rootFigures);
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void dispatch(){
        for(Figure f :  listFigure){
            if( f instanceof SRectangle){
                printRectangle((SRectangle) f);
            }else if( f instanceof SEllipse){
                printEllipse((SEllipse) f);
            }else if( f instanceof SLine){
                printLine((SLine) f);
            }
        }
        
        
    }
    
    
    public void print(){
        init();
        dispatch();
        
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(myFile);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
