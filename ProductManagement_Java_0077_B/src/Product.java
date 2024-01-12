public class Product{
	private String productId;
	private String productCode;
	private String productName;
	private String productCategory;
	private String unitSalesPrice;
	private String purchaseUnitPrice;
	private String registrationDate;

	public Product(){
	this.productId=null;
	this.productCode=null;
	this.productName="";
	this.unitSalesPrice="";
	this.purchaseUnitPrice="";
	this.registrationDate="";
	}
	
	// 要素ごとの値を渡す
	public String getProductId() {
		return this.productId;
	}
	
	public String getProductCode() {
		return this.productCode;
	}
	
	public String getProductName() {
		return this.productName;
	}
	
	public String getProductCategory() {
		return this.productCategory;
	}
	
	public String getUnitSalesPrice() {
		return this.unitSalesPrice;
	}
	
	public String getPurchaseUnitPrice() {
		return this.purchaseUnitPrice;
	}
	
	public String getRegistrationDate() {
		return this.registrationDate;
	}
	
	// 要素ごとの値を設定する
	public void setProductId(String id) {
		this.productId = id;
	}
	
	public void setProductCode(String code) {
		this.productCode = code;
	}
	
	public void setProductName(String name) {
		this.productName = name;
	}
	
	public void setProductCategory(String category) {
		this.productCategory = category;
	}
	
	public void setUnitSalesPrice(String sPrice) {
		this.unitSalesPrice = sPrice;
	}
	
	public void setPurchaseUnitPrice(String pPrice) {
		this.purchaseUnitPrice = pPrice;
	}
	
	public void setRegistrationDate(String date) {
		this.registrationDate = date;
	}	
	
	
}
	