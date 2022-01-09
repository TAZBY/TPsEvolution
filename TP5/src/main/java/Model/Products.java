package Model;

import java.util.ArrayList;
import java.util.List;

public class Products {
	private int id;
	private String name;
	private float price;
	private int quantity;
	
	
	public Products(int id,String name, float price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Products() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	public Products add(int id, String name, float price, int quantity) {
		return new Products(id,name,price,quantity);
	}
	
	
	public List<Products> initProducts(){
		List<Products> Products = new ArrayList<Products>();
		Products.add(add(1,"Hp",780,5));
		Products.add(add(2,"Ecran",180,15));
		Products.add(add(3,"Clavier",50,50));
		Products.add(add(4,"Asus",1000,15));
		Products.add(add(5,"Ordinateur",780,5));
		return Products;
	}
	
	public List<Products> getProducts(){
		return initProducts();
	}
	public Products getProductByID(int id) {
		Products Product = new Products();
		for(Products p : getProducts()) {
			if(p.getId()==id) {
				Product = p;
			}else {
				new Exception();
				
			}
		}
		return Product;
	}
	
	public void deleteProduct(int id) {
		for(Products p : getProducts()) {
			if(p.getId()==id) {
				getProducts().remove(p);
			}else {
				new Exception();
			}
		}
	}
	
	public Products updateProduct(int id, Products prod) {
		Products Product = new Products();
		for(Products p : getProducts()) {
			if(p.getId()==id) {
				p = prod;
				Product = p;
				
			}else {
				new Exception();
				
			}
		}
		return Product;
	}
}
