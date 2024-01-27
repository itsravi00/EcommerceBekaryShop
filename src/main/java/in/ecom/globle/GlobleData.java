package in.ecom.globle;

import java.util.ArrayList;
import java.util.List;

import in.ecom.model.Product;

public class GlobleData {
	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
	}

}
