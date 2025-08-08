# Pomodoro Desktop App

This is a Java desktop application that implements the Pomodoro technique, allowing users to configure work time, break time, and the number of Pomodoro cycles. The application features a graphical interface built with Swing and custom components.

## Features

- Configure work duration, break duration, and number of Pomodoros.
- Animated clock canvas for visual feedback.
- Custom gradient panels and modern UI.
- Sound notifications at the end of each session.
- MVC architecture for maintainability.
- All user messages and interface texts remain in Spanish.

## Project Structure

``` bash
src/
  main/
    java/
      com/
        mycompany/
          pomodoro/
            Pomodoro.java                # Main entry point
            controller/
              App.java                   # Application bootstrap
              Animator.java              # Animation and timer logic
              SoundController.java  # Sound controller
              ClockController.java      # Clock controller
              IView.java                # View interface
              UserFlowController/
                UserFlowController.java # User flow controller (state machine)
                pomodoroState.java      # Pomodoro state enum
                AbstractView.java       # Abstract view logic
                WorkTimeView.java
                BreakTimeView.java
                PomodoroCountView.java
                IniciarConteoRegresivo.java
                SkipPomodoroView.java
            model/
              PomodoroConfig.java    # Singleton for Pomodoro configuration
            view/
              MainFrame.java          # Main window
              ClockCanvas.java         # Custom clock canvas
              componentes/
                CustomPanel.java         # Custom gradient panel
              icons/
              images/
    resources/
      alarma1.wav                        # Alarm sound
      IconoDeRelojParaPomodoro.ico       # App icon
```

## How to Run

1. Make sure you have Java 17 or later installed.
2. Build the project with your preferred IDE (NetBeans, VS Code, IntelliJ) or using Maven.
3. Run the `Pomodoro.java` main class.

## Usage

1. Set the desired work time, break time, and number of Pomodoros using the graphical interface.
2. Click "Siguiente" to advance through each configuration step.
3. Click "Iniciar" to start the Pomodoro cycles.
4. The app will notify you with a sound at the end of each session.

## Authors

- Developed by jmona

---

*Note: All interface texts and user messages are in Spanish, but the codebase is being migrated to English for better accessibility.*
