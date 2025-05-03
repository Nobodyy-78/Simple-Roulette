# Simple Roulette

Roulette Simple è un'applicazione Java che simula una roulette grafica. Il progetto segue l'architettura Model-View-Controller (MVC) ed è sviluppato con Eclipse.

## Struttura del progetto

- **src/** – Codice sorgente organizzato nei package:
  - `app.main` – Classe `Main` per l'avvio dell'applicazione
  - `app.control` – Logica di controllo
  - `app.model` – Modello dei dati (incluso un LED personalizzato)
  - `app.view` – Interfaccia grafica utente (GUI)
  - `hlp/` – File di aiuto
  - `img/` – Icone e immagini usate nella GUI
- **bin/** – Classi compilate e risorse
- **.settings/, .classpath, .project** – Configurazione Eclipse

## Requisiti

- Java JDK 17 o superiore se si vuole aprire il **.jar**
- IDE Eclipse (opzionale ma consigliato)

## Come eseguire

1. Importa il progetto in Eclipse come "Java Project with Existing Sources"
2. Compila il progetto
3. Esegui la classe `app.main.Main`

## Autori

- Biondo Alessandro

## Licenza

Questo progetto è rilasciato sotto la licenza [MIT](./LICENSE)
