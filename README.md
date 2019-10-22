# Duplicate-Finder
Given a CSV file, this application finds all possible duplicates in it.

### Example
- 4,Kale,Gipp,Klein Group,kgipp3@360.cn,4985 Menomonie Drive,,94975,Petaluma,California,CA,707-840-2551
- 4,Kale,Gipp,The Klein Group,kgippp3@360.cn,4985 Menomonie Drive,,94975,Petaluma,California,CA,707-840-2551

Notice the different but similiar email IDs.

## Setup
### How to build
Building this application is straightforward and easy since all the dependencies are located in Maven. 

If you are using an IDE like IntelliJ:
- Clone the project
- Import the project in IntelliJ
- The IDE should prompt you to import changes.

If you want to build using Terminal, you should use an online guide. [This](https://www.vogella.com/tutorials/ApacheMaven/article.html) is one of the resources you can follow.

### How to run
- Run `DuplicateFinder.java` in `src/main/java/dev/a3y3/`.
- Open up a web browser and navigate to `http://localhost:8080`.
- To specify the alternate dataset, use `http://localhost:8080/?fileName=advanced.csv`
