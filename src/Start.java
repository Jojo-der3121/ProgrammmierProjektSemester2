import Products.*;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Start {
    public Product[][][] shelf = new Product[3][4][2];
    public Order[] orders = new Order [3];
    public Order selectedOrder;
    public String[][] orderArray = new String[47][5];
    public int OrderIndex;
    public int earnings;

    public boolean selectedSecondLayer;

        public static void main(String[] args)throws FileNotFoundException{
            Start start = new Start();
            start.initializeOrderArray();
            JFrame tach3 = new JFrame("Fach Lagerrist");
            tach3.setSize(650,450);
            tach3.setContentPane(new FachLagerist(start).tach4);
            tach3.setVisible(true);
            tach3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void initializeOrderArray()  throws FileNotFoundException{
            File getCSVFiles = new File("Leistungsnachweis.csv");
            Scanner sc = new Scanner(getCSVFiles);
            sc.useDelimiter(";|\n");

            for(int i =0; i< 6;i++ ){
                sc.next();
            }
            for(int i=0; i<5;i++){
                sc.next();
                for(int e=0;e<5;e++){
                    orderArray[i][e] = sc.next();
                }
            }
            sc.close();
        }

    public boolean InsertProduct(int selectedFieldY, int selectedFieldX, int selectedFieldZ ,Product product){
        switch(product.secondAttribute){
            case Beams:
                if( IsRoomForWood(selectedFieldY, selectedFieldX)){
                    for(int i = 0; i <2; i++){
                        shelf[selectedFieldY][selectedFieldX][i] = product;
                        }
                    return true;
                    }
                return false;
            case Medium:
            case Heavy:
                if(shelf[selectedFieldY][selectedFieldX][selectedFieldZ] != null && !IsStoneTooHeavy(selectedFieldY,product.secondAttribute)){
                    shelf[selectedFieldY][selectedFieldX][selectedFieldZ] = product;
                    return true;
                }
                return false;
            default:
                if(shelf[selectedFieldY][selectedFieldX][selectedFieldZ] != null){
                    shelf[selectedFieldY][selectedFieldX][selectedFieldZ] = product;
                    return true;
                }
                return false;
        }
    }

    public void switchProductLocation(int extractFieldY, int extractFieldX, int extractFieldZ ,int destinationFieldY, int destinationFieldX, int destinationFieldZ ){
    Product productCache = shelf[extractFieldY][extractFieldX][extractFieldZ];
    shelf[extractFieldY][extractFieldX][extractFieldZ] = null;
    shelf [destinationFieldY][destinationFieldX][destinationFieldZ] = productCache;
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
    if(orderArray[OrderIndex][0] == "Einlagerung") newOrder.insertProduct = true;
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
    newOrder.reward = Integer.parseInt(orderArray[OrderIndex% 47][4]);


    OrderIndex ++;
    return newOrder;
}
}