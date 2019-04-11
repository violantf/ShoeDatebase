/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxshoedatabase;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author owner
 */
public class FXML_FormController implements Initializable {
    
    final static int RECORD_SIZE = (Shoe.ID_SIZE * 2) + 4 + 8;
    private RandomAccessFile raf;
    private ArrayList<Shoe> shoeNew;
    
    
    
    public void writeAddress(ArrayList<Shoe> arr) throws IOException {
        
        raf.setLength(0);
        
        for(Shoe s : arr) {
            try {
                
                raf.seek(raf.length());
                try {
                    FixedLengthStringIO.writeFixedLengthString(s.getName(), Shoe.NAME_SIZE, raf);
                    System.out.println(s.getName());
                    
                    FixedLengthStringIO.writeFixedLengthString(s.getBrand(), Shoe.BRAND_SIZE, raf);
                    System.out.println(s.getBrand());
                    
                    FixedLengthStringIO.writeFixedLengthString(s.getStyle(), Shoe.STYLE_SIZE, raf);
                    System.out.println(s.getStyle());
                    
                    raf.writeDouble(s.getSize());
                    System.out.println(s.getSize());
                    
                    raf.writeInt(s.getStock());
                    System.out.println(s.getStock());
                    
                    FixedLengthStringIO.writeFixedLengthString(s.getID(), Shoe.ID_SIZE, raf);
                    System.out.println(s.getID());
                    
                } catch (IOException | NumberFormatException e) {
                    System.out.println("Input Invalid");
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
    }
    
    
    
    public ArrayList<Shoe> readAddress() throws IOException {
        raf.seek(0);
        System.out.println("-----------");
        ArrayList<Shoe> tempNew = new ArrayList<>();        
        while(true) {
            try{
                Shoe s = new Shoe();
                
                String name = FixedLengthStringIO.readFixedLengthString(Shoe.NAME_SIZE, raf);
                System.out.println(raf.getFilePointer());
                
                String brand = FixedLengthStringIO.readFixedLengthString(Shoe.BRAND_SIZE, raf);
                System.out.println(raf.getFilePointer());
                
                String style = FixedLengthStringIO.readFixedLengthString(Shoe.STYLE_SIZE, raf);
                System.out.println(raf.getFilePointer());
                
                double size = raf.readDouble();
                System.out.println(raf.getFilePointer());
                
                int stock = raf.readInt();
                System.out.println(raf.getFilePointer());
                
                String ID = FixedLengthStringIO.readFixedLengthString(Shoe.ID_SIZE, raf);
                System.out.println(raf.getFilePointer());
                
                s.setName(name);
                s.setBrand(brand);
                s.setStyle(style);
                s.setSize(size);
                s.setStock(stock);
                s.setID();
                
                tempNew.add(tempNew.size(), s);
                

            } catch (EOFException e) {
                break;
            }
        }
        return tempNew;
    }
    
    @FXML private Label lblID, lblName, lblBrand, lblStyle, lblSize, lblStock, lblResult;
    
    @FXML private TextField txtID, txtName, txtBrand, txtStyle, txtStock;
    
    @FXML private ComboBox cmbSize;  
    
    @FXML private Button btnAdd, btnView, btnRemove, btnSave;
    
    @FXML 
    private void onSave(ActionEvent event) throws IOException {
        writeAddress(shoeNew);
        //System.out.println(shoeNew);
    }
    
    @FXML 
    private void onAdd(ActionEvent event) throws IOException {
        
         try {
            Shoe s = new Shoe();
            s.setName(txtName.getText());
            s.setBrand(txtBrand.getText().trim());
            s.setStyle(txtStyle.getText().trim());
            s.setSize(Double.parseDouble(cmbSize.getValue().toString()));
            s.setStock(Integer.parseInt(txtStock.getText()));
            s.setID();
            shoeNew.add(shoeNew.size(), s);
             System.out.println("Object[" + s.getID() + "] added");
        } catch (NumberFormatException ex) {
            System.out.println("Stock should be a Number");
        } catch (RuntimeException ex) {
             System.out.println("Fields should not be empty");
        }
    }
    
    @FXML 
    private void onView(ActionEvent event) throws IOException {
        String nS = "";
        
        shoeNew = readAddress();
        
        for(Shoe s : shoeNew) {
            nS += s.toString();
            System.out.println(nS);
        }
        lblResult.setText(nS);
    }
        
    @FXML 
    private void onDelete(ActionEvent event) {
        System.out.println("arrSize: " + shoeNew.size());
        
        if(!shoeNew.isEmpty()) {
            shoeNew.remove(shoeNew.size() - 1 );
        } else {
            System.out.println("File is Empty");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(RECORD_SIZE);
        
        try {
            raf = new RandomAccessFile("inventory.dat", "rw");
        } catch (IOException ex) {
            System.out.print("Error: " + ex);
            System.exit(1);
        }
        
        shoeNew = new ArrayList();
//        try {
//            shoeNew = readAddress();
//        } catch (IOException ex) {
//            System.out.println("no address to read from");
//        }
    }
}
