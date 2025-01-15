# Variáveis
MVN = mvn
JAVA = java
JAVAC = javac

# Comandos
.PHONY: all clean compile test run report dependencies package install checkstyle help

all: compile test

clean:
	$(MVN) clean

compile:
	$(MVN) compile

test:
	$(MVN) test

run:
	$(MVN) exec:java -Dexec.mainClass="com.example.MainClass"

# Comando para gerar relatório de testes
report:
	$(MVN) surefire-report:report

# Comando para verificar dependências
dependencies:
	$(MVN) dependency:resolve

# Comando para empacotar o projeto
package:
	$(MVN) package

# Comando para instalar as dependências
install:
	$(MVN) install

# Comando para verificar o estilo do código
checkstyle:
	$(MVN) checkstyle:check

# Comando de ajuda
help:
	@echo "Comandos disponíveis:"
	@echo "  all           - Compila e executa os testes"
	@echo "  clean         - Limpa os arquivos gerados pelo Maven"
	@echo "  compile       - Compila o projeto"
	@echo "  test          - Executa os testes"
	@echo "  run           - Executa a aplicação principal"
	@echo "  report        - Gera relatório de testes"
	@echo "  dependencies  - Resolve as dependências do projeto"
	@echo "  package       - Empacota o projeto"
	@echo "  install       - Instala as dependências do projeto"
	@echo "  checkstyle    - Verifica o estilo do código"
	@echo "  help          - Mostra esta mensagem de ajuda"
