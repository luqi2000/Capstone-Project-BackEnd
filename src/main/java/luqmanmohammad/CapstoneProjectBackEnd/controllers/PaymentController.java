package luqmanmohammad.CapstoneProjectBackEnd.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import com.stripe.param.checkout.SessionCreateParams.PaymentMethodType;

@RestController
public class PaymentController {

  @Value("${STRIPE_SECRET_KEY}")
  private String stripeSecretKey;

  @PostMapping("/api/pagamento")
  public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request) {
    BigDecimal total = new BigDecimal(request.getTotal());

    // Configura la chiave segreta di Stripe
    Stripe.apiKey = stripeSecretKey;

    try {
      // Creazione della sessione di pagamento
      SessionCreateParams.Builder sessionParamsBuilder = new SessionCreateParams.Builder()
          .addPaymentMethodType(PaymentMethodType.CARD)
          .addLineItem(
            LineItem.builder()
              .setPriceData(
                LineItem.PriceData.builder()
                  .setCurrency("usd")
                  .setUnitAmount(total.multiply(BigDecimal.valueOf(100)).longValueExact())
                  .setProductData(
                    LineItem.PriceData.ProductData.builder()
                      .setName("Product")
                      .build()
                  )
                  .build()
              )
              .setQuantity(1L)
              .build()
          )
          .setMode(SessionCreateParams.Mode.PAYMENT)
          .setSuccessUrl("http://localhost:3000/payment-success")
          .setCancelUrl("http://localhost:3000/payment-cancelled");

      SessionCreateParams sessionParams = sessionParamsBuilder.build();

      // Creazione effettiva della sessione di pagamento
      Session session = Session.create(sessionParams);

      // Restituisci l'ID della sessione di pagamento al frontend
      return ResponseEntity.ok(new PaymentResponse(session.getId()));
    } catch (StripeException e) {
      // Gestisci l'errore durante la creazione della sessione di pagamento
      return ResponseEntity.badRequest().body("Errore durante la creazione della sessione di pagamento");
    }
  }

  public static class PaymentRequest {
    private String total;

    // Getter e setter per total
    public String getTotal() {
      return total;
    }

    // Costruttore vuoto per deserializzazione JSON
    public PaymentRequest() {}

    // Costruttore per inizializzare total
    public PaymentRequest(String total) {
      this.total = total;
    }
  }

  public static class PaymentResponse {
    private String sessionId;

    // Getter e setter per sessionId
    public String getSessionId() {
      return sessionId;
    }

    // Costruttore per inizializzare sessionId
    public PaymentResponse(String sessionId) {
      this.sessionId = sessionId;
    }
  }
}