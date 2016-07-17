package br.com.springmvc.timetrialfactory.apis.paypal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import br.com.springmvc.timetrialfactory.models.ShoppingCart;

@Controller
public class PayPalCall {

	public Payment apiRequest(String currency, ShoppingCart cart) throws PayPalRESTException {
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(this.listTransactions(currency, cart));
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("https://devtools-paypal.com/guide/pay_paypal?cancel=true");
		redirectUrls.setReturnUrl("http://timetrialfac.wix.com/ttfac#!paypal/wviae");
		payment.setRedirectUrls(redirectUrls);

		Payment createdPayment = payment.create(setAPIContext());
		System.out.println(createdPayment);

		return createdPayment;
	}

	public void apiReturn(String paymentId) throws PayPalRESTException {
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		Payment payment = new Payment(paymentId, payer);
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId("743WADY6RL6BS");
		payment.execute(setAPIContext(), paymentExecute);

		System.out.println(paymentExecute.getPayerId());
	}

	private static APIContext setAPIContext() {
		APIContext apiContext = new APIContext(PayPalToken.getClientId(), PayPalToken.getClientSecret(), "sandbox");
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		apiContext.setConfigurationMap(sdkConfig);
		return apiContext;
	}

	private Amount setAmount(String currency, ShoppingCart cart) {
		Amount amount = new Amount();
		amount.setCurrency(currency);
		amount.setTotal(cart.getTotal().toString());

		return amount;
	}

	private ItemList setItemsInListPayPal(String currency, ShoppingCart cart) {
		Item itemPayPal = new Item();
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();

		for (int i = 0; i < cart.getItems().size(); i++) {
			itemPayPal.setName(cart.getItems().get(i).getGame().getTitle());
			itemPayPal.setPrice(cart.getItems().get(i).getGame().getPrice().toString());
			itemPayPal.setCurrency(currency);
			itemPayPal.setQuantity("1");
			items.add(itemPayPal);
		}

		itemList.setItems(items);

		return itemList;
	}

	private List<Transaction> listTransactions(String currency, ShoppingCart cart) {
		Transaction transaction = new Transaction();
		transaction.setDescription("creating a payment");
		transaction.setAmount(this.setAmount(currency, cart));
		transaction.setItemList(setItemsInListPayPal(currency, cart));

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		return transactions;
	}

}
