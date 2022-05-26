import Products.*;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Start {
    public Product[][][] shelf = new Product[3][4][2];
    public Product selectedProduct;
    public Order[] orders = new Order [3];
    public Order selectedOrder;
    public String[][] orderArray = new String[47][5];
    public int OrderIndex= 0;
    public int earnings = 0;

    public boolean selectedSecondLayer;

        public static void main(String[] args)throws FileNotFoundException{
            Start start = new Start();
            start.initializeOrderArray();
            JFrame gameFrame = new JFrame("Fach Lagerrist");
            gameFrame.setSize(1010,1010);
            gameFrame.setResizable(false);
            gameFrame.setContentPane(new FachLagerist(start).FormPanel);
            gameFrame.setVisible(true);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void initializeOrderArray()  throws FileNotFoundException{
            File getCSVFiles = new File("Leistungsnachweis.csv");
            Scanner sc = new Scanner(getCSVFiles);
            sc.useDelimiter(";|\n");

            for(int i =0; i< 6;i++ ){
                sc.next();
            }
            for(int i=0; i<47;i++){
                sc.next();
                for(int e=0;e<5;e++){
                    orderArray[i][e] = sc.next();
                }
            }

            sc.close();
        }

        public void ExportProduct(int X, int Y){
                DisposeProduct(X,Y);
                earnings += 300;
                earnings += selectedOrder.reward;
        }

    public boolean InsertProduct(int selectedFieldY, int selectedFieldX){
            int selectedFieldZ;
            if(selectedSecondLayer) selectedFieldZ = 1;
            else selectedFieldZ = 0;
        switch(selectedOrder.product.secondAttribute){
            case Beams:
                if( IsRoomForWood(selectedFieldY, selectedFieldX)){
                    for(int i = 0; i <2; i++){
                        shelf[selectedFieldY][selectedFieldX][i] = selectedOrder.product;
                        }
                    earnings += selectedOrder.reward;
                    return true;
                    }
                return false;
            case Medium:
            case Heavy:
                if(selectedFieldZ==0 && shelf[selectedFieldY][selectedFieldX][selectedFieldZ] == null && !IsStoneTooHeavy(selectedFieldY,selectedOrder.product.secondAttribute )||shelf[selectedFieldY][selectedFieldX][0] == null && shelf[selectedFieldY][selectedFieldX][selectedFieldZ] == null && !IsStoneTooHeavy(selectedFieldY,selectedOrder.product.secondAttribute )){
                    shelf[selectedFieldY][selectedFieldX][selectedFieldZ] = selectedOrder.product;
                    earnings += selectedOrder.reward;
                    return true;
                }
                return false;
            default:
                if(selectedFieldZ==0 && shelf[selectedFieldY][selectedFieldX][selectedFieldZ] == null ||shelf[selectedFieldY][selectedFieldX][selectedFieldZ] == null && shelf[selectedFieldY][selectedFieldX][0] == null ){
                    shelf[selectedFieldY][selectedFieldX][selectedFieldZ] = selectedOrder.product;
                    earnings += selectedOrder.reward;
                    return true;
                }
                return false;
        }
    }

    public void DisposeProduct(int X,int Y){
            int Z;
            if(selectedSecondLayer) Z = 1 ;
            else Z= 0;
            if(shelf[Y][X][Z].secondAttribute != SecondAttribute.Beams) {
                shelf[Y][X][Z] = null;
            }
            else{
                shelf[Y][X][0] = null;
                shelf[Y][X][1] = null;
            }
            earnings -=300;
    }

    public void switchProductLocation(int extractFieldY, int extractFieldX, int extractFieldZ ,int destinationFieldY, int destinationFieldX, int destinationFieldZ ){
    shelf [destinationFieldY][destinationFieldX][destinationFieldZ] =shelf[extractFieldY][extractFieldX][extractFieldZ] ;
    shelf[extractFieldY][extractFieldX][extractFieldZ] = null;
    earnings -= 100;
}

public boolean IsRoomForWood(int y, int x){
    for(int i = 0; i <2; i++){
        if(shelf[y][x][i] != null){
            return false;
        }
    }
    return true;
}

public boolean IsStoneTooHeavy(int y,SecondAttribute stoneWeight){
        switch(stoneWeight){
            case Leight:
                return false;
            case Medium:
                if(y < 1) return true;
                return false;
            case Heavy:
                if(y<0)return true;
                return false;
            default:
                return true;
        }
    }

public Order GetNewOrder(){
    Order newOrder = new Order();
    if(orderArray[OrderIndex % 47][0].equals(new String("Einlagerung"))) newOrder.insertProduct = true;
    switch(orderArray[OrderIndex % 47][1]){
        case "Papier":
            newOrder.product.productType = ProductType.Paper;
            break;
        case "Holz":
            newOrder.product.productType = ProductType.Wood;
            break;
        case "Stein":
            newOrder.product.productType = ProductType.Stone;
            break;
        default:
            return null;
    }
    switch(orderArray[OrderIndex % 47][2]){
        case "Weiss":
            newOrder.product.firstAttribute = FirstAttribute.White;
            break;
        case "Gruen":
            newOrder.product.firstAttribute = FirstAttribute.Green;
            break;
        case "Blau":
            newOrder.product.firstAttribute = FirstAttribute.Blue;
            break;
        case "Marmor":
            newOrder.product.firstAttribute = FirstAttribute.Marble;
            break;
        case "Granit":
            newOrder.product.firstAttribute = FirstAttribute.Granite;
            break;
        case "Sandstein":
            newOrder.product.firstAttribute = FirstAttribute.Sandstone;
            break;
        case "Buche":
            newOrder.product.firstAttribute = FirstAttribute.Beech;
            break;
        case "Eiche":
            newOrder.product.firstAttribute = FirstAttribute.Oak;
            break;
        case "Kiefer":
            newOrder.product.firstAttribute = FirstAttribute.Pine;
            break;
        default:
            return null;
    }
    switch(orderArray[OrderIndex % 47][3]){
        case "A3":
            newOrder.product.secondAttribute = SecondAttribute.A3;
            break;
        case "A4":
            newOrder.product.secondAttribute = SecondAttribute.A4;
            break;
        case "A5":
            newOrder.product.secondAttribute = SecondAttribute.A5;
            break;
        case "Bretter":
            newOrder.product.secondAttribute = SecondAttribute.Boards;
            break;
        case "Balken":
            newOrder.product.secondAttribute = SecondAttribute.Beams;
            break;
        case "Scheit":
            newOrder.product.secondAttribute = SecondAttribute.Logs;
            break;
        case "Leicht":
            newOrder.product.secondAttribute = SecondAttribute.Leight;
            break;
        case "Mittel":
            newOrder.product.secondAttribute = SecondAttribute.Medium;
            break;
        case "Schwer":
            newOrder.product.secondAttribute = SecondAttribute.Heavy;
            break;
        default:
            return null;
    }
    newOrder.reward =  Integer.parseInt(orderArray[OrderIndex% 47][4].replace("\r", ""));


    OrderIndex ++;
    return newOrder;
}


}