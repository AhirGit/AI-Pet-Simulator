# Makefile for AI-Pet-Simulator

JAVAC = javac
JAVA = java
JAR = lib/opennlp-tools-2.5.3.jar
SRC_DIR = src
OUT_DIR = out
RES_DIR = res
MAIN_CLASS = src.CatSimulator
MAIN_CLASS_PATH = src.CatSimulator
FAT_JAR = cat-sim-fat.jar
ICON = petSim.ico

SOURCES = $(wildcard $(SRC_DIR)/*.java)

all: compile run

# Compile Java source files
compile:
	@echo "🔨 Compiling Java files..."
	@mkdir -p $(OUT_DIR)
	$(JAVAC) -cp "$(JAR)" -d $(OUT_DIR) $(SOURCES)

# Run the program
run:
	@echo "🚀 Running the simulator..."
	$(JAVA) -cp "$(OUT_DIR):$(RES_DIR):$(JAR)" $(MAIN_CLASS)

# Create a fat JAR with all classes and dependencies
fat-jar: compile
	@echo "📦 Creating fat JAR..."
	@mkdir -p temp/images
	@cp -r out/* temp/
	@cp -r res/images/* temp/images/
	@cd temp && jar xf ../$(JAR)
	@cd temp && jar xf ../lib/slf4j-api-2.0.16.jar

	@echo "Main-Class: $(MAIN_CLASS_PATH)" > manifest.txt
	@cd temp && jar cfm ../$(FAT_JAR) ../manifest.txt *

	@rm -rf temp manifest.txt

# Package into .exe using jpackage
package: fat-jar
	@echo "📦 Packaging as native app..."
	@mkdir -p dist
	@cp $(FAT_JAR) dist/

	jpackage \
	--type app-image \
	--input dist \
	--name "CatSimulator" \
	--main-jar $(FAT_JAR) \
	--main-class $(MAIN_CLASS_PATH) \
	--icon $(ICON)

	@rm -rf dist

# Clean compiled files and artifacts
clean:
	@echo "🧹 Cleaning build..."
	rm -rf $(OUT_DIR)/* $(FAT_JAR) output installer *.exe