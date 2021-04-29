
<div class="container-lg clearfix">
  <div class="col-4 float-right border p-4">
    UTSA CS-3443-003:
  </div>
  <div class="col-4 float-right border p-4">
    Spring 2021
  </div>
  <div class="col-4 float-right border p-4">
    Created by: @arduino731, @ryanGill8120, @judenelson468
  </div>
</div>

<h1 align="center"> Energy Balance</h1>
 <h2 align="center"> A Nutrition Applicaion </h1>
  

<p align="center">
  <img src="./doc/logo.png?raw=true">
</p>

# Description 

The application Energy Balance will track the user's daily :date: and meal data :plate_with_cutlery: based on their input and display information. You can add and save meal and workout types so that tracking your nutrition can be easy!

# Mission Goal 
Understanding the relationship between the calories you burn and calories you consume is called Energy Balance. Our app will allow you to track this data easily so that you can pursue a healthy lifestyle. Useful for anyone who is looking to lose weight, be healthy or learn more about their manageable nutrition.

## Table of Contents
- [Quickstart](#quickstart)

## Quickstart:
- [Download the latest release](https://github.com/ryanGill8120/energy-balance/releases/tag/1.0.0) of Energy Balance directly from GitHub. ([Beta](https://github.com/ryanGill8120/energy-balance/releases/tag/1.0.0))
- Clone the repo: `git clone https://github.com/ryanGill8120/energy-balance.git`
- Download IDE Eclipse that is right for you (https://www.eclipse.org/downloads/)
- [Configure JAVAFX](#configure JAVAFX) - Install the Eclipse JavaFX plugin Version (1.2.0) - (https://download.eclipse.org/efxclipse/updates-released/)
- [SceneBuilder](#sceneBuilder) - If Scenebuilder is not installed: Please install SceneBuider: Download the installer Version (2.0) (https://www.oracle.com/java/technologies/javafxscenebuilder-1x-archive-downloads.html)  

## Configure JAVAFX:
1. create a new project on eclipse.
2. create a simple class namely Hello.
3. to configure JavaFx. right click on the project and click on properties. ![alt text](./doc/cjfx1.jpg?raw=true)
4. Go to Java Build path, and click on libraries. ![alt text](./doc/cjfx2.jpg?raw=true) 
5. Click on add library, click on JRE System Library, click next, ![alt text](./doc/cjfx3.jpg?raw=true) 
6. Select workspace default JDK 8/JRE 8, click finish. ![alt text](./doc/cjfx4.jpg?raw=true) 
7. Click on add Library. ![alt text](./doc/cjfx5.jpg?raw=true)
8. Click on User library, click next. ![alt text](./doc/cjfx6.jpg?raw=true)
9. Click on new and name the file as JavaFX. ![alt text](./doc/cjfx7.jpg?raw=true) 
10. Click on Add External JARs. Specify the path, java>jdk8>jre>file>jfxswt.jar ![alt text](./doc/cjfx8.jpg?raw=true)
11. Finish the task. Following Screen appears ![alt text](./doc/cjfx9.jpg?raw=true)
12. Paste the following Sample JavaFX code in Hello class
```
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Hello extends Application {
 public static void main(String[] args) {
 launch(args);
 }

 @Override
 public void start(Stage primaryStage) {
 primaryStage.setTitle("Hello World!");
 Button btn = new Button();
 btn.setText("Say 'Hello World'");
 btn.setOnAction(new EventHandler<ActionEvent>() {
 @Override
 public void handle(ActionEvent event) {
 System.out.println("Hello World!");
 }
 });

 StackPane root = new StackPane();
 root.getChildren().add(btn);
 primaryStage.setScene(new Scene(root, 300, 250));
 primaryStage.show();
 }
}
```
13. If installed properly following screen will appear. ![alt text](./doc/cjfx10.jpg?raw=true)


## SceneBuilder:
1. Go to Windows tab and click on preferences.
2. ![alt text](./doc/sceneBuilder.jpg?raw=true)
3.To test out your installation of JavaFX and SceneBuilder, do a "Hello, World" exercise:
- 1. Create a new JavaFX Project: File - New - Other - JavaFX Project
- 2. Run this as a Java application
4. Completing this test generates the basic code for "hello, world", and running it displays a small white
window.
If this basic example program does not work for you, ensure you have followed the necessary steps for
installation. 

# Project Proposal 
![alt text](./doc/1.jpg?raw=true)
![alt text](./doc/2.jpg?raw=true)
![alt text](./doc/3.jpg?raw=true)
![alt text](./doc/4.jpg?raw=true)
