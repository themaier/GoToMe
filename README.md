# GoToMe
Projekt: ASD

Bei dieser Anwendung handelt es sich um ein Benutzerverwaltungssystem, das die Registrierung, Anmeldung, Abmeldung, Verwaltung und Löschung von Benutzerkonten ermöglicht.


- UserManagementApplication starten um das Programm zu starten.
- Benötigt wird:
  - Java Version 17
  - Maven
  - MySQL (Einstellungen können in application.properties geändert werden)
- Das Programm läuft dann auf http://localhost:8080/ ((Einstellungen können in application.properties geändert werden))


Das System besteht aus mehreren Rest-Services:
- http://localhost:8080/saveUser
  - Post-Request
  - Erwartet sich im Body einen User bestehend aus firstname, lastname, username und password
  - Beispiel Body:
    - {
      "firstname":"testfirstname",
      "lastname":"testlastname",
      "username":"testusername",
      "password":"testpassword"
      }
- http://localhost:8080/users
  - Get-Request
  - liefert alle existierenden User
- http://localhost:8080/login
  - Get-Request
  - zum Einloggen ins System
  - Benötigt wird username und password als Parameter
  - Beispiel:
    - http://localhost:8080/login?username=testusername&password=testpassword456
- http://localhost:8080/changePassword
  - Get-Request
  - zum Ändern des Passworts
  - Benötigt das neue Passwort "password", das wiederholte neue Passwort "repeatedPassword" und das alte Passwort "oldPassword"
  - Beispiel:
    - http://localhost:8080/changePassword?password=testtest&repeatedPassword=testtest&oldPassword=testpassword456
- http://localhost:8080/deleteUser
  - Delete-Request
  - zum Löschen des Accounts
  - Benötigt wird das aktuelle Passwort
  - Beispiel:
    - http://localhost:8080/deleteUser?password=testpassword
- http://localhost:8080/logout
  - Get-Request
  - Zum Ausloggen aus dem System
