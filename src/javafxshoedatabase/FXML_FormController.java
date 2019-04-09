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
    private int count = 0;
    private ArrayList<Shoe> shoeNew;
    
    public void writeAddress(ArrayList<Shoe> arr) throws IOException {
        
        raf.setLength(0);
        
        for(Shoe s : arr) {
            try {
                
                raf.seek(raf.length());
                try {
                    FixedLengthStringIO.writeFixedLengthString(s.getID(), Shoe.ID_SIZE, raf);
                    System.out.println(s.getID());
                    
                    FixedLengthStringIO.writeFixedLengthString(s.getName(), Shoe.NAME_SIZE, raf);
                    System.out.println(s.getName());
                    
                    FixedLengthStringIO.writeFixedLengthString(s.getBrand(), Shoe.BRAND_SIZE, raf);
                    System.out.println(s.getBrand());
                    
                    FixedLengthStringIO.writeFixedLengthString(s.getStyle(), Shoe.STYLE_SIZE, raf);
                    System.out.println(s.getStyle());
                    
                    raf.writeInt(s.getStock());
                    System.out.println(s.getStock());
                    
                    raf.writeDouble(s.getSize());
                    System.out.println(s.getSize());
                    
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

                String ID = FixedLengthStringIO.readFixedLengthString(Shoe.ID_SIZE, raf);
                System.out.println(raf.getFilePointer());
                
                String name = FixedLengthStringIO.readFixedLengthString(Shoe.NAME_SIZE, raf);
                System.out.println(raf.getFilePointer());
                
                String brand = FixedLengthStringIO.readFixedLengthString(Shoe.BRAND_SIZE, raf);
                System.out.println(raf.getFilePointer());
                
                String style = FixedLengthStringIO.readFixedLengthString(Shoe.STYLE_SIZE, raf);
                System.out.println(raf.getFilePointer());
                
                int stock = raf.readInt();
                System.out.println(raf.getFilePointer());
                
                double size = raf.readDouble();
                System.out.println(raf.getFilePointer());
                
                s.setID(ID);

                tempNew.add(tempNew.size(), s);
                

            } catch (EOFException e) {
                break;
            }
        }
        return tempNew;
    }
    
    @FXML private Label lblID, lblName, lblBrand, lblStyle, lblSize, lblStock;
    
    @FXML private TextField txtID, txtName, txtBrand, txtStyle, txtStock;
    
    @FXML private ComboBox cmbSize;  
    
    @FXML private Button Add, Update, Remove;
    
    @FXML 
    private void onAdd(ActionEvent event) {
        
    }
    
    @FXML 
    private void onUpdate(ActionEvent event) {
        
    }
        
    @FXML 
    private void onDelete(ActionEvent event) {
        
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
    }
}
