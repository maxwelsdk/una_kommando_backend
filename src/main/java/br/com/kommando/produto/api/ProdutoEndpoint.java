package br.com.kommando.produto.api;

import br.com.kommando.produto.data.models.Produto;
import br.com.kommando.produto.data.services.ProdutoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("produtos")
public class ProdutoEndpoint {

    @Autowired
    ProdutoServices produtoServices;

    @GetMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> getProduto(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("produto", produtoServices.findById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getProdutos() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("produtos", produtoServices.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteProduto(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        produtoServices.deleteById(id);
        response.put("Produto deletado", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> newProduto(@RequestBody Produto produto) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "produto criado com sucesso!");
        response.put("produto", produtoServices.save(produto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HashMap<String, Object>> updateProduto(@RequestBody Produto produto) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "produto atualizado com sucesso!");
        response.put("produto", produtoServices.update(produto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
