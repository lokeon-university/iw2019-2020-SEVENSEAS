package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@Route(value = "ManageCity", layout = MainLayout.class)
@PageTitle("Manage City")
public class ManageCityView extends VerticalLayout {

    final TextField filter;
    final Grid<City> cityGrid;
    private final CityRepository cityRepository;
    private final CityForm cityForm;
    private final Button addCity;
    private final Button advices;
    final City[] citySelected = new City[1];

    public ManageCityView(CityRepository cityRepository, CityForm cityForm)
    {
        this.cityRepository = cityRepository;
        this.cityForm = cityForm;
        this.cityGrid = new Grid<>(City.class);
        this.filter = new TextField();
        this.addCity = new Button("New City", VaadinIcon.PLUS.create());
        this.advices = new Button("Manage Advices", e -> AdviceView());
        advices.setEnabled(false);

        HorizontalLayout actions = new HorizontalLayout(filter, addCity,advices);
        add(actions, cityGrid, cityForm);

        cityGrid.setColumns("id","name","pic");
        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCities(e.getValue()));


        cityGrid.asSingleSelect().addValueChangeListener(e -> {
            cityForm.editCity(e.getValue());
            citySelected[0] = e.getValue();
            advices.setEnabled(true);
        });

        addCity.addClickListener(e -> cityForm.editCity(new City()));

        cityForm.setChangeHandler(() -> {
            cityForm.setVisible(false);
            listCities(filter.getValue());
        });

        listCities(null);

    }

    void listCities(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            cityGrid.setItems(cityRepository.findAll());
        }
        else {
            cityGrid.setItems(cityRepository.findByNameStartsWithIgnoreCase(filterText));
        }
    }

   public void AdviceView()
    {
        UI.getCurrent().navigate(ManageCityAdviceView.class,citySelected[0].getId());
        UI.getCurrent().getPage().reload();
    }

}
