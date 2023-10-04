package digital.lett.netshoeswebscraper.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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
@PageTitle("Netshoes Web Scraper")
@Route(value = "")
@Uses(Icon.class)
@StyleSheet("https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;700;800;900&display=swap")
public class HomeView extends Composite<VerticalLayout> {

    private final Details name = new Details();
    private final Details price = new Details();
    private final Details description = new Details();
    private final Details picture = new Details();

    public HomeView(ProductService service) {
        HorizontalLayout headerLayout = new HorizontalLayout();
        H1 title = new H1("Netshoes Web Scraper");
        title.addClassName("home-view-h1-1");
        Paragraph subtitle = new Paragraph("Cole o link da página de um produto da Netshoes para extrair o seu título, valor, descrição e imagem.");
        subtitle.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        headerLayout.setAlignSelf(FlexComponent.Alignment.CENTER, title, subtitle);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addClassName("home-view-vertical-layout-1");
        TextField url = new TextField("URL");
        Button buttonScrape = new Button("Extrair");
        buttonScrape.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonScrape.setDisableOnClick(true);
        buttonScrape.addClickListener(event -> {
                    try {
                        setProductDetails(service.scrapeProduct(url.getValue()));
                    } catch (InvalidURLException e) {
                        showErrorNotification("A URL fornecida é inválida.");
                    } catch (ConnectionException e) {
                        showErrorNotification("Erro na conexão com a Netshoes.");
                    } catch (NoProductException e) {
                        showErrorNotification("A URL não contém um produto disponível.");
                    }
                    buttonScrape.setEnabled(true);
                });
        mainLayout.setAlignSelf(FlexComponent.Alignment.CENTER, url, buttonScrape);
        url.setWidthFull();

        VerticalLayout productDataLayout = new VerticalLayout();
        name.setSummaryText("Título");
        price.setSummaryText("Valor");
        description.setSummaryText("Descrição");
        picture.setSummaryText("Imagem");

        headerLayout.add(title);
        mainLayout.add(subtitle, url, buttonScrape, new Hr());
        productDataLayout.add(name, price, description, picture);
        changeProductDetailsVisibility(false);
        setLayoutPreferences(headerLayout, mainLayout, productDataLayout);
        getContent().add(headerLayout, mainLayout, productDataLayout);
    }

    private void setProductDetails(Product product) {
        name.setContent(new Span(new Text(product.name())));
        price.setContent(new Span(new Text(String.format("R$ %s", product.price().toString()))));
        description.setContent(new Span(new Text(product.description())));
        picture.setContent(new Span(new Image(product.imageUrl(), product.name())));

        changeProductDetailsVisibility(true);
    }

    private void showErrorNotification(String message) {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setDuration(5000);
        notification.setPosition(Notification.Position.TOP_END);

        Div text = new Div(new Text(message));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> notification.close());

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(Alignment.CENTER);

        notification.add(layout);
        notification.open();
    }

    private void changeProductDetailsVisibility(boolean value) {
        name.setVisible(value);
        name.setOpened(value);
        price.setVisible(value);
        price.setOpened(value);
        description.setVisible(value);
        description.setOpened(value);
        picture.setVisible(value);
        picture.setOpened(value);
    }

    private void setLayoutPreferences(HorizontalLayout headerLayout, VerticalLayout mainLayout, VerticalLayout productDataLayout) {
        getContent().addClassName(Gap.XLARGE);
        getContent().addClassName(Padding.XLARGE);
        getContent().setWidthFull();
        getContent().setHeightFull();
        headerLayout.setWidthFull();
        headerLayout.addClassName(Gap.MEDIUM);
        headerLayout.setAlignItems(Alignment.CENTER);
        headerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setWidthFull();
        mainLayout.addClassName(Gap.LARGE);
        mainLayout.setPadding(false);
        productDataLayout.setPadding(false);
        productDataLayout.setWidthFull();
        productDataLayout.setAlignSelf(Alignment.STRETCH, name, price, description, picture);
    }


}
