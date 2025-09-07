# Proyecto Notas
Este sistema está construido con una arquitectura hexagonal, contiene un unico recurso el cual lista o crea las notas del usuario,  
para tener detalles sobre la arquitectura y flujo de ejecución de una solicitud a la Api visualizar la siguiente imagen:


![nofound](docs/arquitectura_flujo.png)

**Consideraciones**:
1. Para iniciar el sistema, ejecutar la clase principal de la capa infraestructura.
2. Quizás es necesario realizar un mvn clean install en la raiz del proyecto en caso no ejecute debido a que estoy usando mapstruct para el mapeo entre clases.
3. La collection se encuentra en la carpeta /docs de la raiz del proyecto.

# Justificación del modelo de base de datos:
![nofound](docs/diagrama-er.png)

Decidí agregar las entidades users y notes para la solución y tags para añadirle funcional adicional, las relaciones entre las entidades fueron decididas se la siguiente manera:
1. Decidi que usuario y nota tengan una relacion de 1:N porque cada usuario puede crear multiples notas,
   pero cada nota pertenece a un unico usuario.
2. Decidi que las notas y tags tengan una relacion de N:M porque un tag puede compartirse entre las notas
   y una nota puede tener varios tags.

# Autenticación requerida
Para realizar una prueba al recurso protegido se requiere un token JWT válido en el encabezado
para ello se debe realizar un post al endpoint de autenticacion.

Ejemplo de request:

POST /auth/login  
Host: localhost:8080  
Content-Type: application/json

```json
{
    "username": "raphael",
    "password": "123"
}
```
Nota: los usuarios cargados al iniciar la app son "raphael" y "jose", tienen el mismo password (123) para los ejemplos.

# Ejemplos de Casos de uso desarrollados
### Caso de uso: Crear nota
Actor: Usuario autenticado

Flujo:
- El usuario envía un POST con título, contenido y tags.
- El sistema valida los datos de entrada.
- El sistema identifica al usuario autenticado y lo asocia a la nota.
- El sistema aplica las reglas de negocio definidas para la creación de notas.
- El sistema registra los tags que no existen y los asocia a la nota.
- El sistema guarda la nota y retorna el resultado completo al usuario.

**Ejemplo de request:**

POST /notes  
Host: localhost:8080  
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...  
Content-Type: application/json

```json
{
	"title": "Hablando sobre lenguajes de programación",
	"content": "Los lenguajes de programación son...",
	"tags": ["java", "python"]
}
```
**Ejemplo de response:**
```json
{
    "user": {
        "id": 1,
        "username": "raphael",
        "role": "USER"
    },
    "title": "Hablando sobre lenguajes de programación",
    "content": "Los lenguajes de programación son...",
    "tags": [
        {
            "id": 1,
            "name": "java"
        },
        {
            "id": 2,
            "name": "python"
        }
    ]
}
```

### Caso de uso: Listar notas
Actor: Usuario autenticado  
Flujo:
- El usuario envía un GET a la ruta /notes.
- El sistema identifica al usuario autenticado y obtiene las notas que le corresponden.
- El sistema retorna al usuario la lista de notas encontradas.

**Ejemplo de request:**

GET /notes  
Host: localhost:8080  
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

**Ejemplo de response:**
```json
[
    {
        "id": 1,
        "user": {
            "id": 1,
            "username": "raphael",
            "role": "USER"
        },
        "title": "Java",
        "content": "java es un lenguaje de...",
        "tags": [
            {
                "id": 1,
                "name": "java se"
            },
            {
                "id": 2,
                "name": "java ee"
            }
        ]
    }
]
```

### El sistema se desarrolló siguiendo diferentes principios algunos mencionados son:

Principio SRP, el caso de uso cumple con su responsabilidad de ejecutar la acción de crear nota y cada servicio cumple con su propio objetivo.
![nofound](docs/principio_srp.png)

Principio ISP, aqui decido separar en interfaces mas pequeñas las aciones de lectura y escritura para no depender netamente de crudrepositorio en caso necesito solo de la accion "crear" o "listar", por ejemplo si mañana mas tarde una entidad solo necesita acciones de lectura solo implementaria ReadRepository  
![nofound](docs/principio_isp.png)

Patron Observer, decidi agregar este patron en caso se requiera notificar o realizar otra acción luego de registrar la nota (quizás notificar a subcritores, generar un resumen de la nota, etc...), se puede incluir más observadores que esten a la escucha de la clase NoteCreatedEvent.  
![nofound](docs/patron_observer.png)

