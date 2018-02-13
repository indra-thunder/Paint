/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;




/**
 *
 * @author WeFree
 */
public class MyDialog {
    
    private  Dialog<List> dialog ;
    private List<Paire> listPaire;
    
    
    
    public MyDialog(String k1, String k2){
        listPaire = new ArrayList<Paire>();
        dialog = new Dialog<>();    
        ButtonType end = new ButtonType("End", ButtonData.OK_DONE);
      
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL,end);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField v1 = new TextField();
        v1.setPromptText(k1);
        TextField  v2 = new TextField ();
        v2.setPromptText(k2);
        
        grid.add(new Label(k1+ " :"), 0, 0);
        grid.add(v1, 1, 0);
        grid.add(new Label(k2+ " :"), 0, 1);
        grid.add(v2, 1, 1);
        
        
        
        
        
        
        
        
        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(dialogButton -> {
            System.out.println("in");
            if (dialogButton == end) {
                System.out.println("in2");
                String regex = "[0-9]+"; 
                String val1,val2;

                val1 = v1.getText().toString().trim();
                val2 = v2.getText().toString().trim();
                if(val1.matches(regex) && val2.matches(regex)){
                    System.out.println("in3");
                    int value1, value2;
                    value1 = Integer.parseInt(val1);
                    value2 = Integer.parseInt(val2);
                    if(value1 > 10 && value2 >10){
                        System.out.println("in4");
                        listPaire.add(new Paire(k1,value1));
                        listPaire.add(new Paire(k2,value2));
                        return  listPaire;
                    }
                }
            }
            return null;
         
});
        
    }
    
    public List<Paire> show(){
        Optional<List> o = dialog.showAndWait();
        if(o.isPresent()){
            List<Paire> l = o.get();
            return l;
        }
        return null;
    }
    
    public void setTitle(String t ){
        dialog.setTitle(t);
    }
    
    
    public void setHeaderText(String t){
        dialog.setHeaderText(t);
    }
    
    public void setIcon(ImageView img){
        dialog.setGraphic(img);
    }
    
    
}
