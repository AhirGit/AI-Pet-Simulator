# AI Pet Simulator

AI Pet Simulator is an interactive pet simulation application built in Java. It uses natural language processing with Apache OpenNLP to detect user intents and Java Swing for graphical user interface and animations. The simulator allows the user to interact with the virtual pet by typing natural language commands such as "I'm hungry," "Let's play," or "Go to sleep."

## Features

- Intent detection using Apache OpenNLP
- Real-time response to user input
- Java Swing-based animated pet behavior
- Frame-based sprite animation engine
- Modular and extensible design for adding new actions or behaviors

## Technologies

- Java 17 or higher
- Apache OpenNLP
- Java Swing (AWT)

## Project Structure
- `src/` — Java source code  
- `models/` — OpenNLP trained models  
- `assets/` — Pet sprite images organized by behavior  
- `bin/` — Compiled `.class` files generated after build  

## Prerequisites

- Java Development Kit (JDK) version 17 or above  
- GNU Make  
- Apache OpenNLP models (`en-token.bin`, `en-pos-maxent.bin`, `en-doccat.bin`) downloaded and placed into `models/` directory  

## Build and Run Instructions

To compile and run the project, use the provided `Makefile`.

**Build the project:**
```bash
make compile
```
**Run the project**
```bash
make run
```
**Clean compiled files:**
```bash
make clean
```
How It Works
	1.	The user enters a command in natural language.
	2.	The input text is tokenized and categorized using OpenNLP models.
	3.	Based on detected intent, the pet performs an associated action (eating, sleeping, playing) with animation.
	4.	The animation panel updates the graphics in real-time.
Notes
•	All OpenNLP .bin model files must be present in the models/ directory before running.
•	Asset folders must contain sprite frames corresponding to each behavior.
•	Designed for extensibility to support additional behaviors or animations.