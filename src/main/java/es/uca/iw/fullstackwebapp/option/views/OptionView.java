package es.uca.iw.fullstackwebapp.option.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import es.uca.iw.fullstackwebapp.option.services.OptionService;
import es.uca.iw.fullstackwebapp.option.domain.Option;

@Route("option")  // Puedes ajustar la ruta según tus necesidades
public class OptionView extends VerticalLayout {
    private final OptionService optionService;
    private final Grid<Option> grid = new Grid<>(Option.class);
    private final TextField name = new TextField("Name");
    private final Button save = new Button("Save", e -> saveOption());

    private final Binder<Option> binder = new Binder<>(Option.class);

    public OptionView(OptionService optionService) {
        this.optionService = optionService;

        grid.setColumns("id", "name");

        add(grid, name, save);

        binder.bindInstanceFields(this);

        grid.setItems(optionService.getAllOptions());

        //grid.asSingleSelect().addValueChangeListener(e -> binder.setBean(e.getValue()));

        //updateGrid();
    }

    private void updateGrid() {
        grid.setItems(optionService.getAllOptions());
        setFormVisible(true);
    }

    private void saveOption() {
        Option option = binder.getBean();
        optionService.saveOption(option);
        updateGrid();
    }

    private void setFormVisible(boolean visible) {
        name.setVisible(visible);
        save.setVisible(visible);
    }
}
