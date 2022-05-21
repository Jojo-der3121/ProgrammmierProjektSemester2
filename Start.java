import ProgrammierProjektSemester2.*;
import java.io.*;
import java.util.Scanner;

public class Start {
    public Product[][][] shelf = new Product[3][4][2];
    public Order[] orders = new Order [3];
    public String[][] orderArray = new String[47][5]
    public int OrderIndex;
    public int earnings;

        public static void main(String[] args){
            File getCSVFiles = new File("/Leistungsnachweis.csv");
            Scanner sc = new Scanner(getCSVFiles);
            sc.useDelimiter(",");
            for(i =0; i< (OrderIndex)*6;i++ ){
                sc.next();
            }
            for(i=0; i<47;i++){
                sc.next();
                for(e=0;e<5;e++){
                    orderArray[i][e] = sc.next();
                }
            }
        }

    public boolean InsertProduct(int selectedFieldY, int selectedFieldX, int selectedFieldZ ,Product product){
        switch(product.productType){
            case ProducType.Paper:
                if(shelf[selectedFieldY][selectedFieldX][selectedFieldZ] != null){
                    shelf[selectedFieldY][selectedFieldX][selectedFieldZ] = product;
                    return true;
                }
                return false;
            case ProducType.Wood:
                if( IsRoomForWood(selectedFieldY, selectedFieldX)){
                    for(int i = 0; i <2; i++){
                        shelf[y][x][i] = product;
                        }
                    return true;
                    }
                return false;
            case ProducType.Stone:
                if(shelf[selectedFieldY][selectedFieldX][selectedFieldZ] != null && !IsStoneTooHeavy(selectedFieldY,product.weightOfStone)){
                    shelf[selectedFieldY][selectedFieldX][selectedFieldZ] = product;
                    return true;
                }
                return false;
            default:
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

public boolean IsStoneTooHeavy(int y,WeightOfStone stoneWeight){
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
            newOrder.Product.productType = ProductType.Paper;
            break;
        case "Holz":
            newOrder.Product.productType = ProductType.Wood;
            break;
        case "Stein":
            newOrder.Product.productType = ProductType.Stone;
            break;
        default:
            return null;
    }
    switch(orderArray[OrderIndex % 47][2]){
        case "Weiss":
            newOrder.Product.firstAttribute = FirstAttribute.White;
            break;
        case "Gruen":
            newOrder.Product.firstAttribute = FirstAttribute.Green;
            break;
        case "Blau":
            newOrder.Product.firstAttribute = FirstAttribute.Blue;
            break;
        case "Marmor":
            newOrder.Product.firstAttribute = FirstAttribute.Marble;
            break;
        case "Granit":
            newOrder.Product.firstAttribute = FirstAttribute.Granite;
            break;
        case "Sandstein":
            newOrder.Product.firstAttribute = FirstAttribute.Sandstone;
            break;
        case "Buche":
            newOrder.Product.firstAttribute = FirstAttribute.Beech;
            break;
        case "Eiche":
            newOrder.Product.firstAttribute = FirstAttribute.Oak;
            break;
        case "Kiefer":
            newOrder.Product.firstAttribute = FirstAttribute.Pine;
            break;
        default:
            return null;
    }
    switch(orderArray[OrderIndex % 47][3]){
        case "A3":
            newOrder.Product.secondAttribute = SecondAttribute.A3;
            break;
        case "A4":
            newOrder.Product.secondAttribute = SecondAttribute.A4;
            break;
        case "A5":
            newOrder.Product.secondAttribute = SecondAttribute.A5;
            break;
        case "Bretter":
            newOrder.Product.secondAttribute = SecondAttribute.Boards;
            break;
        case "Balken":
            newOrder.Product.secondAttribute = SecondAttribute.Beams;
            break;
        case "Scheit":
            newOrder.Product.secondAttribute = SecondAttribute.Logs;
            break;
        case "Leicht":
            newOrder.Product.secondAttribute = SecondAttribute.Leight;
            break;
        case "Mittel":
            newOrder.Product.secondAttribute = SecondAttribute.Medium;
            break;
        case "Schwer":
            newOrder.Product.secondAttribute = SecondAttribute.Heavy;
            break;
        default:
            return null;
    }
    newOrder.reward = orderArray[OrderIndex% 47][4];

    sc.close();
    OrderIndex ++;
    return newOrder;
}
}