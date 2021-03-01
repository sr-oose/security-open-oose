# Beispielprojekt Spring Security + OAuth 2.0 / OpenID Connect

Drei Gradle-Projekte, die als Spring Applikationen miteinander kommunizieren

## Übersicht

| Inhalt            | Beschreibung                                           |
| ----------------- | ------------------------------------------------------ |
| **Gateway**       | Gateway mit Authentifizierung über KeyCloak            |
| **Resource**      | Einfacher Rest-Service mit admin und user Endpoint     |
| **DataServer**    | Einfach Rest-Service (Zufallszahlen) mit user Endpoint | 
| **KeyCloak**      | KeyCloak-Konfiguration                                 |

Die Aufrufe finden wie folgt statt REST Client -> Gateway -> Resource (-> DataServer). Auth-Tokens werden jeweils weitergeleitet.
