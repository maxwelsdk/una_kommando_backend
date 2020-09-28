package br.com.kommando.pedido.api;

import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.pedido.data.services.PedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("pedidos")
public class PedidoEndpoint {

    @Autowired
    PedidoServices pedidoServices;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getPedidos() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("pedidos", pedidoServices.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> novoPedido(@RequestBody Pedido pedido) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "pedido criado com sucesso!");
        response.put("pedido", pedidoServices.savePedido(pedido));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> getPedido(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("pedidos", pedidoServices.findById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HashMap<String, Object>> updatePedido(@RequestBody Pedido pedido) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("pedido alterado com sucesso", pedidoServices.updatePedido(pedido));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> deletePedido(@PathVariable String id) {
        try {
            pedidoServices.deleteById(id);
            HashMap<String, Object> response = new HashMap<>();
            response.put("Pedido deletado", id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }


}