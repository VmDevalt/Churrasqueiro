package com.churrasqueiro.business;

import com.churrasqueiro.data.ItemCardapioDAO;
import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemCardapioController {

    private final ItemCardapioDAO itemCardapioDAO;
    public ItemCardapioController() {
        this.itemCardapioDAO = new ItemCardapioDAO(); 
    }
    
    private void validarItem(ItemCardapio item) throws ControllerException, DatabaseException {
        if (item.getNome() == null || item.getNome().trim().isEmpty()) {
            throw new ControllerException("O nome do item é obrigatório.");
        }
        
        if (item.getPreco() <= 0) {
            throw new ControllerException("O preço do item deve ser positivo.");
        }

        Optional<ItemCardapio> itemExistente = itemCardapioDAO.buscarPorNome(item.getNome());
		
		if (itemExistente.isPresent() && itemExistente.get().getId() != item.getId()) {
		    throw new ControllerException("Já existe um item cadastrado com o nome: " + item.getNome());
		}
    }

    public List<ItemCardapio> listarTodos() throws DatabaseException {
        return itemCardapioDAO.listarTodos(); 
    }

    public ItemCardapio cadastrarItem(ItemCardapio item) throws ControllerException, DatabaseException {
        validarItem(item); 
        return itemCardapioDAO.inserir(item); 
    }
    
}