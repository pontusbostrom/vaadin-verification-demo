Simpleimageviewer
==============

This application demonstrates how the tool Infer can be used to check a small Vaadin application for null pointer dereferencing and resource leaks. The application itself is small image viewer where a user can upload images and select one of them for viewing.
The application is based on a template for a simple Vaadin application that only requires a Servlet 3.0 container to run.


Building
========

To compile the entire project, run "mvn install".
To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To develop the theme, simply update the relevant theme files and reload the application.
Pre-compiling a theme eliminates automatic theme updates at runtime - see below for more information.

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean vaadin:compile-theme package"
  - See below for more information. Running "mvn clean" removes the pre-compiled theme.
- test with "mvn jetty:run-war

Using a precompiled theme
-------------------------

When developing the application, Vaadin can compile the theme on the fly when needed,
or the theme can be precompiled to speed up page loads.

To precompile the theme run "mvn vaadin:compile-theme". Note, though, that once
the theme has been precompiled, any theme changes will not be visible until the
next theme compilation or running the "mvn clean" target.

When developing the theme, running the application in the "run" mode (rather than
in "debug") in the IDE can speed up consecutive on-the-fly theme compilations
significantly.

Using Vaadin pre-releases
-------------------------

If Vaadin pre-releases are not enabled by default, use the Maven parameter
"-P vaadin-prerelease" or change the activation default value of the profile in pom.xml .


Finding null pointer problems
======================


This is just a brief overview of how to run Infer on this project. Please see the [Infer documentation](http://fbinfer.com) for more information.  To find null pointer problems in this program one needs to use a model of the used Vaadin components. Fortunately, the default model is ok for all components except for the ComboBox. The folder "models" contains a model of the class "AbstractSelect" which is a super class of the ComboBox. This model needs to be put where Infer can find it. Infer stores the source for Java-models in the folder "<infer install dir>/infer/models/java/src". In order for Infer to compile the models for use, the Vaadin jars needed for compilation need to be present on the classpath. This is achieved by adding the path to the jar-files in the makefile "<infer install dir>/infer/models/java/Makefile.in". To use the new models, rebuild the Java version of Infer. For more information see [adding models at the Infer website](http://fbinfer.com/docs/adding-models.html) and the [build and install instruction on github](https://github.com/facebook/infer).

To run the basic version of the tool run "infer -- mvn clean compile". This cleans the project before compiling the Java source files in order to ensure all files are recompiled. To run the tool on source files annotated by "@Nullable" run "infer -a eradicate -- mvn clean compile". NOTE! Infer needs version 3.5.1 of maven-compiler-plugin. By default Vaadin projects have version 3.0, i.e. this has to be fixed or Infer will fail to run properly.







