package com.churrasqueiro.service;

public class PixPaymentResponse {

    private Long paymentId;
    private String qrCode;
    private String qrCodeBase64;
    private String ticketUrl;

    public PixPaymentResponse(Long paymentId, String qrCode, String qrCodeBase64, String ticketUrl) {
        this.paymentId = paymentId;
        this.qrCode = qrCode;
        this.qrCodeBase64 = qrCodeBase64;
        this.ticketUrl = ticketUrl;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getQrCodeBase64() {
        return qrCodeBase64;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }
}
