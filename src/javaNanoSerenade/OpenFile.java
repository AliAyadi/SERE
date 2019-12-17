/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaNanoSerenade;

import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author aliay
 */
public class OpenFile {
    
    
    //declare variable
    JFileChooser filechooser = new JFileChooser();
    StringBuilder sb = new StringBuilder();
    
    public void PickMe() throws Exception{
        if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            
            //get the file
            java.io.File file = filechooser.getSelectedFile();
                    
            //create a scanner for the file
            Scanner input = new Scanner(file);
            
            //read text from file
            while(input.hasNext()){
                sb.append(input.nextLine());
                sb.append("\n");
            }
            
            input.close();
            
        }
        else{
            sb.append("No file was selected");
        }
        
    }
    
}
