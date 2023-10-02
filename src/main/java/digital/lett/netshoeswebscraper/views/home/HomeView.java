package digital.lett.netshoeswebscraper.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "")
@Uses(Icon.class)
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        H1 h1 = new H1();
        Paragraph textLarge = new Paragraph();
        TextField textField = new TextField();
        Button buttonPrimary = new Button();
        getContent().setHeightFull();
        getContent().setWidthFull();
        h1.setText("Welcome to the Netshoes Web Scraper!");
        textLarge.setText("Paste the Netshoes' product URL below to get its title, description, price and photo.");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        textField.setLabel("URL");
        textField.setWidthFull();
        buttonPrimary.setText("Continue");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary);
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(h1);
        getContent().add(textLarge);
        getContent().add(textField);
        getContent().add(buttonPrimary);
    }
}
