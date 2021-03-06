package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import es.uca.iw.sss.spring.backend.entities.Event;
import es.uca.iw.sss.spring.backend.repositories.EventRepository;
import es.uca.iw.sss.spring.backend.services.EventService;
import es.uca.iw.sss.spring.backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class EventForm extends VerticalLayout implements KeyNotifier {
  private final EventRepository eventRepository;
  private Event event;
  private TextField photo = new TextField("Photo");
  private TextField description = new TextField("Description");
  private TextField name = new TextField("Name");
  private TextField price = new TextField("Price");
  private TextField aforum = new TextField("Aforum");
  private TextField date = new TextField("Date");
  private TextField init_time = new TextField("Open Time");
  private TextField end_time = new TextField("Close Time");
  private TextField licensePlate = new TextField("Ship License Plate");
  private EventService eventService;
  private ShipService shipService;
  private Button save = new Button("Save", VaadinIcon.CHECK.create());
  private Button cancel = new Button("Reset");
  private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public EventForm(
      EventRepository eventRepository, EventService eventService, ShipService shipService) {
    this.eventRepository = eventRepository;
    this.eventService = eventService;
    this.shipService = shipService;
    add(name, description, photo, aforum, price, date,init_time, end_time, licensePlate, actions);

    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editEvent(event));
    setVisible(false);
  }

  private void delete() {
    eventRepository.delete(event);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  private void save() {
    event.setDate(date.getValue());
    event.setPhoto(photo.getValue());
    event.setName(name.getValue());
    event.setDescription(description.getValue());
    event.setAforum(Long.parseLong(aforum.getValue()));
    event.setInit_time(init_time.getValue());
    event.setEnd_time(end_time.getValue());
    event.setPrice(Long.parseLong(price.getValue()));
    event.setShip(shipService.findByLicensePlate(licensePlate.getValue()));
    eventService.create(event);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editEvent(Event eventEdit) {
    if (eventEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = eventEdit.getId() != null;
    if (persisted) {
      event = eventRepository.findById(eventEdit.getId()).get();
    } else {
      event = eventEdit;
    }
    cancel.setVisible(persisted);

    if (event.getPhoto() != null) {
      photo.setValue(event.getPhoto());
    } else {
      photo.setValue("");
    }
    if (event.getName() != null) {
      name.setValue(event.getName());
    } else {
      name.setValue("");
    }
    if (event.getDescription() != null) {
      description.setValue(event.getDescription());
    } else {
      description.setValue("");
    }
    if (event.getAforum() != null) {
      aforum.setValue(Long.toString(event.getAforum()));
    } else {
      aforum.setValue("");
    }
    if (event.getPrice() != null) {
      price.setValue(Long.toString(event.getPrice()));
    } else {
      price.setValue("");
    }
    if (event.getInit_time() != null) {
      init_time.setValue(event.getInit_time());
    } else {
      init_time.setValue("");
    }
    if (event.getEnd_time() != null) {
      end_time.setValue(event.getEnd_time());
    } else {
      end_time.setValue("");
    }
    if (event.getShip() != null) {
      this.licensePlate.setEnabled(false);
      licensePlate.setValue(event.getShip().getLicensePlate());
    } else {
      licensePlate.setValue("");
    }
    if (event.getPhoto() != null) {
      date.setValue(event.getDate());
    } else {
      date.setValue("");
    }

      setVisible(true);

      name.focus();
  }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

    public TextField getShipPlate() {
        return licensePlate;
    }
}
