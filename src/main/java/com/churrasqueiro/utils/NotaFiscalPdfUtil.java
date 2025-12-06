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

        float margin = 50;
        float yStart = page.getMediaBox().getHeight() - margin;
        float x = margin;
        float y = yStart;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
        cs.newLineAtOffset(x, y);
        cs.showText("O CHURRASQUEIRO BURGUER");
        cs.endText();

        y -= 25;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 12);
        cs.newLineAtOffset(x, y);
        cs.showText("Comprovante / Nota Fiscal Simples do Pedido");
        cs.endText();

        y -= 30;

        String dataFormatada = pedido.getDataHora() != null
                ? pedido.getDataHora().format(fmt)
                : "";

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 12);
        cs.newLineAtOffset(x, y);
        cs.showText("Mesa: " + pedido.getNumeroMesa());
        cs.endText();
        y -= 15;

        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText("Garçom: " + pedido.getGarconLogin());
        cs.endText();
        y -= 15;

        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText("Data/Hora: " + dataFormatada);
        cs.endText();
        y -= 15;

        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText("Forma de Pagamento: " + pedido.getFormaPagamento());
        cs.endText();
        y -= 25;

        cs.setLineWidth(0.5f);
        cs.moveTo(x, y);
        cs.lineTo(page.getMediaBox().getWidth() - margin, y);
        cs.stroke();
        y -= 20;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
        cs.newLineAtOffset(x, y);
        cs.showText("ITENS");
        cs.endText();
        y -= 20;

        if (pedido.getItensDescricao() != null) {
            cs.setFont(PDType1Font.HELVETICA, 11);
            for (String linha : pedido.getItensDescricao()) {
                if (y < margin + 50) {
                    break;
                }
                cs.beginText();
                cs.newLineAtOffset(x, y);
                cs.showText(linha);
                cs.endText();
                y -= 15;
            }
        }

        y -= 15;
        cs.setLineWidth(0.5f);
        cs.moveTo(x, y);
        cs.lineTo(page.getMediaBox().getWidth() - margin, y);
        cs.stroke();
        y -= 20;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA, 12);
        cs.newLineAtOffset(x, y);
        cs.showText(String.format("Desconto: R$ %.2f", pedido.getDesconto()).replace('.', ','));
        cs.endText();
        y -= 15;

        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(String.format("Acréscimo: R$ %.2f", pedido.getAcrescimo()).replace('.', ','));
        cs.endText();
        y -= 15;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 13);
        cs.newLineAtOffset(x, y);
        cs.showText(String.format("TOTAL: R$ %.2f", pedido.getTotal()).replace('.', ','));
        cs.endText();
        y -= 30;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_OBLIQUE, 11);
        cs.newLineAtOffset(x, y);
        cs.showText("Obrigado pela preferência!");
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
}
