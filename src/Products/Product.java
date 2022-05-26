package Products;

public class Product {
    public ProductType productType;
    public FirstAttribute firstAttribute;
    public SecondAttribute secondAttribute;

    public boolean SameProduct(Product compareProduct){
        if(productType == compareProduct.productType && firstAttribute == compareProduct.firstAttribute && secondAttribute == compareProduct.secondAttribute) return true;
        return false;
    }
}


