package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {
        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();
        ComboBox<String> comboBox = new ComboBox<>("Browser");
        comboBox.setAllowCustomValue(false); //este deja que el usuario escriba lo que quiera en la caja del comboBox. Si se pone a false no deja
        comboBox.setItems("people", "planets", "starships");
        comboBox.setHelperText("Selecciona el tipo de petición");

        Grid<Character> grid = new Grid<>(Character.class, true);
        grid.addColumn(Character::getName).setHeader("Nombre");
        grid.addColumn(Character::getHeight).setHeader("Altura");
        grid.addColumn(Character::getMass).setHeader("Peso");
        grid.addColumn(Character::getHair_color).setHeader("Color de pelo");
        grid.addColumn(Character::getEye_color).setHeader("Color de ojo");

        TextField requestId = new TextField("Request id");
        requestId.addThemeName("bordered");
        inputs.add(comboBox, requestId);
        Button boton1 = new Button("Lee caracter",
                e -> {
                    String tipo = comboBox.getValue();
                    int id = Integer.parseInt(requestId.getValue());
                    try {
                        results.removeAll();
                        results.add(service.getSWAPI(tipo,id));
                    } catch (Exception ex) {
                    }
                });
        Button boton2 = new Button("Lee lista caracteres",
                e -> {
                    String tipo = comboBox.getValue();

                    try {
                        grid.setItems(service.getCharList(tipo));
                        results.add(grid);
                    } catch (Exception ex) {
                    }
                });
        boton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        boton1.addClickShortcut(Key.ENTER);
        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(inputs, boton1,boton2, results);
    }

}
