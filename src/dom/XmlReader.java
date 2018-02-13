/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom;
import Shapes.Figure;
import Shapes.SEllipse;
import Shapes.SLine;
import Shapes.SRectangle;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
/**
 *
 * @author CHABBAB
 */
public class XmlReader {
    File myFile ;
    DocumentBuilderFactory dbFactory ;
    DocumentBuilder dBuilder ;
    Document doc ;
    private ArrayList<Figure> ListFig = new ArrayList<Figure>();
    
    
    public XmlReader(File f){
       myFile = f;
    }
    
  
    
    public void init(){
        try{
            
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(myFile);
            doc.getDocumentElement().normalize();
            
            
                    
               

            
        
        }   catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }   
        
        
    
    public ArrayList<Figure> read(){
        init();
        NodeList nList = doc.getElementsByTagName("figure");
        for (int i = 0; i < nList.getLength(); i++) {

            Node node = nList.item(i);

            System.out.println("\nCurrent Element :" + node.getNodeName());
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Node childNode = node.getFirstChild();
                if(childNode.getNodeName().equals("rectangle")){
                    readRect(childNode);
                }else if(childNode.getNodeName().equals("ellipse")){
                    readEllipse(childNode);
                }else if(childNode.getNodeName().equals("line")){
                    readLine(childNode);
                }
                
        
            }
        }
        return ListFig;
        
    }
    
    
    
    public void readLine(Node node){
        System.out.println("\nCurrent Element :" + node.getNodeName());

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element line = (Element) node;
            SLine l = new SLine(Integer.parseInt(line.getAttribute("x1")), Integer.parseInt(line.getAttribute("y1")),Integer.parseInt(line.getAttribute("x2")), Integer.parseInt(line.getAttribute("y2")),Color.web(line.getAttribute("stroke")));
          
            
            ListFig.add(l);
            
            System.out.println("x1 = " + line.getAttribute("x1"));
            System.out.println("y1 = " + line.getAttribute("y1"));
            
            System.out.println("x2 = " + line.getAttribute("x2"));
            System.out.println("y2 = " + line.getAttribute("y2"));

            System.out.println("fill = " + line.getAttribute("fill"));
            System.out.println("stroke = " + line.getAttribute("stroke"));
            System.out.println("stroke-width = " + line.getAttribute("stroke-width"));
        }                              
    } 
    
    public void readEllipse(Node node){
        System.out.println("\nCurrent Element :" + node.getNodeName());

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element el = (Element) node;
            
            SEllipse l = new SEllipse(Integer.parseInt(el.getAttribute("cy")), Integer.parseInt(el.getAttribute("cx")),Integer.parseInt(el.getAttribute("rx")),Integer.parseInt(el.getAttribute("ry")),Color.web(el.getAttribute("fill")),Color.web(el.getAttribute("stroke")));
            
            ListFig.add(l);
            
            System.out.println("cx = " + el.getAttribute("cx"));
            System.out.println("cy = " + el.getAttribute("cy"));
            
            System.out.println("rx = " + el.getAttribute("rx"));
            System.out.println("ry = " + el.getAttribute("ry"));

            System.out.println("fill = " + el.getAttribute("fill"));
            System.out.println("stroke = " + el.getAttribute("stroke"));
            System.out.println("stroke-width = " + el.getAttribute("stroke-width"));
        }                              
    } 

    public void readRect(Node node){
        System.out.println("\nCurrent Element :" + node.getNodeName());

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element rect = (Element) node;
            
            SRectangle l = new SRectangle(Integer.parseInt(rect.getAttribute("x")), Integer.parseInt(rect.getAttribute("y")),Integer.parseInt(rect.getAttribute("width")),Integer.parseInt(rect.getAttribute("height")),Color.web(rect.getAttribute("fill")),Color.web(rect.getAttribute("stroke")));
            
            ListFig.add(l);
            
            
            
            System.out.println("x = " + rect.getAttribute("x"));
            System.out.println("y = " + rect.getAttribute("y"));
            
            System.out.println("width = " + rect.getAttribute("width"));
            System.out.println("height = " + rect.getAttribute("height"));

            System.out.println("fill = " + rect.getAttribute("fill"));
            System.out.println("stroke = " + rect.getAttribute("stroke"));
            System.out.println("stroke-width = " + rect.getAttribute("stroke-width"));
        }                              
    } 
}

    
    

