# Backend de Odissey - README (Español)

Este es el README del backend de Odissey, diseñado con una arquitectura federada de GraphQL.

## Stack Tecnológico

*   **Lenguaje:** Java
*   **Framework:** Spring Boot
*   **GraphQL:** Apollo Federation, DGS (Netflix DGS Framework)
*   **API Gateway:** Apollo Gateway
*   **Base de datos:** H2 (en memoria, para el registro de esquemas)
*   **Herramientas de construcción:** Maven

## Diseño de la Arquitectura

La arquitectura del backend se basa en los siguientes principios:

1.  **Federación de GraphQL:** Se utiliza Apollo Federation para construir un grafo de GraphQL a partir de múltiples servicios independientes. Cada servicio es responsable de una parte del esquema general.

2.  **API Gateway:** El API Gateway (implementado con Apollo Gateway) actúa como punto de entrada centralizado para todas las consultas GraphQL. Parsea las consultas, las descompone en subconsultas y las enruta a los servicios backend apropiados.

3.  **Servicios Backend (DGS):** Cada servicio backend es una aplicación Spring Boot que implementa un Domain Graph Service (DGS). El DGS implementa una porción del esquema GraphQL general. Los DGS se construyen utilizando el framework DGS de Netflix, que simplifica la creación de resolvers GraphQL en Spring Boot.

4.  **Registro de Esquemas:** Los DGS registran sus fragmentos de esquema en un registro compartido. El API Gateway utiliza este registro para combinar los esquemas y enrutar las consultas a los servicios correctos.

## Estructura del Proyecto

El proyecto se organiza en los siguientes subdirectorios:

*   `api-gateway`: Contiene la implementación del Apollo Gateway.
*   `user-auth-service`: Contiene el servicio de autenticación de usuarios, incluyendo el DGS para la autenticación.
*   `schema-registry`: Contiene el servicio de registro de esquemas, que almacena los fragmentos de esquema de los diferentes servicios.

## Próximos Pasos

*   Implementar el servicio de autenticación de usuarios.
*   Implementar el registro de esquemas.
*   Configurar el API Gateway para federar los esquemas y enrutar las consultas.
*   Implementar pruebas unitarias e integración.
*   Configurar la implementación.

## Consideraciones Adicionales

*   **Observabilidad:** Se implementará observabilidad utilizando las capacidades de Spring.
*   **Seguridad:** Se implementará la seguridad utilizando los mecanismos de Spring.
*   **Escalabilidad:** La arquitectura federada permite la escalabilidad independiente de los servicios.

## Diagrama de Arquitectura

Para una representación visual de la arquitectura del sistema, consulta el archivo [architecture-diagram.mmd](./architecture-diagram.mmd). Este diagrama muestra la interacción entre el cliente, el API Gateway, los servicios backend y el registro de esquemas, junto con las bases de datos utilizadas.
