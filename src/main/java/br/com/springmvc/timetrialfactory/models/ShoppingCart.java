package br.com.springmvc.timetrialfactory.models;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
public class ShoppingCart {

	private List<ShoppingItem> items = new ArrayList<ShoppingItem>();
	private BigDecimal total = new BigDecimal("0.0");

	public List<ShoppingItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingItem> items) {
		this.items = items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void add(ShoppingItem item) {
		this.items.add(item);
		this.total = (item.getGame().getPrice()).add(total);
	}

	public void remove(int itemIndex) {
		ShoppingItem removed = new ShoppingItem();
		removed = this.items.remove(itemIndex);
		this.total = total.subtract(removed.getGame().getPrice());
	}

}
