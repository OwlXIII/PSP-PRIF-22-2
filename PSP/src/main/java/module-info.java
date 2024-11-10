module coursework.psp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.desktop;

    opens coursework.psp to javafx.fxml;
    exports coursework.psp;
}