package br.com.kommando.item;

import br.com.kommando.item.api.ItemEndpoint;
import br.com.kommando.item.data.models.Item;
import br.com.kommando.produto.data.models.Produto;
import org.junit.jupiter.api.Test;

public class ItemTests {

    @Test
    void test1() {
        ItemEndpoint endpoint = new ItemEndpoint();
        endpoint.newItem(new Item("1", new Produto(), 1));
    }
}
