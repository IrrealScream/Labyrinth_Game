all:
	make comp_all
	make launch_all

comp_all:
	make comp_LabyrinthMain
	make comp_LabyrinthChessType
	make comp_ThinType
	make comp_Hero
	make comp_Item
	make comp_Game

launch_all:
	make launch_LabyrinthMain
	make launch_LabyrinthChessType
	make launch_ThinType
	make launch_Hero
	make launch_Item
	make launch_Game


# commandes principales

jeu.jar:
	make cls;
	jar cvfe jeu.jar jeu.Game -C classes jeu

go:
	java -jar jeu.jar

cls:
	make comp_Game

docs:
	javadoc -sourcepath src -d docs -subpackages jeu

clean:
	rm -f -r classes/*
	rm -f -r docs/*
	rm -f jeu.jar




# Commandes de netoyage :

# pour windows
winclean:
	del /f /s /q "classes/"

cleanAll:
	rm -f -r classes/*

cleanLabyrinths:
	rm -f -r classes/labyrinth

cleanEntity:
	rm -f -r classes/entity

# Commandes qui compile et lance le programme java

LabyrinthMain:
	make comp_LabyrinthMain
	make launch_LabyrinthMain

LabyrinthChessType:
	make comp_LabyrinthChessType
	make launch_LabyrinthChessType $(ARGS)

ThinType:
	make comp_ThinType
	make launch_ThinType

Hero:
	make comp_Hero
	make launch_Hero

Item:
	make comp_Item
	make launch_Item

Game:
	make comp_Game
	make launch_Game

RandomUtil:
	make comp_RandomUtil
	make launch_RandomUtil

Dictionary:
	make comp_Dictionary
	make launch_Dictionary

GameRendering:
	make comp_GameRendering
	make launch_GameRendering

Bonus:
	make comp_Bonus
	make launch_Bonus

# Commandes de compilation javac :



comp_LabyrinthMain: src/jeu/labyrinth/LabyrinthMain.java
	javac -sourcepath src -d classes $^

comp_LabyrinthChessType: src/jeu/labyrinth/LabyrinthChessType.java
	javac -sourcepath src -d classes $^

comp_ThinType: src/jeu/labyrinth/ThinType.java
	javac -sourcepath src -d classes $^

comp_Hero: src/jeu/entity/Hero.java
	javac -sourcepath src -d classes $^

comp_Item: src/jeu/entity/items/Item.java
	javac -sourcepath src -d classes $^

comp_Game: src/jeu/Game.java
	javac -sourcepath src -d classes $^

comp_Pnj: src/jeu/entity/pnjs/*.java
	javac -sourcepath src -d classes $^

comp_RandomUtil: src/jeu/util/RandomUtil.java
	javac -sourcepath src -d classes $^

comp_Dictionary: src/jeu/entity/Dictionary.java
	javac -sourcepath src -d classes $^

comp_GameRendering: src/jeu/GameRendering.java
	javac -sourcepath src -d classes $^

comp_Question: src/jeu/entity/pnjs/questions/Question.java 
	javac -sourcepath src -d classes $^

comp_Action : src/jeu/util/Action.java
	javac -sourcepath src -d classes $^

comp_Bonus: src/jeu/bonus/*.java
	javac -sourcepath src -d classes $^

comp_test_pnjs: test/entity/pnjs/*.java
	javac -classpath test4poo.jar $^

comp_test_questions: test/entity/pnjs/questions/*.java
	javac -classpath test4poo.jar $^

# Commandes d'execution de java

launch_LabyrinthMain:
	java -classpath classes labyrinth.LabyrinthMain $(ARGS)

launch_LabyrinthChessType:
	java -classpath classes labyrinth.LabyrinthChessType $(ARGS)

launch_ThinType:
	java -classpath classes labyrinth.ThinType $(ARGS)

launch_Hero:
	java -classpath classes entity.pnjs.Hero $(ARGS)

launch_Item:
	java -classpath classes entity.items.Item $(ARGS)

launch_Game:
	java -classpath classes Game $(ARGS)

launch_RandomUtil:
	java -classpath classes util.RandomUtil $(ARGS)

launch_Dictionary:
	java -classpath classes entity.dictionary.Dictionary $(ARGS)

launch_GameRendering:
	java -classpath classes GameRendering $(ARGS)

launch_test_Asker:
	java -jar test4poo.jar entity.pnjs.AskerTest

launch_test_Question:
	java -jar test4poo.jar entity.pnjs.questions.QuestionTest

launch_test_Boss:
	java -jar test4poo.jar entity.pnjs.BossTest

launch_test_PNJ:
	java -jar test4poo.jar entity.pnjs.PNJTest
	
launch_Bonus: 
	java -classpath classes jeu.bonus.HudMain
