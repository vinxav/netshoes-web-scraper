package digital.lett.netshoeswebscraper.service;

import digital.lett.netshoeswebscraper.entity.Product;
import digital.lett.netshoeswebscraper.exception.ConnectionException;
import digital.lett.netshoeswebscraper.exception.InvalidURLException;
import digital.lett.netshoeswebscraper.exception.NoProductException;
import digital.lett.netshoeswebscraper.exception.NotNetshoesURLException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class ProductService {

    public static Product scrapeProduct(String url) throws InvalidURLException, ConnectionException, NoProductException {
        Document page = connectToUrl(url);
        String name;
        String description;
        String price;
        String imageUrl;

        try {
            name = Objects.requireNonNull(page.select("h1[data-productName]").first()).text();
            description = Objects.requireNonNull(page.select("p[itemprop = description]").first()).text();
            price = Objects.requireNonNull(page.select("div.default-price strong").first()).text();
            imageUrl = Objects.requireNonNull(page.getElementsByClass("zoom").first()).attributes().get("src");
        } catch (NullPointerException e) {
            throw new NoProductException("The provided page is not a valid product");
        }

        return new Product(
                name,
                description,
                new BigDecimal(price.replaceAll("[^\\d.,]", "").replace(",", ".")),
                imageUrl
        );
    }

    public static Document connectToUrl(String urlString) throws ConnectionException, InvalidURLException {
        URL url = parseUrl(urlString);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        ClassicHttpRequest getRequest = ClassicRequestBuilder.get(String.format("https://www.netshoes.com.br%s", url.getPath())).build();

        Document page;
        try {
            page = httpClient.execute(getRequest, response -> Jsoup.parse(EntityUtils.toString(response.getEntity())));
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage());
        }

        return page;
    }

    public static URL parseUrl(String urlString) throws InvalidURLException {
        URL url;
        try {
            url = new URI(urlString).toURL();
            if (!url.getHost().contains("www.netshoes.com.br") || url.getPath().isBlank()) {
                throw new NotNetshoesURLException();
            }
        } catch (NotNetshoesURLException | IllegalArgumentException | URISyntaxException | MalformedURLException e) {
            throw new InvalidURLException("Not a proper www.netshoes.com.br URL");
        }

        return url;
    }
}
