# Prism API

![License](https://img.shields.io/badge/license-GPLv3-blue.svg)

## Visão Geral

A **Prism API** é uma biblioteca modular para Java que fornece utilitários para manipulação de console, formatação de strings e entrada/saída segura.

Projetada para ser flexível, segura e extensível, ela utiliza subprojetos (módulos independentes) que permitem ao desenvolvedor importar apenas o que realmente precisa.
Ela foi projetada para simplificar operações comuns de I/O no console, oferecendo métodos seguros, claros e flexíveis, adequados para iniciantes e profissionais.

---

## Funcionalidades Principais

- **Formatação de Strings**  
  Suporte avançado para templates com placeholders genéricos (`{}`) e tokens (`%s`, `%i`).

- **Leitura de Entrada**  
  Wrapper seguro para `Scanner`, com tratamento de erros, suporte a locais (locales) e métodos para leitura de vários tipos primitivos.

- **Conversão de Tipos**  
  Métodos para converter `String` para tipos primitivos (`int`, `long`, `float`, `double`), com suporte a valores default e `Optional`.

- **Saída Formatada**  
  Métodos para impressão no console que suportam segurança contra `null`, debugging, impressão formatada e funcional via lambdas.

---

## Dependências

- Java 8 ou superior

---

## Integração
[![Maven Central](https://img.shields.io/maven-central/v/io.github.looming-echo/prism.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.looming-echo/prism)
### Maven


Adicione ao seu `pom.xml`:

```xml
<dependency>
    <groupId>io.github.looming-echo</groupId>
    <artifactId>prism</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

Adicione no seu `build.gradle`:

```gradle
dependencies {
    implementation("io.github.looming-echo:prism:1.0.0")
}
```
___

## Como Usar
### Formatação de Strings

```java
import com.github.rickmvi.formatter.Formatted;
import java.util.HashMap;
import java.util.Map;

// Placeholders genéricos
String formatted = StringFormatter.format("Hello {}, you are {} years old", "John", 30);

// Placeholders e tokens especiais (%n, %t, %r, %d)
Out.format("Test %rHello%d{}, you're {} years old%n", "John", 30);
// Console output: Hello John, you're 30 years old

// Tokens de formatação com índice
String tokenFormatted = StringFormatter.format("Name: %S{0}, Age: %in{1}", "john", 30);
```

## Padrões de Uso Recomendados

1. **Inicialização**: Sempre chame `Scan.init()` antes de usar métodos de leitura

2. **Tratamento de erros**: Use os métodos `nextSafe()` e conversões com fallback para evitar exceções

3. **Formatação**: Prefira placeholders nomeados para templates complexos

4. **Localização**: Configure a localização com `Scan.locale()` quando necessário

## Contribuição

Contribuições são muito bem-vindas!
Por favor:

  1. Abra uma issue para reportar bugs ou sugerir melhorias

  2. Fork o repositório

  3. Crie uma branch para sua feature (`git checkout -b minha-feature`)

  4. Faça commit das suas alterações (`git commit -m 'Minha feature'`)

  5. Envie para sua branch (`git push origin minha-feature`)

  5. Abra um Pull Request

## Licença

Este projeto é licenciado sob os termos da **GNU Lesser General Public License v3.0**.  
Você pode usá-lo em projetos comerciais e fechados, desde que preserve os termos da licença.

🔗 [Leia a licença completa aqui](https://www.gnu.org/licenses/lgpl-3.0.html)


**Desenvolvido por Rick M. Viana**

Contato: rickmviana.dev@outlook.com
