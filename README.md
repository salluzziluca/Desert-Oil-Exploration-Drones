# Desert oil exploration drones

## Introduccion 
En este proyecto se buscó desarollar un sistema de movimiento y trackeo de drones a lo largo de una planicie a traves de comandos simples. Se indicaba el tamaño de la planicie, las posiciones iniciales de el o los drones, y una serie de comandos que indicaban el movimiento de los mismos. Estos debian moverse siguiendo esas instrucciones y reportar su posicion final.

## Requisitos Previos 
Para poder correr el programa se necesita tener instalado java 22 o superior y maven 4.0 o superior.

## Instrucciones de uso
Este programa se ejecuta a traves de la terminal. Para poder correrlo se debe clonar el repositorio y luego ejecutar los siguientes comandos en la terminal:

```bash
mvn clean package
/home/kvothe/.jdks/openjdk-22.0.1/bin/java -classpath target/classes org.example.DroneApiServer
```
esto levantara un server en la direccion 8083

Luego, si hicieramos un POST a la direccion http://localhost:8083/drones/process-command enviando como body un json del estilo
```json
{
    "input": "5 5\n1 2 N\nLMLLMM\n3 3 E\nMMRMRRM"
}
```
obtendriamos como respuesta un json con la posicion final de los drones
```json
{
    "result": "2 2 E 5 3 N"
}
```

Comando curl: 
```bash
curl --location 'http://localhost:8083/drones/process-command' \
--header 'Content-Type: application/json' \
--data '{
    "input": "5 5\n1 2 N\nLMLLMM\n3 3 E\nMMRMRRM"
}
'
```

Tambien se pueden correr los tests (ubicados en src/test/java) con el siguiente comando:
```bash
mvn test
```


La documentacion sobre el desarollo del proyecto y las decisiones de diseño tomadas se encuentra en el archivo [Informe.md](docs/Informe.md)
