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
   
  
    
 ### Despliege localhost
 
 Desplegar las imágenes y contenedores,con el siguiente comando en la raíz del proyecto:
 ```
 docker-compose up -d
 ```
![](/Imagenes/5.PNG)
  
 Se revisa las imágenes  que crearon con el siguiente comando:
 
 ```
 docker images
 ```
![](/Imagenes/6.PNG)
 
 
 
 
    
## Construido con

* [Java] : Tecnología que se usa para el desarrollo de aplicaciones que convierten a la Web en un elemento más interesante y útil.
* [IntelliJ] : Es un entorno de desarrollo integrado (IDE) para el desarrollo de programas informáticos.
* [Git] : Herramienta que realiza una función del control de versiones de código de forma distribuida
* [Maven] : Maven es una herramienta de software para la gestión y construcción de proyectos Java creada por Jason van Zyl, de Sonatype, en 2002. 
* [JavaScrip] : Es el lenguaje de programación encargado de dotar de mayor interactividad y dinamismo a las páginas web.


## Autor

* [Ana Maria Salazar Bohorquez](https://github.com/anamariasalazar)

## Licencia

**©️** Ana Maria Salazar Bohorquez etudiante de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería Julio Garavito

Licencia bajo la [GNU General Public License](/LICENSE.txt)

