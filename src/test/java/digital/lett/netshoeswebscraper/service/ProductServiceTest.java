package digital.lett.netshoeswebscraper.service;

import digital.lett.netshoeswebscraper.entity.Product;
import digital.lett.netshoeswebscraper.exception.ConnectionException;
import digital.lett.netshoeswebscraper.exception.InvalidURLException;
import digital.lett.netshoeswebscraper.exception.NoProductException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void testValidURLVerification() {
        String product = "https://www.netshoes.com.br/tenis-nike-zoom-court-next-masculino-branco-2IC-1866-014";
        URL result = null;
        try {
            result = ProductService.parseUrl(product);
        } catch (InvalidURLException e) {
            throw new RuntimeException(e);
        }

        assertAll(() -> ProductService.parseUrl(product));
    }

    @Test
    void testNotNetshoesURLVerification() {
        String product = "https://www.fastshop.com.br/web/p/d/EXFE4DC_PRD/fogao-de-piso-electrolux-de-04-bocas-experience-com-perfectcook360-prata-fe4dc";
        assertThrows(InvalidURLException.class, () -> ProductService.parseUrl(product));
    }

    @Test
    void testInvalidURLVerification() {
        String product = "maquina-de-lavar";
        assertThrows(InvalidURLException.class, () -> ProductService.parseUrl(product));
    }

    @Test
    void testNoProduct() {
        String product = "https://www.netshoes.com.br/lst/mi-outlet?mi=hm_ger_mntop_outlet_211122&psn=Menu_Top";
        assertThrows(NoProductException.class, () -> ProductService.scrapeProduct(product));
    }

    @Test
    void testProduct() throws NoProductException, InvalidURLException, ConnectionException {
        String productURL = "https://www.netshoes.com.br/tenis-nike-structure-25-masculino-preto-JD8-4935-006";

        assertAll(() -> ProductService.scrapeProduct(productURL));
    }
}