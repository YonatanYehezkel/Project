package Model;

public class Product {

	public int id;
	public String title;
	public float price;
	public float quantity;
	public float salesQuantity;
	public String unit;
	
	/*
	 * full Product constructor
	 */
	public Product(int id, String title, float price, float quantity, String unit) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
	}

	public Product(int id, String title, float price, String unit) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.unit = unit;
	}
	
	public Product (int id, String title,float salesQuantity) {
		super();
		this.id = id;
		this.title = title;
		this.salesQuantity = salesQuantity;
	}
	
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(float salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", salesQuantity=" + salesQuantity + "]";
	}
	
}
