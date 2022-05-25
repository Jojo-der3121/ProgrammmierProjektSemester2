import javax.swing.*;
import java.awt.event.*;

public class FachLagerist {

    ImageIcon imagePaper = new ImageIcon("Paper.png");

    private Start start;
    private JButton einlagernButton;
    private JButton ausliefernButton;
    private JButton umlagernButton;
    private JButton neuerAuftragButton;
    private JButton billanzButton;
    public JPanel tach4;
    private JLabel Guthaben;
    private JButton ebeneWechselnButton;
    private JButton zerlegenButton;

    public FachLagerist(Start str){
        start = str;
       einlagernButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                onInsert();
           }
       });
       ausliefernButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               onExport();
           }
       });
       umlagernButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               onRelocate();
           }
       });
       zerlegenButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               onDispose();
           }
       });
       neuerAuftragButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               onNewOrder();
           }
       });
       billanzButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               onBalanceSheet();
           }
       });

       ebeneWechselnButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               onSwitchLayer();
           }
       });

   }

    private void onSwitchLayer() {
        if(start.selectedSecondLayer){
            start.selectedSecondLayer = false;
        }
        else{
            start.selectedSecondLayer= true;
        }
    }

    private void onBalanceSheet() {
    }

    private void onDispose() {
    }

    private void onRelocate() {
    }

    private void onExport() {
    }

    private void onInsert(){
    Guthaben.setIcon(imagePaper);
   }
   private void onNewOrder(){
        if(start.orders[0]== null) start.orders[0]= start.GetNewOrder();
        else if (start.orders[1]== null) start.orders[1]= start.GetNewOrder();
        else if (start.orders[2]== null) start.orders[2]= start.GetNewOrder();
        //else : add error handling
    }
}
