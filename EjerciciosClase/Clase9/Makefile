JFLAGS = -g

JC = javac


.SUFFIXES: .java .class



.java.class: 
	$(JC) $(JFLAGS) $*.java

CLASSES  = \
	Coordenada.java \
	PoligonoIrreg.java \
	PruebaPoligonoIrreg.java 

default: classes



classes: $(CLASSES:.java=.class)



clean:
	$(RM) *.class


run:
	java PruebaPoligonoIrreg