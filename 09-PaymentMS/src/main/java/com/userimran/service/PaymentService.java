package com.userimran.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.userimran.clients.OrderClient;
import com.userimran.constants.AppConstants;
import com.userimran.dto.PaymentRequest;
import com.userimran.dto.ProductOrderDto;
import com.userimran.props.AppProps;
import com.userimran.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final AppProps props;
    private OrderClient orderClient;

    public Order createOrder(PaymentRequest paymentRequest) throws RazorpayException {
        Map<String, String> messages = props.getMessages();
        RazorpayClient razorpayClient = new RazorpayClient(messages.get(AppConstants.RAZORPAY_API_KEY), messages.get(AppConstants.RAZORPAY_SECRET_KEY));
        JSONObject orderRequest = new JSONObject();
        orderRequest.put(AppConstants.AMOUNT, paymentRequest.getAmount() * 100);
        orderRequest.put(AppConstants.CURRENCY, paymentRequest.getCurrency());
        orderRequest.put(AppConstants.ORDER_ID, paymentRequest.getOrderId());
        return razorpayClient.orders.create(orderRequest);
    }

    public boolean verifyPaymentSignature(Map<String, Object> paymentDetails) {

        String razorpayOrderId = (String) paymentDetails.get(AppConstants.ORDER_ID);
        String razorpayPaymentId = (String) paymentDetails.get(AppConstants.PAYMENT_ID);
        String razorpaySignature = (String) paymentDetails.get(AppConstants.SIGNATURE);
        Integer orderId = (Integer) paymentDetails.get(AppConstants.RECEIPT);
        System.out.println(orderId);
        try {
            String payload = razorpayOrderId + "|" + razorpayPaymentId;
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec("cqBHGRXrRioNz3YoKg5cY5C9".getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(payload.getBytes());
            String expectedSignature = new String(Hex.encodeHex(hash));
            if (expectedSignature.equals(razorpaySignature)) {
                ProductOrderDto orderDto = new ProductOrderDto();
                orderDto.setOrderId(orderId);
                orderDto.setOrderStatus("Created");
                ResponseEntity<ApiResponse<ProductOrderDto>> updateOrder = orderClient.updateOrder(orderDto);
                return updateOrder.getStatusCode().is2xxSuccessful();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
