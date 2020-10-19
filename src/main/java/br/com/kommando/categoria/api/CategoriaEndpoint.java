package br.com.kommando.categoria.api;

import br.com.kommando.categoria.data.models.Categoria;
import br.com.kommando.categoria.data.services.CategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("categorias")
public class CategoriaEndpoint {

    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping
    ResponseEntity<HashMap<String, Object>> getAllCategorias() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("categorias", categoriaServices.findAllCategorias());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<HashMap<String, Object>> getCategoriaById(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("categoria", categoriaServices.findCategoria(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<HashMap<String, Object>> newCategoria(@RequestBody Categoria categoria) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "Categoria criada com sucesso!");
        response.put("categoria", categoriaServices.save(categoria));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<HashMap<String, Object>> deleteById(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        categoriaServices.deleteById(id);
        response.put("msg", "Categoria deletada com sucesso!");
        response.put("id", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    ResponseEntity<HashMap<String, Object>> updateUser(@RequestBody Categoria categoria) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "Categoria atualizada com sucesso!");
        response.put("categoria", categoriaServices.updateCategoria(categoria));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
