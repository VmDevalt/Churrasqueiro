package com.churrasqueiro.business;

import com.churrasqueiro.data.PedidoDAO;
import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.entities.PedidoResumo;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.ui.TelaLogin;


import java.util.List;

public class PedidoController {

    private final PedidoDAO pedidoDAO;

    public PedidoController() {
        this.pedidoDAO = new PedidoDAO();
    }

    public List<PedidoResumo> listarPedidosPorStatus(String status)
            throws DatabaseException, ControllerException {

        if (status == null || status.isBlank()) {
            throw new ControllerException("Status do pedido não pode ser vazio.");
        }

        return pedidoDAO.listarPorStatus(status);
    }

    public void alterarStatusPedido(int pedidoId, String novoStatus)
            throws DatabaseException, ControllerException {

        if (pedidoId <= 0) {
            throw new ControllerException("ID de pedido inválido.");
        }
        if (novoStatus == null || novoStatus.isBlank()) {
            throw new ControllerException("Novo status não pode ser vazio.");
        }

        pedidoDAO.atualizarStatus(pedidoId, novoStatus);
    }

     public void salvar(PedidoEmMontagem pedido) throws ControllerException, DatabaseException {

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new ControllerException("Adicione pelo menos um item ao pedido.");
        }
        if (pedido.getNumeroMesa() == null || pedido.getNumeroMesa().isBlank()) {
            throw new ControllerException("Selecione a mesa do pedido.");
        }
        if (pedido.getFormaPagamento() == null || pedido.getFormaPagamento().isBlank()) {
            throw new ControllerException("Selecione a forma de pagamento.");
        }

        Usuario usuarioLogado = TelaLogin.getUsuarioLogado();
        int garconId;

        if (usuarioLogado != null && usuarioLogado.getId() != 0) {
            garconId = usuarioLogado.getId();
        } else {
            garconId = 1;
        }

        pedidoDAO.inserirPedidoComItens(pedido, garconId);
    }
}
