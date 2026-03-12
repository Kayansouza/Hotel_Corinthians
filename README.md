# 🏨 Hotel Corinthians - Sistema de Gestão de Hotel

Sistema desenvolvido em **Kotlin** com o objetivo de simular a gestão de um hotel, incluindo controle de quartos, cadastro de clientes e funcionários, além de reservas para eventos especiais.

O projeto foi criado como prática de **lógica de programação, organização de código e desenvolvimento de sistemas**.

---

# ⚽ Contexto do Projeto

O sistema simula o funcionamento de um hotel temático do Corinthians, onde diferentes eventos podem ocorrer, como:

- 🎉 Aniversário do Corinthians
- 📅 Eventos especiais em datas específicas
- 🏨 Reservas feitas por clientes VIP e clientes comuns

O hotel possui **50 quartos**, que podem ser reservados para eventos ou clientes.

---

# 🚀 Funcionalidades

✔ Cadastro de clientes  
✔ Cadastro de funcionários  
✔ Controle de 50 quartos do hotel  
✔ Verificação de disponibilidade de quartos  
✔ Reserva de quartos por evento  
✔ Sistema de validação para evitar reservas duplicadas  

---

# 🧠 Lógica do Sistema

O sistema funciona utilizando **listas dinâmicas em Kotlin**, que armazenam:

- Clientes cadastrados
- Funcionários
- Reservas
- Status dos quartos (Livre / Ocupado)

Exemplo da lógica de reserva:


```kotlin
if (numQuarto != null && numQuarto in 1..50) {
    val index = numQuarto - 1
    if (quartos[index] == "Livre") {
        quartos[index] = "Ocupado - $nomeEventos"
        println("Quarto reservado com sucesso")
    }


src
 ├── model
 │    ├── Cliente.kt
 │    ├── Funcionario.kt
 │
 ├── services
 │    ├── ClienteService.kt
 │    ├── FuncionarioService.kt
 │
 ├── reservas
 │    └── SistemaReserva.kt
 │
 └── Main.kt
}

🛠 Tecnologias Utilizadas

Kotlin

Programação Orientada a Objetos

Estrutura de dados (List / MutableList)

IntelliJ IDEA

Git & GitHub

📚 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de:

Praticar lógica de programação

Aprender organização de sistemas

Trabalhar com estrutura de dados

Utilizar Git e GitHub no desenvolvimento

👨‍💻 Autor

Richard Kayan de Souza

📎 GitHub
https://github.com/Kayansouza

📎 LinkedIn
https://br.linkedin.com/in/richard-kayan-de-souza-91a532204

🔮 Melhorias Futuras

Sistema de login para funcionários

Banco de dados para armazenar reservas

Interface gráfica

API para integração com aplicativos

Sistema de reservas por data

