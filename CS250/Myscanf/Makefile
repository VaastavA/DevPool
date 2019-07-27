all: maintest

maintest: main.c myscanf.c scaninteger.s
	gcc main.c myscanf.c scaninteger.s -std=c99 -g -o maintest

clean:
	rm -f maintest

run:
	./maintest
