# Duplicate-Finder
Given a CSV file, this application finds all _possible duplicate_ records in it. Duplicates are are not exact duplicates. "Similiar" records that differ each other by only small letters are also flagged as duplicates.

### Example
Amongst all records in `normal.csv`, two records look like this:

|id|first_name|last_name|company|email|address1|address2|zip|city|state_long|state|phone
|---|---|---|---|---|---|---|---|---|---|---|---|
|4|Kale|Gipp|**Klein Group**|**kgipp3@360.cn**|4985 Menomonie Drive| |94975|Petaluma|California|CA|707-840-2551
|4|Kale|Gipp|**The Klein Group**|**kgippp3@370.cn**|4985 Menomonie Drive| |94975|Petaluma|California|CA|707-840-2551

They're not exact duplicates (**company name** and **email IDs** differ, say because of a typo), but are correctly found and flagged by the application.

## Setup
### How to build
Building this application is straightforward and easy since all the dependencies are located in Maven. 

##### If you are using an IDE like IntelliJ:
- Clone the project
- Import the project in IntelliJ
- The IDE should prompt you to import changes.

##### If you want to build using Terminal:
- Install maven (on debian systems, use `sudo apt install maven`)
- In the project root, run `mvn package`.

### How to run
##### On an IDE:
- Run `DuplicateFinder.java` in `src/main/java/dev/a3y3/`.
- Open up a web browser and navigate to `http://localhost:8080`
- To specify the alternate dataset, use `http://localhost:8080/?fileName=advanced.csv`.

##### On Terminal
- After running `mvn package` Maven will create a jar in `target/main-app-1.0.jar`.
- Run using `java -jar target/main-app-1.0.jar`.
- Open up a web browser and navigate to `http://localhost:8080`
- To specify the alternate dataset, use `http://localhost:8080/?fileName=advanced.csv`.
