# TALLER-DE-MODULARIZACIÓN-CON-VIRTUALIZACIÓN-E-INTRODUCCIÓN-A-DOCKER-Y-A-AWS

Taller donde se trabaja el despliegue de instancias de código Java, en este caso 5 imágenes que están en contenedores docker; Round Robin es un balanceador con el puerto 35000 este distribuye solicitud a tres instancias LogService1 con puerto 35001, LogService2 con puerto 35002 y LogService3 con puerto 35003, estos LogService se comunican con mongoDB por medio de una imagen que se instancio,estos contenedores se suben a un repositorio de Docker el cual se desplega en una máquina virtual creada en AWS.

### Requisitos previos

* Tener maven instalado
* Tener git instalado
* Versión de Java 7 o Java 8
* Tener Docker instalado
* Tener un protocolo SSH instalado y habilitado


### Instalación

1. Para hacer uso de este proyecto debe clonarlo de este repositorio a su computadora desde cmd usando el siguiente comando:
   
   ```
   git clone https://github.com/anamariasalazar/TALLER-DE-DE-MODULARIZACI-N-CON-VIRTUALIZACI-N-E-INTRODUCCI-N-A-DOCKER-Y-A-AWS.git
   ```

## Ejecución

Para ejecutar el proyecto después de clonarlo se debe hacer uso de los siguientes comandos en el orden que se encuentran,(se pondrán algunas imágenes de ejemplo):
   
    
    cd TALLERR
    mvn clean install
    
![](/Imagenes/1.PNG)
   
    mvn package
![](/Imagenes/2.PNG)
    
    cd ..
    
    cd TALLERR2
    
    mvn clean install
![](/Imagenes/3.PNG)
    
    mvn package
    
    cd ..
    
    mvn clean install
    
    mvn package
   
  
    
 ### Despliegue localhost
 
 Desplegar las imágenes y contenedores,con el siguiente comando en la raíz del proyecto:
 ```
 docker-compose up -d
 ```
![](/Imagenes/5.PNG)
  
 Se revisa las imágenes que se crearon con el siguiente comando:
 
 ```
 docker images
 ```
![](/Imagenes/6.PNG)
 
 
 Se revisa los contenedores que se crearon con el siguiente comando:
 
 ```
 docker ps
 ```
 ![](/Imagenes/7.PNG)
 
 ### Despliegue en Docker Hub
 
Se creo un repositorio en Docker hub para subir el proyecto:

-Nombre de usuario: anamariasalazar

-Nombre del repositorio: tallerarepdockeryaws

-Link: https://hub.docker.com/repository/docker/anamariasalazar/tallerarepdockeryaws

![](/Imagenes/8.PNG)

 Se crean nuevos tags para cada imagen de la siguiente manera  "Nombre usuario de Docker"/"Nombre repositorio" y con tag el nombre de cada recurso:

```
docker tag taller-de-de-modularizaci-n-con-virtualizaci-n-e-introducci-n-a-docker-y-a-aws_roundrobin:latest anamariasalazar/tallerarepdockeryaws:roundrobin

docker tag taller-de-de-modularizaci-n-con-virtualizaci-n-e-introducci-n-a-docker-y-a-aws_logservice1:latest anamariasalazar/tallerarepdockeryaws:logservice1

docker tag taller-de-de-modularizaci-n-con-virtualizaci-n-e-introducci-n-a-docker-y-a-aws_logservice2:latest anamariasalazar/tallerarepdockeryaws:logservice2

docker tag taller-de-de-modularizaci-n-con-virtualizaci-n-e-introducci-n-a-docker-y-a-aws_logservice3:latest anamariasalazar/tallerarepdockeryaws:logservice3
```

![](/Imagenes/9.PNG)

```
docker tag mongo:latest anamariasalazar/tallerarepdockeryaws:mongodb
```

![](/Imagenes/10.PNG)


Se verifica que se hayan creado las imagenes con el siguiente comando:

```
docker images
```

Se empujaran las imágenes al repositorio de Docker hub:

```
docker push anamariasalazar/tallerarepdockeryaws:mongodb
docker push anamariasalazar/tallerarepdockeryaws:roundrobin
```
![](/Imagenes/11.PNG)

