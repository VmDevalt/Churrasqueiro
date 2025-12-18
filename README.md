# Sistema O Churrasqueiro :hamburger::fire:

Boas vindas ao sistema da hamburgueria **O Churrasqueiro**, proojeto desenvolvido pelos estudantes do curso de Análise e Desenvolvimento de Sistemas *(ADS)*, no Instituto Federal de Educação, Ciência e Tecnologia de Pernambuco *(IFPE)* - Campus Paulista, na disciplina de Linguagem de Programação Orientada a Objetos, aplicando conceitos vistos em sala e, também, extrapolando esse conhecimento.

## Tecnologias utilizadas no projeto :computer:
O projeto foi desenvolvido em **Java JDK 11**, utilizando o **Maven** como gerenciador de dependências.
As dependências são:
- **H2 Database** como sistema de gerenciamento de banco de dados *(SGBD)*;
- **JakartaMail *(v2.0.1)* com *com.sun.mail (v2.0.1)* ** para envio de e-mails automáticos;
- **JFreeChart *(v1.5.4)*** e **JCommon *(v1.0.24)*** para leituras estatísticas;
- **Java-Dotenv *(v5.2.2)*** para leitura de arquivos *.env*;
- **Gson *(v2.11.0)*** para leitura de arquivos *.json*;
- **Apache PDFBox *(v2.0.32)*** para criação de arquivos PDF;
- **JCalendar *(v1.4)*** para navegar entre datas.

*Todas as dependências podem ser vizualizadas no arquivo `pom.xml`.*

## Como configurar o repositório? :grey_question:
Após realizar a clonagem do repositório com o comando `git clone https://github.com/VmDevalt/Churrasqueiro.git` no terminal do **Git Bash**, é necessário configurar o `.env`.

### Configurando o `.env` :gear:
O arquivo `.env` é utilizado para armazenar pares chave-valor de dados sensíveis *(ou seja, que não devem ser expostos)*. Tendo isso em mente, essas são as seguintes chaves que devem estar presentes e configuradas no seu repositório local:
- **EMAIL** - É o e-mail que você utilizará como remetente dos e-mails automáticos;
- **EMAIL_NAME** - É o e-nome que aparecerá na mensagem dos e-mails automáticos *(Essa variável está fora de uso nesse momento na aplicação)*;
- **EMAIL_PASSWORD** - É a senha de app que o serviço do e-mail disponibiliza para uso em aplicações como essa *(**ATENÇÃO:** A senha padrão de login dessa conta de e-mail não é válida, por isso utiliza-se uma senha de app para que a aplicação tenha "acesso" à conta)*;
- **EMAIL_HOST_ADDRESS** - É o endereço do protocolo SMTP do serviço de e-mail que você utilizará como remetente dos e-mails automáticos;
- **MERCADO_PAGO_ACCESS_TOKEN** - É onde você colocará o token para acesso da conta do manejador da API do Mercado Pago *(você ou algum colega que está clonando o repositório com você)*;
- **MERCADO_PAGO_PUBLIC_KEY** - É a chave pública que você quer que apareça na aplicação para realização de pagamentos, como uma chave PIX *(e sim, ela realmente funciona para pagamentos **reais**!)*.

Com o `.env` configurado, você já pode rodar a aplicação! *(segue para o próximo tópico)*.

## Como iniciar a aplicação? :hamburger:
Para começar a rodar a aplicação você deve ir na pasta main e inicializar o arquivo `Main.java`. Ele inicializará o banco de dados (H2) e a aplicação desktop, então você já estará utilizando o sistema do Churrasqueiro! :hamburger::fire:
