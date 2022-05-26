import Products.Product;

import javax.swing.*;
import java.awt.event.*;

public class FachLagerist implements MouseListener{

    ImageIcon Empty = new ImageIcon(("emptyshelf.png"));
    ImageIcon GreenA3 = new ImageIcon("ShelvedGreenA3Paper.png");
    ImageIcon GreenA4 = new ImageIcon("ShelvedGreenA4Paper.png");
    ImageIcon GreenA5 = new ImageIcon("ShelvedGreenA5Paper.png");
    ImageIcon BlueA3 = new ImageIcon("ShelvedBlueA3Paper.png");
    ImageIcon BlueA4 = new ImageIcon("ShelvedBlueA4Paper.png");
    ImageIcon BlueA5 = new ImageIcon("ShelvedBlueA5Paper.png");
    ImageIcon WhiteA3 = new ImageIcon("ShelvedWhiteA3Paper.png");
    ImageIcon WhiteA4 = new ImageIcon("ShelvedWhiteA4Paper.png");
    ImageIcon WhiteA5 = new ImageIcon("ShelvedWhiteA5Paper.png");
    ImageIcon LightSandstone = new ImageIcon("ShelvedLightSandstone.png");
    ImageIcon MediumSandstone = new ImageIcon("ShelvedMediumSandstone.png");
    ImageIcon HeavySandstone = new ImageIcon("ShelvedHeavySandstone.png");
    ImageIcon LightGranit = new ImageIcon("ShelvedLightGranit.png");
    ImageIcon MediumGranit = new ImageIcon("ShelvedMediumGranit.png");
    ImageIcon HeavyGranit = new ImageIcon("ShelvedHeavyGranit.png");
     ImageIcon LightMarble = new ImageIcon("ShelvedLightMarble.png");
    ImageIcon MediumMarble = new ImageIcon("ShelvedMediumMarble.png");
    ImageIcon HeavyMarble = new ImageIcon("ShelvedHeavyMarble.png");
    ImageIcon OakBeam = new ImageIcon("ShelvedOakBeam.png");
    ImageIcon OakLog = new ImageIcon("ShelvedOakLog.png");
    ImageIcon OakBoard = new ImageIcon("ShelvedOakBoard.png");
    ImageIcon BeechBeam = new ImageIcon("ShelvedBeechBeam.png");
    ImageIcon BeechLog = new ImageIcon("ShelvedBeechLog.png");
    ImageIcon BeechBoard = new ImageIcon("ShelvedBeechBoard.png");
    ImageIcon PineBeam = new ImageIcon("ShelvedPineBeam.png");
    ImageIcon PineLog = new ImageIcon("ShelvedPineLog.png");
    ImageIcon PineBoard = new ImageIcon("ShelvedPineBoard.png");

    JLabel[][] LabelShelf = new JLabel[3][4];
    private int selectedX;
    private int selectedY;

    private boolean relocateProduct;
    private int relocateX;
    private int relocateY;
    private int relocateZ = 0;

    private int selectedOrderInt;

    private Start start;
    private JButton einlagernButton;
    private JButton ausliefernButton;
    private JButton umlagernButton;
    private JButton neuerAuftragButton;
    private JButton billanzButton;
    public JPanel FormPanel;
    private JLabel Guthaben;
    private JButton ebeneWechselnButton;
    private JButton zerlegenButton;
    private JLabel picOne;
    private JLabel picTwo;
    private JLabel picThree;
    private JLabel picFour;
    private JLabel picFive;
    private JLabel picSix;
    private JLabel picSeven;
    private JLabel picEight;
    private JLabel picNine;
    private JLabel picTen;
    private JLabel picElevan;
    private JLabel picTwealth;
    private JButton ablehnenButton;
    private JLabel OrderOne;
    private JLabel OrderTwo;
    private JLabel OrderThree;
    private JLabel selectedOrder;
    private JLabel selectedLayer;
    private JLabel OrderOneProductType;
    private JLabel OrderTwoProductType;
    private JLabel OrderThreeProductType;
    private JLabel selectedOrderProductType;
    private JLabel OrderOneProductAttributes;
    private JLabel OrderTwoProductAttributes;
    private JLabel OrderThreeProductAttributes;
    private JLabel selectedOrderProductAttributes;
    private JLabel selectedOrderRewards;
    private JLabel OrderOneRewards;
    private JLabel OrderTwoRewards;
    private JLabel OrderThreeRewards;
    private JButton cancelTransfer;
    private JLabel selectionDisplay;

