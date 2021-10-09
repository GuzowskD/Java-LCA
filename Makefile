lca:
	make build
	make run

build:
	javac src/LowestCommonAncestor.java src/ReadFile.java src/BinaryTree.java -d ./bin

run:
	cd ./bin & java LowestCommonAncestor