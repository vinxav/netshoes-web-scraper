package digital.lett.netshoeswebscraper.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import digital.lett.netshoeswebscraper.entity.Product;
import digital.lett.netshoeswebscraper.exception.ConnectionException;
import digital.lett.netshoeswebscraper.exception.InvalidURLException;
import digital.lett.netshoeswebscraper.exception.NoProductException;
import digital.lett.netshoeswebscraper.service.ProductService;
@PageTitle("Home")
@Route(value = "")
@Uses(Icon.class)
public class HomeView extends Composite<VerticalLayout> {

    private final Details name = new Details();
    private final Details price = new Details();
    private final Details description = new Details();
    private final Details picture = new Details();

    public HomeView() {
        HorizontalLayout layoutRow = new HorizontalLayout();

        H1 title = new H1();
        title.setText("Netshoes Web Scraper");

        Paragraph subtitle = new Paragraph();
        subtitle.setText("Paste the Netshoes' product URL below to get its title, description, price and picture.");
        subtitle.getStyle().set("font-size", "var(--lumo-font-size-xl)");

        VerticalLayout productFields = new VerticalLayout();
        TextField url = new TextField();
        url.setLabel("URL");
        Button buttonScrape = new Button("Scrape",
                buttonClickEvent -> {
                    try {
                        setProductDetails(ProductService.scrapeProduct(url.getValue()), name, price, description, picture);
                    } catch (InvalidURLException | ConnectionException | NoProductException e) {
                        throw new RuntimeException(e);
                    }
                });
        Hr hr = new Hr();

        getContent().addClassName(Gap.XLARGE);
        getContent().addClassName(Padding.XLARGE);
        getContent().setWidthFull();
        getContent().setHeightFull();
        layoutRow.setWidthFull();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, title);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, subtitle);
        productFields.setWidthFull();
        productFields.addClassName(Gap.LARGE);
        productFields.setPadding(false);
        url.setWidthFull();
        productFields.setAlignSelf(FlexComponent.Alignment.CENTER, url);
        productFields.setAlignSelf(FlexComponent.Alignment.CENTER, buttonScrape);
        buttonScrape.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        productFields.setAlignSelf(FlexComponent.Alignment.CENTER, name);
        productFields.setAlignSelf(FlexComponent.Alignment.CENTER, price);
        productFields.setAlignSelf(FlexComponent.Alignment.CENTER, description);
        productFields.setAlignSelf(FlexComponent.Alignment.CENTER, picture);
        getContent().add(layoutRow);
        layoutRow.add(title);
        getContent().add(subtitle);
        productFields.add(url);
        productFields.add(buttonScrape);
        productFields.add(hr);
        getContent().add(productFields);

        productFields.add(name);
        productFields.add(price);
        productFields.add(description);
        productFields.add(picture);
        name.setSummaryText("Name");
        price.setSummaryText("Price");
        description.setSummaryText("Description");
        picture.setSummaryText("Picture");
        toggleDetailsVisibility(false, name, price, description, picture);
    }

    private void toggleDetailsVisibility(boolean value, Details... details) {
        for (Details i : details) {
            i.setVisible(value);
            i.setOpened(value);
        }
    }

    private void setProductDetails(Product product, Details name, Details price, Details description, Details picture) {
        Span nameSpan = new Span(new Text(product.name()));
        Span priceSpan = new Span(new Text(String.format("R$ %s", product.price().toString())));
        Span descriptionSpan = new Span(new Text(product.description()));
        Span pictureSpan = new Span(new Image(product.imageUrl(), product.name()));

        name.addContent(nameSpan);
        price.addContent(priceSpan);
        description.addContent(descriptionSpan);
        picture.addContent(pictureSpan);

        toggleDetailsVisibility(true, name, price, description, picture);
    }
}
