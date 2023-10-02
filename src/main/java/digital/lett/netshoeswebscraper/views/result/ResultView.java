package digital.lett.netshoeswebscraper.views.result;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Result")
@Route(value = "result")
@Uses(Icon.class)
public class ResultView extends Composite<VerticalLayout> {

    public ResultView() {
        Accordion accordion = new Accordion();
        getContent().setHeightFull();
        getContent().setWidthFull();
        accordion.setWidthFull();
        setAccordionPanels(accordion);
        getContent().add(accordion);
    }

    private void setAccordionPanels(Accordion accordion) {
        accordion.add(new AccordionPanel("Name"));
        accordion.add(new AccordionPanel("Description"));
        accordion.add(new AccordionPanel("Price"));
        accordion.add(new AccordionPanel("Pictures"));
    }
}
