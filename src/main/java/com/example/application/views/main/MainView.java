package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("MaraBytes")
@Route(value = "")
public class MainView extends VerticalLayout {

    Button button;
    TextField textField;
    PasswordField password;

    public MainView() {
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setTitle("MaraBytes");
        loginOverlay.setDescription("Welcome to MaraBytes");
        Paragraph text = new Paragraph("Never tell your password to anyone");
        text.addClassName(LumoUtility.TextAlignment.CENTER);
        loginOverlay.getFooter().add(text);
        add(loginOverlay);
        loginOverlay.setOpened(true);
        loginOverlay.addLoginListener(event -> {
            String enteredEmail = event.getUsername();
            String enteredPassword = event.getPassword();

            if ("lunique604@gmail.com".equals(enteredEmail) && "marabytes123".equals(enteredPassword)) {
                // Redirect to a different page
                getUI().ifPresent(ui -> ui.navigate(DifferentPage.class));
            } else {
                // Show an error message
                Notification.show("Invalid email or password", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                loginOverlay.setEnabled(true);
                loginOverlay.getElement().executeJs("this.$.username.value = ''; this.$.password.value = '';");

                // Focus on the username field after clearing the input fields
                loginOverlay.getElement().executeJs("this.$.username.focus();");
            }
        });
        // Prevent the example from stealing focus when browsing the
        // documentation
        loginOverlay.getElement().setAttribute("no-autofocus", "");
    }

}
