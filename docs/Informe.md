# Desert oil exploration drones

## Introduccion 
En este proyecto se buscó desarollar un sistema de movimiento y trackeo de drones a lo largo de una planicie a traves de comandos simples. Se indicaba el tamaño de la planicie, las posiciones iniciales de el o los drones, y una serie de comandos que indicaban el movimiento de los mismos. Estos debian moverse siguiendo esas instrucciones y reportar su posicion final.

## Requisitos Previos 
Para poder correr el programa se necesita tener instalado java 22 o superior y maven 4.0 o superior.

## Instrucciones de uso
Este programa se ejecuta a traves de la terminal. Para poder correrlo se debe clonar el repositorio y luego ejecutar el siguiente comando en la terminal:

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

## Desarrollo

Este proyecto cuenta con 5 clases principales. El controlador es el encargado de manejar la aplicacion en si. Este recibe el string de comandos y crea las diferentes entidades necesarias para poder procesarlos. Luego, se encarga de procesar los comandos, pasandole el movimiento a cada uno de los drones (luego de crearlos) y devolviendo como su posicion final como respuesta.

El controlador inicializa una Plateau y uno o mas drones, segun lo requiera el input. Luego, le pasa los comandos a cada uno de los drones, que se encargan de moverse segun las instrucciones.

Adicionalmente, el controlador tiene como atributos a una Pleateau y a un arrayList de drones. 

La clase Plateau es la planicie sobre la que se mueven los drones. Esta tiene un solo metodo, `isWithinBounds`, que recibe una posicion y verifica si esta dentro de los limites de la planicie.

Luego, el drone es la entidad que se mueve. Tiene como atributos una posicion(x, y) y una orientación. Tiene un metodo `move` que recibe un string con los comandos y se encarga de avanzar o rotar sobre su eje segun corresponda. Adicionalmente, luego de cada movimiento, notifica al ObserverIsOutOfBounds.

### Observer IsOutOfBounds

Debido a que es importante que el sistema registre que el dron se salio de la planicie pero intentando evitar una dependencia ciclica entre drone y planicie o entre dron y controlador, decidí implementar un observer que se encargue de notificar a al controlador cada vez que el dron realiza un movimiento. 
Este notifica, entonces, a todos los observers de su array de observers y estos, al recibirlo, hace verificaciones segun la informacion recibida (la posicion del dron). En este caso, el controlador verifica si la posicion esta dentro de los limites de la planicie y, de no ser asi, lanza una excepcion. Esto tiene una considerable escalabilidad, ya que diferentes entidades podrian observar al dron y hacer cosas diferentes con la informacion de su posicion, tales como enviar nuevos comandos o simplemente llevar un trackeo del historial de ubicación del mismo


### API
Finalmente, la clase DroneApiServer es la encargada de levantar el server y manejar las peticiones. Esta recibe un json con el input y lo procesa, devolviendo un json con el resultado. La ventaja de haber extraido la logica de procesamiento a un controlador y no haberla dejado en el main es que puedo tener diferentes interfaces de entrada (en este caso, una API) sin tener que modificar la logica de procesamiento. Se podrian lenvantar en una pagina web, una API o lo que se requiera.

## Requerimientos no tecnicos
Este proyecto fue desarollado utilizando solamente librerias JDK, exceptuando por maven y Junit, que se utilizaron para compilación y testing.