```
docker push anamariasalazar/tallerarepdockeryaws:logservice1
docker push anamariasalazar/tallerarepdockeryaws:logservice2
```
![](/Imagenes/12.PNG)

```
docker push anamariasalazar/tallerarepdockeryaws:logservice3
```
![](/Imagenes/13.PNG)

Se visualizan las imágenes que se subieron en el repositorio de Docker hub
![](/Imagenes/14.PNG)

### Creación máquina en AWS
Antes de desplegar en AWS debemos tener un entorno en el cual desplegarlo, por los cual crearemos una maquina virtual EC2 con ambiente linux en la consola de AWS; para hacer esto debemos dirigirnos a nuestra consola de AWS e iniciarla, nos daremos cuenta que estara iniciada al estar la luz verde enecendida al lado de donde dice AWS
![](/Imagenes/15.PNG)

Tras encenderse la luz damos click en AWS y nos dirigira a la siguiente pestaña en donde seleccionaremos "Lance una maquina virtuall con EC2"
![](/Imagenes/16.PNG)

Ahora nos encontraremos en otra ventana que nos solicitara escoger el sistema operativo con el que se va a trabajar, elegiremos "Amazon Linux 2 AMI (HVM), SSD Volume Type" de 64 bits(x86)
![](/Imagenes/17.PNG)

Tras esto nos enviara a escoger las caracteristicas que queremos que tenga el sistema operativo, seleccionaremos la que tiene la etiqueta verde que dice "Apto para capa gratuita" y oprimimos la opción del boton azul "revisar y lanzar"
![](/Imagenes/18.PNG)

Después nos muestra las caracteristicas de la maquina y oprimimos en "lanzar"
![](/Imagenes/19.PNG)

Y nos desplegara este menú, seleccionamos "Crear nuevo par de claves" de tipo "RSA" y le damos unn nombre, en este caso es "roundrobin" y las descargamos, tras la descarga se habilitara la opción "Lanzar instancias", a la cual le daremos clic
![](/Imagenes/20.PNG)

Ahora seleccionamos nuestra instancia y en el menpu superior de "Acciones" elegimos la opción conectar
![](/Imagenes/21.PNG)

Adentro elegimos la opción ssh y copiamos la ruta de conexión en mi caso es esta:

```
ssh -i "roundrobin.pem" ec2-user@ec2-52-23-175-25.compute-1.amazonaws.com
```
![](/Imagenes/24.PNG)

Después en nuestro computador creamos una nueva carpeta y dentro de esa carpeta vamos a colocar el archivo de claves que descargamos de la consola de amazon y desplegamos una consola en esta ubicación
![](/Imagenes/25.PNG)

Ahora pondremos el siguiente comando para actualizar el sistema a la versión mas reciente

```
sudo yum update -y
```

Después instalaremos docker en nuestro ambiente con el siguiente comando

```
sudo yum install docker
```

Y asignaremos al usuario docker permisos de administrador para que no tengamos que hacer uso del comando sudo cada vez que ejecutemos un comando docker

```
sudo usermod -a -G docker ec2-user
```

Después de esto saldremos y volveremos a entrar para que los cambios se refresquen

```
exit
```

```
ssh -i "roundrobin.pem" ec2-user@ec2-52-23-175-25.compute-1.amazonaws.com
```

Tras volver a ingresar inicializamos el servicio docker

```
sudo service docker start
```

Y al consultar las imagenes nos daremos cuenta que no existe ninguna, por lo cual las debemos desplegar en nuestro ambiente de la maquina EC2

```
docker images
```
![](/Imagenes/26.PNG)


### Creación puertos AWS

Ahora que tenemos instalado nuestra maquina podemos desplegar nuestras imagenes docker en el, pero para eso necesitamos crear reglas de entrada para que la maquina pueda leer los puertos que necesitamos; por lo cual iremos a nuestra instancia desde la consola de amazón y nos dirigiremos a la pestaña de "seguridad" y oprimiremos en el link de "grupos de seguridad"
![](/Imagenes/27.PNG)

