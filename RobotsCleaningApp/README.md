# Robots Cleaning App

This project is an application to simulate the movement of robots on a grid, following specific instructions.

## Requirements

- **Java 17** or higher
- **Maven** (for dependency management and project build)

## Dependencies

The project uses the following key dependencies:
- **Lombok** (1.18.28) for reducing boilerplate code.
- **SLF4J** (2.0.9) and **Logback** (1.4.11) for logging.
- **JUnit Jupiter** (5.9.3) and **Mockito** (5.5.0) for testing.

## Setup

1. Clone this repository:

```
git clone https://github.com/danimartinmrt/robotsCleaningDMM.git
```

1. Build the project with Maven: 

```
mvn clean install
```


## Execution

To run the application, use the following command:

```
java -jar target/RobotsCleaningApp-1.0.0.jar
```
or from IDE:

```
Run as Java Application
```

## Tests

To run the tests, use:

```
mvn test
```


## Project Structure

- `src/main/java`: Main source code.
- `src/main/java/com/robotscleaningapp/dto/`: To represent data in a structured way (RobotDto and SquareAreaDto)
- `src/main/java/com/robotscleaningapp/dto/RobotDto`: Represents a robot with its main attributes, such as its initial position, orientation, assigned area of ​​the plant or factory, and the instructions it must follow to move on the grid.
- `src/main/java/com/robotscleaningapp/dto/SquareAreaDTO`: It represents the square area in which the robots move, defining their dimensions and limits.
- `src/main/java/com/robotscleaningapp/logic/Movements`: It represents the possible movements a robot can perform. Currently, it has a 90-degree rotation, but more movements can be added, such as 180 degrees, for example.
- `src/main/java/com/robotscleaningapp/service/impl/AreaInputService`: Represents the class responsible for defining the boundaries of the area. Multiple areas can be created with their corresponding dimensions.
- `src/main/java/com/robotscleaningapp/service/impl/RobotInputService`: Represents the class for capturing the initial position of robots. You can initialize multiple robots and assign them to different areas.
- `src/main/java/com/robotscleaningapp/service/impl/RobotMovementService`: Represents the class for processing robot movements, checking the boundaries of the area or other robots. 
- `src/main/java/com/robotscleaningapp/service/impl/RobotProcessingService`: Represents the class for processing robot movements and capturing their initial positions.
- `src/main/java/com/robotscleaningapp/util/Constants`: Represents the class where constants are defined to define the number of robots and areas to work with, default position of origin of coordinates or number of grid points to advance...
- `src/test/java`: Unit and integration tests.
- `src/main/resources`: Configuration files and resources.

## Author

- **Daniel Martín Martín**


