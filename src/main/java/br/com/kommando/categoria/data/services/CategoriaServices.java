package br.com.kommando.categoria.data.services;

import br.com.kommando.categoria.data.models.Categoria;
import br.com.kommando.categoria.repository.CategoriaRepository;
import br.com.kommando.exception.error.DataFoundException;
import br.com.kommando.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServices {

    @Autowired
    CategoriaRepository repository;

    @Autowired
    ProdutoRepository produtoRepository;

    public Categoria save(Categoria categoria) {
        isProdutoValido(categoria);
        return repository.save(categoria);
    }

    @Transactional(readOnly = true)
    public Categoria findCategoria(String id) {
        Optional<Categoria> categoriaOptional = repository.findById(id);
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
        } else {
            throw new DataFoundException("Categoria não encontrada");
        }
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAllCategorias() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new DataFoundException("Categoria não encontrada");
        }
    }

    public Categoria updateCategoria(Categoria categoria) {
        isProdutoValido(categoria);
        if (repository.findById(categoria.getId()).isPresent()) {
            deleteById(categoria.getId());
            return save(categoria);
        } else {
            throw new DataFoundException("Categoria não encontrada");
        }
    }

    private void isProdutoValido(Categoria categoria) {
        if (categoria.getItens() != null) {
            categoria.getItens().forEach(
                    s -> {
                        if (produtoRepository.findById(s).isEmpty())
                            throw new DataFoundException("Produto não encontrado");
                    }
            );
        }
    }
}
