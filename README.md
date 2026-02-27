# AndroidTest
Teste para vaga de desenvolvedor Android

Deveria fazer um fork para o github pessoal e abrir um m.r com as changes para o github do AndroidTest

O desafio consite em criar 3 telas.
 - Tela Principal
 - Tela Meus Dados
 - Tela Meu Plano

Arquitetura, poderá se optar por: 
 - MVVM
 - MVI
 - MVVM-Clean

Linguage: Kotlin

Injeção de dependência: Koin

Renderizar imagens remotas: Coil

Android compile sdk 36 e min sdk 28

Testes unitários

Figma: https://www.figma.com/design/PQAFTgCZHlsmj57yaxWQJc/Sem-t%C3%ADtulo?node-id=0-1&t=HfF89ZbmO2FZ6WNW-1

Das telas: 
   
**Tela Principal**

- O botão nativo deverá ser interceptado e apresentar uma modal perguntando se ele deseja sair como abaixo:
  
  <img width="158" height="118" alt="Captura de Tela 2026-02-27 às 13 42 41" src="https://github.com/user-attachments/assets/1976c585-0ccf-45ec-96d1-75cdd1559735" />

Opção "Sim", fechar o app.
Opção "Não", o usuário continuará com o app aberto.

- Ter 2 opções de navegação para a "Plataforma Nova" e para a "Plataforma Antiga"

**Tela Meus Dados**

<img width="107" height="215" alt="Captura de Tela 2026-02-27 às 14 17 08" src="https://github.com/user-attachments/assets/ee2b5c9c-3c34-453a-af48-5ae70923b1e2" />

- De design poderá se usar XML com Fragments ou em Compose

- Criar uma classe de uiModel que represente toda a tela sendo: 
   - Título
   - Avatar
   - Dados do usuário [nome, cpf, idade]
   - Lista de ações [Meu Plano, Baixar Contrato, Privacidade e Sair]

- A tela deverá ser renderizada via observer [LiveData, StateFlow ou MutableStateFlow]
  
 - ViewModels:
 Deverá se criar 2 viewModels sendo uma para a plataforma nova[NP] e uma para a antiga[RW]:

 A viewModel da plataforma nova deverá se chamar "NPMyDataViewModel" e deverá realizar as seguintes ações:
 - Disparar um loading de uns "2 segundos"
 - Ter um mapper que coverta da classe de modelo para uiModel
 - Disparar um observer com o objeto uiModel para atualizar a view
 - Consumir as classes seja usecase, repository que usem como base o assets "assets/my_data/my_data_new.json" que simulará um retorno de api
 - Realizar as ações necessárias

 A viewModel da plataforma antiga deverá se chamar "RWMyDataViewModel" e deverá realizar as seguintes ações:
 - Disparar um loading de uns "2 segundos"
 - Como não é um bff deverá se criar a classe de uiModel totalmente via código sem base em um bff
 - Disparar um observer com o objeto uiModel para atualizar a view
 - Consumir as classes seja usecase, repository que usem como base o assets "assets/my_data/my_data_old.json" que simulará um retorno de api
 - Realizar as ações necessárias

- Das ações:   
  - Meu Plano: Navegar para a tela "Meu Plano"
  - Baixar Contrato: Deverá se utilizar da função nativa "ShareSheet" para compartilhar o arquivo pdf "terms.pdf" que está nos assets.
  - Privacidade: Abrir o navegador com a url que é traga da api ou local(Caso local utilizar dos properties do koin para armazenar a url)
  - Sair: Exibir a modal que a estrutura vem via bff ou montar local

Do desafio principal: 
- Ter somente 1 tela 1 instância de viewModel mas podendo ser a viewModel "NPMyDataViewModel" ou a "RWMyDataViewModel"
- Essa injeção deverá ser feita via qualifier com o Koin.


**Tela Meu Plano**

<img width="176" height="285" alt="Captura de Tela 2026-02-27 às 14 18 30" src="https://github.com/user-attachments/assets/f1b45ee8-65b9-4e83-9b6b-ba0ac0eee492" />


- De design poderá se usar XML com Fragments ou em Compose

- Criar uma classe de uiModel que represente toda a tela sendo:
   - Dados do plano [status, número de telefone, valor do plano]
   - Plano e Bonus
   - Apps inclusos

- A tela deverá ser renderizada via observer [LiveData, StateFlow ou MutableStateFlow]
  
 - ViewModels:
 Deverá se criar 2 viewModels sendo uma para a plataforma nova[NP] e uma para a antiga[RW]:

 A viewModel da plataforma nova deverá se chamar "NPMyPlanViewModel" e deverá realizar as seguintes ações:
 - Disparar um loading de uns "2 segundos"
 - Ter um mapper que coverta da classe de modelo para uiModel
 - Disparar um observer com o objeto uiModel para atualizar a view
 - Consumir as classes seja usecase, repository que usem como base o assets "assets/my_plan/my_plan_new.json" que simulará um retorno de api
 - Realizar as ações necessárias

 A viewModel da plataforma antiga deverá se chamar "RWMyPlanViewModel" e deverá realizar as seguintes ações:
 - Disparar um loading de uns "2 segundos"
 - Como não é um bff deverá se criar a classe de uiModel totalmente via código sem base em um bff
 - Disparar um observer com o objeto uiModel para atualizar a view
 - Consumir as classes seja usecase, repository que usem como base o assets "assets/my_plan/my_plan_old.json" que simulará um retorno de api
 - Realizar as ações necessárias

- Das ações:   
  - Voltar: Voltar pra tela Meus Dados

Do desafio principal: 
- Ter somente 1 tela 1 instância de viewModel mas podendo ser a viewModel "NPMyPlanViewModel" ou a "RWMyPlanViewModel"
- Essa injeção deverá ser feita via qualifier com o Koin.


 

