package ProgrammierProjektSemester2;

public enum ProductType {
    Paper,
    Wood,
    Stone
}

public enum FirstAttribute{
    White,
    Green,
    Blue,
    Pine,
    Beech,
    Oak,
    Marble,
    Granite,
    Sandstone
}

public enum SecondAttribute{
    A3,
    A4,
    A5,
    Boards,
    Beams,
    Logs,
    Leight,
    Medium,
    Heavy
}

public class Product {
    public ProductType productType;
    public FirstAttribute firstAttribute;
    public SecondAttribute secondAttribute;
}


public class Order{
    public Product product;
    int reward;
    boolean insertProduct;
}