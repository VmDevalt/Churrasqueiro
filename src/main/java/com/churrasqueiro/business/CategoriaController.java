package com.churrasqueiro.business;

import com.churrasqueiro.data.CategoriaDAO;
import com.churrasqueiro.entities.Categoria;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaController {

    private final CategoriaDAO categoriaDAO;

    public CategoriaController() {
        this.categoriaDAO = new CategoriaDAO();
    }

    private void validarCategoria(Categoria categoria) throws ControllerException, DatabaseException {
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new ControllerException("O nome da categoria é obrigatório.");
        }
        
        try {
            Optional<Categoria> categoriaExistente = categoriaDAO.buscarPorNome(categoria.getNome());
            if (categoriaExistente.isPresent() && categoriaExistente.get().getId() != categoria.getId()) {
                throw new ControllerException("Já existe uma categoria cadastrada com o nome: " + categoria.getNome());
            }
        } catch (DatabaseException e) {
             throw e; 
        }
    }


    public Categoria inserir(Categoria categoria) throws ControllerException, DatabaseException {
        validarCategoria(categoria); 
        
        return categoriaDAO.inserir(categoria); 
    }
    
    public List<Categoria> listarTodas() throws DatabaseException {
        return categoriaDAO.listarTodos(); 
    }
    
    public Categoria buscarPorId(int id) throws ControllerException, DatabaseException {
        Optional<Categoria> categoriaOpt = categoriaDAO.buscarPorId(id);
        
        if (categoriaOpt.isEmpty()) {
            throw new ControllerException("Categoria com ID " + id + " não encontrada.");
        }
        return categoriaOpt.get();
    }
}