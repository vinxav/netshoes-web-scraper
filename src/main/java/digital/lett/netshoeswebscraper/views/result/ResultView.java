package digital.lett.netshoeswebscraper.views.result;

import com.vaadin.flow.component.Composite;
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
        Tabs tabs = new Tabs();
        getContent().setHeightFull();
        getContent().setWidthFull();
        tabs.setWidthFull();
        setTabsSampleData(tabs);
        getContent().add(tabs);
    }

    private void setTabsSampleData(Tabs tabs) {
        tabs.add(new Tab("Dashboard"));
        tabs.add(new Tab("Payment"));
        tabs.add(new Tab("Shipping"));
    }
}
