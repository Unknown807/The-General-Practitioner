# Assessment A2 - The General Practitioner #

## 🙂 Members ##
* <a href='mailto:mg699@kent.ac.uk'>Milovan Gveric</a>
* <a href='mailto:ac2042@kent.ac.uk'>Andrei Constantin</a>
* <a href='mailto:ff235@kent.ac.uk'>Filip Fois</a>
* <a href='mailto:ww221@kent.ac.uk'>Wenbo Wu</a>

## 📦 Prerequisites ##
* IntelliJ (or another Java IDE)  
* MySQL (version 14.14, Distrib 5.7.36)  
* Java SDK (version 16)
* Maven

## ⚙️ Installation ##
* Unzip the project
* Run/import the `/TheGP_Database_9_Mar_Raw.sql` script with MySQL to create the local database
* If there is an error with the imports, it is most likely due to missing Maven. Follow the steps in this link to fix the issue if encountered: <a href='https://stackoverflow.com/questions/8620127/maven-in-eclipse-step-by-step-installation'>StackOverflow answer</a>

If running `/TheGP_Database_9_Mar_Raw.sql` gives a bug in MySQL, then try running `/TheGP_Database_9_Mar.sql`

## 🛠️ Set up ##

* Open the project with IntelliJ (or the IDE of your choice)  
* Inside the project, open the `/src/main/java/com/group15A/DataAccess/DataAccess.java` file  
* Edit the `DB_USER` and `DB_PASSWORD` values to your local database username and password respectively

## ▶️ Running the program ##
In the IDE of your choice, run the `/src/main/java/com/group15A/Main.java` class

## 📔 Existing data
* The database contains some dummy data that you can test the application with.  
* Here is a list of patient emails you can use to login (the password is **Password1*** for all of the following patients):
```
- dneal@email.com
- lhines@email.com
- lschaefer@email.com
- jelly@email.com
- hdrake@email.com
- lrice@email.com
- bdouglas@email.com
- adennis@email.com
- acarter@email.com
- jedwards@email.com
```
