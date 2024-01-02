package es.uca.iw.fullstackwebapp.employee.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.fullstackwebapp.employee.domain.Employee;
import es.uca.iw.fullstackwebapp.employee.services.EmployeeService;

import java.util.Optional;

@PageTitle("Informacion del Empleado")
@Route(value = "informacion-employee")
public class InformacionEmployeeView extends VerticalLayout {
    private final EmployeeService employeeService;

    private final H1 title;
    private final TextField username;
    private final TextField firstname;
    private final TextField lastname;
    private final PasswordField password;
    private final Button loginButton;
    private final Button showInfoButton;

    private Optional<Employee> authenticatedEmployee;

    public InformacionEmployeeView(EmployeeService employeeService) {
        this.employeeService = employeeService;

        title = new H1("Información del Empleado");

        username = new TextField("Usuario");
        username.setId("Username");

        firstname = new TextField("Nombre");
        firstname.setId("Firstname");
        firstname.setReadOnly(true);

        lastname = new TextField("Apellido");
        lastname.setId("Lastname");
        lastname.setReadOnly(true);

        password = new PasswordField("Contraseña");
        password.setId("Password");

        loginButton = new Button("Iniciar Sesión", e -> login());

        showInfoButton = new Button("Mostrar Información", e -> showInfo());
        showInfoButton.setEnabled(false); // Inicialmente deshabilitado hasta que el usuario inicie sesión

        add(title, username, password, loginButton, showInfoButton, firstname, lastname);
    }

    private void login() {
        String usernameValue = username.getValue();
        String passwordValue = password.getValue();

        // Añade mensajes de depuración
        System.out.println("Intento de inicio de sesión: Usuario - " + usernameValue + ", Contraseña - " + passwordValue);

        authenticatedEmployee = employeeService.authenticate(usernameValue, passwordValue);

        if (authenticatedEmployee.isPresent()) {
            // Autenticación exitosa
            showInfoButton.setEnabled(true);
            // Actualizar los campos de nombre y apellido
            firstname.setValue(authenticatedEmployee.get().getFirstname());
            lastname.setValue(authenticatedEmployee.get().getLastname());
            Notification.show("Inicio de sesión exitoso", 3000, Notification.Position.MIDDLE);
        } else {
            // Autenticación fallida
            // Puedes mostrar un mensaje de error aquí si lo deseas
            showInfoButton.setEnabled(false);
            // Restablecer los campos de nombre y apellido
            firstname.clear();
            lastname.clear();
            Notification.show("Inicio de sesión fallido", 3000, Notification.Position.MIDDLE);
        }
    }

    private void showInfo() {
        // Lógica para mostrar la información del empleado
        // Puedes usar un nuevo componente o ventana emergente para mostrar los datos

        if (authenticatedEmployee.isPresent()) {
            // Imprime información de depuración
            System.out.println("Mostrar información del empleado: Usuario - " + authenticatedEmployee.get().getUsername());

            Notification.show(
                    "Información del empleado:\n" +
                            "Usuario: " + authenticatedEmployee.get().getUsername() + "\n" +
                            "Nombre: " + authenticatedEmployee.get().getFirstname() + "\n" +
                            "Apellido: " + authenticatedEmployee.get().getLastname(),
                    5000,
                    Notification.Position.MIDDLE
            );
        } else {
            Notification.show("Usuario no autenticado", 3000, Notification.Position.MIDDLE);
        }
    }
}
