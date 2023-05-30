# MessageGame
In this project, 2 players communicate each other over message channel. 

To create the jar file, build the maven project to have a executuble jar file "SocketGame-1.0-SNAPSHOT.jar".
```
mvn clean
mvn install
```
In order to run the jar file with server and client classes, run the following command. At the end, you can see their
messages in the terminal respectively. The channel are closed after 10 mutual messages are achieved.
```
java -cp SocketGame-1.0-SNAPSHOT.jar Main server
java -cp SocketGame-1.0-SNAPSHOT.jar Main client
```

Alternatively, you can directly run the script to run everything.
```
bash run_server.sh
bash run_client.sh
```


