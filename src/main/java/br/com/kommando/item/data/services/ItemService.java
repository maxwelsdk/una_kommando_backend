package br.com.kommando.item.data.services;

import br.com.kommando.exception.error.DataFoundException;
import br.com.kommando.exception.error.DataNotFoundException;
import br.com.kommando.item.data.models.Item;
import br.com.kommando.item.repository.ItemRepository;
import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.pedido.repository.PedidoRepository;
import br.com.kommando.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item save(String pedidoId, Item item) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        if (pedidoOptional.isEmpty()) {
            throw new DataNotFoundException("Pedido não encontrado");
        }
        if (produtoRepository.findById(item.getProdutoId()).isPresent()) {
            Pedido pedido = pedidoOptional.get();
            item.setPedidoId(pedidoId);
            Item itemSalvo = repository.save(item);
            pedido.getItems().add(itemSalvo);
            pedidoRepository.save(pedido);
            return itemSalvo;
        } else {
            throw new DataNotFoundException("Produto não encontrado");
        }
    }

    public Optional<Item> findById(String id) {
        return repository.findById(id);
    }

    public void removeById(String id) {
        try {
            Optional<Item> itemOptional = repository.findById(id);
            if (itemOptional.isPresent()) {
                Item item = itemOptional.get();
                Optional<Pedido> pedidoOptional = pedidoRepository.findById(item.getPedidoId());
                int index = -1;
                if (pedidoOptional.isPresent()) {
                    for (int i = 0; i < pedidoOptional.get().getItems().size(); i++) {
                        if (pedidoOptional.get().getItems().get(i).getId().equals(item.getId())) {
                            index = i;
                            repository.deleteById(id);
                        }
                    }
                    if (index != -1) {

                        pedidoOptional.get().getItems().remove(index);
                    }
                    pedidoRepository.save(pedidoOptional.get());
                }
            } else {
                throw new DataFoundException("Item não existe!");
            }

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
