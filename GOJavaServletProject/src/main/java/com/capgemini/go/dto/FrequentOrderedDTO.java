package com.capgemini.go.dto;

 

public class FrequentOrderedDTO {
    private String retailerID;
    private String productID;

 


    public FrequentOrderedDTO(String retailerID, String productID) {
        super();
        this.retailerID = retailerID;
        this.productID = productID;

 

    }

 

    public String getRetailerID() {
        return retailerID;
    }

 

    public void setRetailerID(String retailerID) {
        this.retailerID = retailerID;
    }

 

    public String getProductID() {
        return productID;
    }

 

    public void setProductID(String productID) {
        this.productID = productID;
    }

 

}