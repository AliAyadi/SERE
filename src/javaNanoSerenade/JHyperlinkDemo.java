/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaNanoSerenade;




import java.awt.FlowLayout;
import java.awt.HeadlessException;
 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
/**
 * This Java Swing program demonstrates how to use JHyperlink custom component.
 *  
 * @author www.codejava.net
 * 
 */
public class JHyperlinkDemo extends JFrame {
    private JHyperlink linkWebsite = new JHyperlink("Visit our website");
    private JHyperlink linkEmail = new JHyperlink("Email Us");
     
    public JHyperlinkDemo() throws HeadlessException {
        setTitle("Swing Hyperlink Demo");
         
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
         
        linkWebsite.setURL("http://www.codejava.net");
        linkWebsite.setToolTipText("Visit http://www.codejava.net");
         
        linkEmail.setURL("mailto:info@codejava.net");
        linkEmail.setToolTipText("Send an email to info@codejava.net");
         
        getContentPane().add(linkWebsite);
        getContentPane().add(linkEmail);
         
        setSize(400, 200);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
             
            @Override
            public void run() {
                new JHyperlinkDemo().setVisible(true);
            }
        });
    }
}