Ahora en reglas de entrada seleccionaremos "edit inbound rules"
![](/Imagenes/28.PNG)

Lo cual nos mostrara esta pestaña en la cual crearemos nuestras reglas oprimiendo en "agregar regla"
![](/Imagenes/29.PNG)

Crearemos una regla por cada puerto que necesitamos usar es decir 35000 para roundrobin, 35001 para logservice1, 35003 para logservice2, 35003 para logservice3, 27017 para mongo, lo cual nos quedara así
![](/Imagenes/30.PNG)

### Despliegue en AWS

Ya podemos desplegar en AWS teniendo la garantia que nuestras imagenes vvan a poder hablar y escuchar en sus respectivos puertos, por lo cual ejecutaremos las imagenes que se encuentran en nuestro repositorio para que las guarde y ejecute, esto lo haremos con los siguientes comandos

```
docker run -d -p 35000:6000 --name roundrobin anamariasalazar/tallerarepdockeryaws:roundrobin

docker run -d -p 35001:6000 --name logservice1 anamariasalazar/tallerarepdockeryaws:logservice1
```
![](/Imagenes/31.PNG)

```
docker run -d -p 35002:6000 --name logservice2 anamariasalazar/tallerarepdockeryaws:logservice2 

docker run -d -p 35003:6000 --name logservice3 anamariasalazar/tallerarepdockeryaws:logservice3

docker run -d -p 27017:27017 --name mongodb anamariasalazar/tallerarepdockeryaws:mondodb
```
![](/Imagenes/32.PNG)

Tras ejecutar estos comandos se crean las imagenes que las podemos conultar con el comando

```
docker images
```
![](/Imagenes/33.PNG)

Y también se crean los contenedores con el comando
```
docker ps
```
![](/Imagenes/34.PNG)


Ahora teniendo nuestra imagenes podemos verificar que ya se encuentra desplegado consultando en este caso el link http://ec2-52-23-175-25.compute-1.amazonaws.com:35000/
![](/Imagenes/35.PNG)

## Pruebas
Ahora podemos hacer las pruebas ingresando dos mensajes

### Prueba 1
Primer mensaje
![](/Imagenes/35.PNG)

Resultado primer mensaje
![](/Imagenes/36.PNG)

### Prueba 2
Resultado segundo mensaje
![](/Imagenes/37.PNG)

## Construido con

* [Java](https://www.oracle.com/java/) : Tecnología que se usa para el desarrollo de aplicaciones que convierten a la Web en un elemento más interesante y útil.
* [IntelliJ](https://es.wikipedia.org/wiki/IntelliJ_IDEA): Es un entorno de desarrollo integrado (IDE) para el desarrollo de programas informáticos.
* [Git](https://es.wikipedia.org/wiki/Git): Herramienta que realiza una función del control de versiones de código de forma distribuida
* [Maven](https://es.wikipedia.org/wiki/Maven): Maven es una herramienta de software para la gestión y construcción de proyectos Java creada por Jason van Zyl, de Sonatype, en 2002. 
* [JavaScript](https://es.wikipedia.org/wiki/JavaScript): Es el lenguaje de programación encargado de dotar de mayor interactividad y dinamismo a las páginas web.
* [Docker](https://www.docker.com/): Es un proyecto de código abierto que automatiza el despliegue de aplicaciones dentro de contenedores de software, proporcionando una capa adicional de abstracción y automatización de virtualización de aplicaciones en múltiples sistemas operativos.
* [SSH](https://es.wikipedia.org/wiki/Secure_Shell): Es el nombre de un protocolo y del programa que lo implementa cuya principal función es el acceso remoto a un servidor por medio de un canal seguro en el que toda la información está cifrada.
* [AWS](https://aws.amazon.com/es/): s una colección de servicios de computación en la nube pública que en conjunto forman una plataforma de computación en la nube, ofrecidas a través de Internet por Amazon.com

## Autor

* [Ana Maria Salazar Bohorquez](https://github.com/anamariasalazar)

## Licencia

**©️** Ana Maria Salazar Bohorquez etudiante de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería Julio Garavito

Licencia bajo la [GNU General Public License](/LICENSE.txt)

