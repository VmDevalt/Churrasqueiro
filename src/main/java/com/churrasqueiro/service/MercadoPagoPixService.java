package com.churrasqueiro.service;

import com.churrasqueiro.entities.PedidoEmMontagem;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import com.churrasqueiro.exceptions.PagamentoException;

public class MercadoPagoPixService {

    public PixPaymentResponse criarPagamentoPix(PedidoEmMontagem pedido) throws Exception {
    	try {

        URL url = new URL(MercadoPagoConfig.PAGAMENTOS_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Authorization", "Bearer " + MercadoPagoConfig.ACCESS_TOKEN);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        
        String idemKey = "pix-" + pedido.getNumeroMesa() + "-" + System.currentTimeMillis();
        con.setRequestProperty("X-Idempotency-Key", idemKey);

        
        double valor = pedido.getTotalFinal();
        String descricao = "Pedido mesa " + pedido.getNumeroMesa();
        String externalReference = "MESA-" + pedido.getNumeroMesa() + "-TS-" + System.currentTimeMillis();
        String emailCliente = "cliente@exemplo.com"; 

        String valorFormatado = String.format(Locale.US, "%.2f", valor);

        
        String jsonBody =
                "{"
                        + "\"transaction_amount\": " + valorFormatado + ","
                        + "\"payment_method_id\": \"pix\","
                        + "\"description\": \"" + escapeJson(descricao) + "\","
                        + "\"external_reference\": \"" + escapeJson(externalReference) + "\","
                        + "\"payer\": {"
                        +     "\"email\": \"" + escapeJson(emailCliente) + "\""
                        + "}"
                        + "}";

        
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            wr.write(input);
            wr.flush();
        }

        
        int status = con.getResponseCode();

        StringBuilder response = new StringBuilder();
        BufferedReader br;
        if (status >= 200 && status < 300) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8));
        }

        String respLine;
        while ((respLine = br.readLine()) != null) {
            response.append(respLine.trim());
        }
        br.close();

        String responseBody = response.toString();

        if (status != HttpURLConnection.HTTP_CREATED && status != HttpURLConnection.HTTP_OK) {
            
            throw new RuntimeException("Erro ao criar pagamento PIX. HTTP " + status + ": " + responseBody);
        }

        
        JsonObject root = JsonParser.parseString(responseBody).getAsJsonObject();

        Long paymentId = root.get("id").getAsLong();

        JsonObject poi = root.getAsJsonObject("point_of_interaction");
        JsonObject txData = poi.getAsJsonObject("transaction_data");

        String qrCode = txData.get("qr_code").getAsString();
        String qrCodeBase64 = txData.get("qr_code_base64").getAsString();
        String ticketUrl = txData.get("ticket_url").getAsString();

        return new PixPaymentResponse(paymentId, qrCode, qrCodeBase64, ticketUrl);
    	} catch (UnknownHostException | ConnectException | SocketTimeoutException e) {

            throw new PagamentoException(
                "Sem conexão com a internet, Verifique o Wi-Fi e tente novamente."
            );

        } catch (Exception e) {

            throw new PagamentoException(
                "Não foi possível gerar o pagamento PIX. Tente novamente."
            );
        }
	}

    private String escapeJson(String value) {
        if (value == null) return "";
        return value.replace("\"", "\\\"");
    }
}