    public FachLagerist(Start str){
        start = str;

        ablehnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onReject();
            }
        });
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
       cancelTransfer.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               onCancelTransfer();
           }
       });
       cancelTransfer.setEnabled(false);

       selectedLayer.setText("1");

       LabelShelf[0][0] = picOne;
       LabelShelf[0][1] = picTwo;
       LabelShelf[0][2] = picThree;
       LabelShelf[0][3] = picFour;
       LabelShelf[1][0] = picFive;
       LabelShelf[1][1] = picSix;
       LabelShelf[1][2] = picSeven;
       LabelShelf[1][3] = picEight;
       LabelShelf[2][0] = picNine;
       LabelShelf[2][1] = picTen;
       LabelShelf[2][2] = picElevan;
       LabelShelf[2][3] = picTwealth;

       for(JLabel[] labels : LabelShelf){
           for(JLabel label : labels){
               label.setText("");
               label.setIcon(Empty);
           }
       }
       FormPanel.addMouseListener(this);

   }

    private void onCancelTransfer() {
        relocateProduct = false;
        cancelTransfer.setEnabled(false);
    }

    private void onReject() {
        start.earnings -= start.selectedOrder.reward;
       clearOrder();

    }

    private void onSwitchLayer() {
        if(start.selectedSecondLayer){
            start.selectedSecondLayer = false;
            selectedLayer.setText("1");
            showOtherLayer(0);
        }
        else{
            start.selectedSecondLayer= true;
            selectedLayer.setText("2");
            showOtherLayer(1);
        }
    }
    private void showOtherLayer(int whichLayer){
        for(JLabel[] labels : LabelShelf){
            for(JLabel label : labels){
                label.setText("");
                label.setIcon(Empty);
            }
        }
        for( int e= 0; e<3; e++){
            for(int i = 0; i<4; i++){
                if(start.shelf[e][i][whichLayer] != null){
                    drawLabelIcon(i,e,start.shelf[e][i][whichLayer]);
                }
            }
        }
    }

    private void onBalanceSheet() {
    }

    private void onDispose() {
        LabelShelf[selectedY][selectedX].setIcon(Empty);
        start.DisposeProduct(selectedX,selectedY);
        Guthaben.setText("Guthaben: "+ start.earnings);
    }

    private void onRelocate() {
        if(!start.selectedSecondLayer|| start.shelf[selectedY][selectedX][0]== null){
            relocateProduct = true;
            relocateX = selectedX;
            relocateY = selectedY;
            if(start.selectedSecondLayer)relocateZ=1;
            cancelTransfer.setEnabled(true);
        }
    }

    private void onExport() {
        int Z;
        if(start.selectedSecondLayer) Z = 1;
        else Z = 0;
        if((!start.selectedOrder.insertProduct && start.selectedOrder.product.SameProduct(start.shelf[selectedY][selectedX][Z])  && Z ==0 )||(!start.selectedOrder.insertProduct && start.selectedOrder.product.SameProduct(start.shelf[selectedY][selectedX][Z])  && start.shelf[selectedY][selectedX][0] == null)) {
            start.ExportProduct(selectedX,selectedY);
            LabelShelf[selectedY][selectedX].setIcon(Empty);
            clearOrder();
        }

    }

    private void onInsert(){
        if(start.selectedOrder.insertProduct) {
            if ( start.InsertProduct(selectedY, selectedX)){
                drawLabelIcon(selectedX,selectedY,start.selectedOrder.product);
                clearOrder();
            }
            //else ADD ERROR
        }
    }

    private void clearOrder(){
        start.selectedOrder = null;
        start.orders[selectedOrderInt] = null;
        switch (selectedOrderInt) {
            case 0:
                OrderOne.setText("Auftrag 1");
                OrderOneProductType.setText("");
                OrderOneProductAttributes.setText("");
                OrderOneRewards.setText("");
                break;
            case 1:
                OrderTwo.setText("Auftrag 2");
                OrderTwoProductType.setText("");
                OrderTwoProductAttributes.setText("");
                OrderTwoRewards.setText("");
                break;
            case 2:
                OrderThree.setText("Auftrag 3");
                OrderThreeProductType.setText("");
                OrderThreeProductAttributes.setText("");
                OrderThreeRewards.setText("");
                break;
        }
        selectedOrderInt = 5;
        selectedOrder.setText("Ausgewaehlter Auftrag");
        selectedOrderProductType.setText("");
        selectedOrderProductAttributes.setText("");
        selectedOrderRewards.setText("");
        Guthaben.setText("Guthaben: "+ start.earnings);
    }

    private void drawLabelIcon(int x, int y, Product productToDraw){
        switch (productToDraw.firstAttribute) {
            case White:
                switch (productToDraw.secondAttribute) {
                    case A3 -> LabelShelf[y][x].setIcon(WhiteA3);
                    case A4 -> LabelShelf[y][x].setIcon(WhiteA4);
                    case A5 -> LabelShelf[y][x].setIcon(WhiteA5);
                }
                break;
            case Green:
                switch (productToDraw.secondAttribute) {
                    case A3 -> LabelShelf[y][x].setIcon(GreenA3);
                    case A4 -> LabelShelf[y][x].setIcon(GreenA4);
                    case A5 -> LabelShelf[y][x].setIcon(GreenA5);
                }
                break;
            case Blue:
                switch (productToDraw.secondAttribute) {
                    case A3 -> LabelShelf[y][x].setIcon(BlueA3);
                    case A4 -> LabelShelf[y][x].setIcon(BlueA4);
                    case A5 -> LabelShelf[y][x].setIcon(BlueA5);
                }
                break;
            case Pine:
                switch (productToDraw.secondAttribute) {
                    case Logs -> LabelShelf[y][x].setIcon(PineLog);
                    case Beams -> LabelShelf[y][x].setIcon(PineBeam);
                    case Boards -> LabelShelf[y][x].setIcon(PineBoard);
                }
                break;
            case Beech:
                switch (productToDraw.secondAttribute) {
                    case Logs -> LabelShelf[y][x].setIcon(BeechLog);
                    case Beams -> LabelShelf[y][x].setIcon(BeechBeam);
                    case Boards -> LabelShelf[y][x].setIcon(BeechBoard);
                }
                break;
            case Oak:
                switch (productToDraw.secondAttribute) {
                    case Logs -> LabelShelf[y][x].setIcon(OakLog);
                    case Beams -> LabelShelf[y][x].setIcon(OakBeam);
                    case Boards -> LabelShelf[y][x].setIcon(OakBoard);
                }
                break;
            case Marble:
                switch (productToDraw.secondAttribute) {
                    case Leight -> LabelShelf[y][x].setIcon(LightMarble);
                    case Medium -> LabelShelf[y][x].setIcon(MediumMarble);
                    case Heavy -> LabelShelf[y][x].setIcon(HeavyMarble);
                }
                break;
            case Granite:
                switch (productToDraw.secondAttribute) {
                    case Leight -> LabelShelf[y][x].setIcon(LightGranit);
                    case Medium -> LabelShelf[y][x].setIcon(MediumGranit);
                    case Heavy -> LabelShelf[y][x].setIcon(HeavyGranit);
                }
                break;
            case Sandstone:
                switch (productToDraw.secondAttribute) {
                    case Leight -> LabelShelf[y][x].setIcon(LightSandstone);
                    case Medium -> LabelShelf[y][x].setIcon(MediumSandstone);
                    case Heavy -> LabelShelf[y][x].setIcon(HeavySandstone);
                }
                break;
        }
    }
   private void onNewOrder(){
       String[] cacheStringArray = new String[4];
       if(start.orders[0]== null){
           start.orders[0]= start.GetNewOrder();
           cacheStringArray=ParseOrderToString(0);
           OrderOne.setText(cacheStringArray[0]);
           OrderOneProductType.setText((cacheStringArray[1]));
           OrderOneProductAttributes.setText(cacheStringArray[2]);
           OrderOneRewards.setText(cacheStringArray[3]);
       }
       else if (start.orders[1]== null){
           start.orders[1]= start.GetNewOrder();
           cacheStringArray=ParseOrderToString(1);
           OrderTwo.setText(cacheStringArray[0]);
           OrderTwoProductType.setText((cacheStringArray[1]));
           OrderTwoProductAttributes.setText(cacheStringArray[2]);
           OrderTwoRewards.setText(cacheStringArray[3]);
       }
       else if (start.orders[2]== null){
           start.orders[2]= start.GetNewOrder();
           cacheStringArray=ParseOrderToString(2);
           OrderThree.setText(cacheStringArray[0]);
           OrderThreeProductType.setText((cacheStringArray[1]));
           OrderThreeProductAttributes.setText(cacheStringArray[2]);
           OrderThreeRewards.setText(cacheStringArray[3]);
       }
        //else : add error handling
    }

    private String[] ParseOrderToString(int i) {
        String KindOfOrder;
        String ProductKind= "";
        String ProductAttributeOne = "";
        String ProductAttributeTwo = "";
        if(start.orders[i].insertProduct) KindOfOrder = "Einlagerung";
        else KindOfOrder = "Auslagerung";

        switch (start.orders[i].product.firstAttribute){
            case Oak:
                ProductKind ="Holz";
                ProductAttributeOne = "Eiche";
                break;
            case Beech:
                ProductKind ="Holz";
                ProductAttributeOne = "Buche";
                break;
            case Pine:
                ProductKind = "Holz";
                ProductAttributeOne= "Kiefer";
            case Blue:
                ProductKind = "Papier";
                ProductAttributeOne="Blau";
                break;
            case Green:
                ProductKind = "Papier";
                ProductAttributeOne = "Gruen";
                break;
            case White:
                ProductKind = "Papier";
                ProductAttributeOne = "Weiss";
                break;
            case Marble:
                ProductKind = "Stein";
                ProductAttributeOne="Marmor";
                break;
            case Granite:
                ProductKind ="Stein";
            ProductAttributeOne = "Granit";
            break;
            case Sandstone:
                ProductKind = "Stein";
                ProductAttributeOne="Sandstein";
            break;
        }
        switch (start.orders[i].product.secondAttribute){
            case A3 -> ProductAttributeTwo = "A3";
            case A4 -> ProductAttributeTwo = "A4";
            case A5 -> ProductAttributeTwo = "A5";
            case Logs -> ProductAttributeTwo="Scheit";
            case Beams -> ProductAttributeTwo="Balken";
            case Boards -> ProductAttributeTwo="Bretter";
            case Heavy -> ProductAttributeTwo="Schwer";
            case Medium -> ProductAttributeTwo="Mittel";
            case Leight -> ProductAttributeTwo="Leicht";
        }
        String parsedString[] = new String[4];
        parsedString[0]=  "Auftragsart: " + KindOfOrder;
                parsedString[1]="ProduktArt: " + ProductKind;
                parsedString[2]="ProduktAttribute: " + ProductAttributeOne+ ", "+ProductAttributeTwo;
                parsedString[3]= "Lohn: " +start.orders[i].reward;
        return parsedString;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getY() <= 700 && e.getY()>=65){
            selectedX = GetselectedShelfX(e.getX());
            selectedY = GetselectedShelfY(e.getY());
            selectionDisplay.setText("Ausgewaehltes Feld: x:"+ selectedX+ ", y:"+selectedY);
        }

        if(relocateProduct && e.getY() <= 700 && e.getY()>=65 && !start.selectedSecondLayer ||relocateProduct && e.getY() <= 700 && e.getY()>=65 && start.shelf[selectedY][selectedX][0]== null){
           int Z = 0;
           if(start.selectedSecondLayer)Z=1;
           start.switchProductLocation(relocateY,relocateX,relocateZ,selectedY,selectedX,Z);
           drawLabelIcon(selectedX,selectedY,start.shelf[selectedY][selectedX][Z]);
           if(relocateZ == Z)LabelShelf[relocateY][relocateX].setIcon(Empty);
           Guthaben.setText("Guthaben: "+start.earnings);
            relocateProduct = false;
        }

            if(e.getY()>= 715 && e.getY()<=850 && e.getX()<= 730 && e.getX()>= 0 && !relocateProduct){
                int i = GetselectedShelfX(e.getX());
                start.selectedOrder = start.orders[i];
                String[] cacheStringArray = new String[4];
                cacheStringArray=  ParseOrderToString(i);
                selectedOrder.setText(cacheStringArray[0]);
                selectedOrderProductType.setText(cacheStringArray[1]);
                selectedOrderProductAttributes.setText(cacheStringArray[2]);
                selectedOrderRewards.setText(cacheStringArray[3]);
                selectedOrderInt = i;
            }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private int GetselectedShelfX(int x){
        var i = 0;
        while(x > 260 ) {
            x-=250;
            i++;
        }
        return i;
    }
    private int GetselectedShelfY(int y){
        var i = 0;
        while(y > 250 ) {
            y-=185;
            i++;
        }
        return i;
    }
}
