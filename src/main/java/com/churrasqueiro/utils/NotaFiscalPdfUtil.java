package com.churrasqueiro.utils;

import com.churrasqueiro.entities.PedidoResumo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class NotaFiscalPdfUtil {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static File gerarNotaFiscalPedido(PedidoResumo pedido) throws IOException {

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        PDPageContentStream cs = new PDPageContentStream(doc, page);

        float margin = 40;
        float pageWidth = page.getMediaBox().getWidth();
        float y = page.getMediaBox().getHeight() - margin;

        
        
        String titulo = "CHURRASQUEIRO";
        float fontSizeTitulo = 16f;
        float tituloWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(titulo) / 1000 * fontSizeTitulo;
        float xTitulo = (pageWidth - tituloWidth) / 2;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, fontSizeTitulo);
        cs.newLineAtOffset(xTitulo, y);
        cs.showText(titulo);
        cs.endText();
        y -= 18;

        
        String endereco = "R. Ananias Lacerda de Andrade, 335, Abreu e Lima - PE, 53560-560";
        float fontSizeNormal = 10f;
        float endWidth = PDType1Font.HELVETICA.getStringWidth(endereco) / 1000 * fontSizeNormal;
        float xEnd = (pageWidth - endWidth) / 2;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, fontSizeNormal);
        cs.newLineAtOffset(xEnd, y);
        cs.showText(endereco);
        cs.endText();
        y -= 14;

        
        String cnpjLinha = "CNPJ: 11.111.111/1111";
        float cnpjWidth = PDType1Font.HELVETICA.getStringWidth(cnpjLinha) / 1000 * fontSizeNormal;
        float xCnpj = (pageWidth - cnpjWidth) / 2;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, fontSizeNormal);
        cs.newLineAtOffset(xCnpj, y);
        cs.showText(cnpjLinha);
        cs.endText();
        y -= 18;

        
        String cupomTitulo = "CUPOM FISCAL ELETRÔNICO";
        float fontSizeCupom = 12f;
        float cupomWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(cupomTitulo) / 1000 * fontSizeCupom;
        float xCupom = (pageWidth - cupomWidth) / 2;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, fontSizeCupom);
        cs.newLineAtOffset(xCupom, y);
        cs.showText(cupomTitulo);
        cs.endText();
        y -= 20;

        
        cs.setLineWidth(0.5f);
        cs.moveTo(margin, y);
        cs.lineTo(pageWidth - margin, y);
        cs.stroke();
        y -= 12;

        
        
        cs.beginText();
        cs.setFont(PDType1Font.COURIER_BOLD, 10);
        cs.newLineAtOffset(margin, y);
        cs.showText("#   DESC                         VL UNI UNI VL ITEM");
        cs.endText();
        y -= 14;

        
        if (pedido.getItensDescricao() != null) {
            cs.setFont(PDType1Font.COURIER, 10);
            int codigo = 1;
            for (String linha : pedido.getItensDescricao()) {
                if (y < margin + 80) {
                    break; 
                }
                cs.beginText();
                cs.newLineAtOffset(margin, y);
                String codStr = String.format("%03d ", codigo++);
                
                cs.showText(codStr + linha);
                cs.endText();
                y -= 12;
            }
        }

        
        y -= 6;
        cs.setLineWidth(0.5f);
        cs.moveTo(margin, y);
        cs.lineTo(pageWidth - margin, y);
        cs.stroke();
        y -= 14;

        
        String descontoStr = String.format("Descontos           %s", formatValor(pedido.getDesconto()));
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 11);
        cs.newLineAtOffset(margin, y);
        cs.showText(descontoStr);
        cs.endText();
        y -= 14;

        String acrescimoStr = String.format("Acréscimos          %s", formatValor(pedido.getAcrescimo()));
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 11);
        cs.newLineAtOffset(margin, y);
        cs.showText(acrescimoStr);
        cs.endText();
        y -= 14;

        String totalStr = String.format("TOTAL R$            %s", formatValor(pedido.getTotal()));
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
        cs.newLineAtOffset(margin, y);
        cs.showText(totalStr);
        cs.endText();
        y -= 18;

        
        String forma = pedido.getFormaPagamento() != null ? pedido.getFormaPagamento() : "";
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 11);
        cs.newLineAtOffset(margin, y);
        cs.showText("Forma de pagamento: " + forma);
        cs.endText();
        y -= 18;

        
        String dataFormatada = pedido.getDataHora() != null
                ? pedido.getDataHora().format(fmt)
                : "";

        if (!dataFormatada.isEmpty()) {
            float dataWidth = PDType1Font.HELVETICA.getStringWidth(dataFormatada) / 1000 * fontSizeNormal;
            float xData = (pageWidth - dataWidth) / 2;

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, fontSizeNormal);
            cs.newLineAtOffset(xData, y);
            cs.showText(dataFormatada);
            cs.endText();
            y -= 14;
        }

        String idLinha = "ID Pedido: " + pedido.getId();
        float idWidth = PDType1Font.HELVETICA.getStringWidth(idLinha) / 1000 * fontSizeNormal;
        float xId = (pageWidth - idWidth) / 2;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, fontSizeNormal);
        cs.newLineAtOffset(xId, y);
        cs.showText(idLinha);
        cs.endText();
        y -= 20;

        
        String obrigado = "Obrigado pela preferência!";
        float obrigadoWidth = PDType1Font.HELVETICA_OBLIQUE.getStringWidth(obrigado) / 1000 * fontSizeNormal;
        float xObrigado = (pageWidth - obrigadoWidth) / 2;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_OBLIQUE, fontSizeNormal);
        cs.newLineAtOffset(xObrigado, y);
        cs.showText(obrigado);
        cs.endText();

        cs.close();

        
        String userHome = System.getProperty("user.home");
        File pasta = new File(userHome, "Churrasqueiro_Pedidos");
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        String nomeArquivo = "pedido_mesa_" + pedido.getNumeroMesa()
                + "_id_" + pedido.getId() + ".pdf";

        File arquivoPdf = new File(pasta, nomeArquivo);
        doc.save(arquivoPdf);
        doc.close();

        
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(arquivoPdf);
            } catch (Exception e) {
                
            }
        }

        return arquivoPdf;
    }

    
    private static String formatValor(double valor) {
        return String.format("%.2f", valor).replace('.', ',');
    }
}
