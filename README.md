1.	Listar
a.	GET (http://localhost:8080/ctarefa/tarefas)
b.	Descrição: Retorna uma lista de todas as tarefas ordenadas por ID (cresc.)
c.	Parâmetros aceitos: Nenhum (consulta toda a lista)
d.	Resposta: 200 - Lista de Tarefas cadastradas
2.	Consultar
a.	GET (http://localhost:8080/ctarefa/tarefas/{id})
b.	Descrição: Retorna uma tarefa específica
c.	Parâmetros aceitos: ID da tarefa
d.	200 OK – Objeto encontrado e apresentada / 404 Not Found (“Tarefa não encontrada”)
3.	Inserir
a.	POST (http://localhost:8080/ctarefa/tarefas)
b.	Descrição: Inclui uma tarefa à lista
c.	Parâmetros aceitos: JSON da tarefa a ser incluída
d.	200 OK – Tarefa nova criada (com mensagem de “Tarefa criada com sucesso”)  / 500 Internal Server Error (“Já existe tarefa com esse nome”)
4.	Alterar
a.	PUT (http://localhost:8080/ctarefa/tarefas/{id})
b.	Descrição: Altera tarefa indicada
c.	Parâmetros aceitos: ID da tarefa e JSON com novos dados da tarefa
d.	200 OK – Tarefa encontrada e alterada (com mensagem de “Tarefa atualizada com sucesso”) / 404 Not Found (“Tarefa não encontrada”)
5.	Excluir
a.	DELETE (http://localhost:8080/ctarefa/tarefas/{id})
b.	Desrição: Exclui tarefa indicada
c.	Parâmetros aceitos: ID da tarefa a ser excluída
d.	200 OK – Tarefa encontrada e excluída / 404 Not Found (“Tarefa não encontrada”)
