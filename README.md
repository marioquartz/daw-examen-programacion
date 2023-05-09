Codigo de la parte practica del examen de Programación. Es una forma de hacerlo.

# Como esta organizado #
Esta compuesto por dos clases: Database y Examen

## Database ##
Esta clase gestiona la conexión con la base de datos y tiene 3 funciones:
- openConnection() Crea la conexión
- closeConnection() La cierra (aunque luego no la use, eso me habra quitado decimas)
- createStatement() Para crear cada consulta.

## Examen ##
El nucleo. La parte importante. Tiene dos propiedades privadas:
 - Database database. Guarda la clase con la que hacemos las consultas.
 - Scanner teclado. Para recibir datos desde el teclado y no tener que iniciarla cada vez.

Y tiene las siguientes funciones:
 - Examen() Constructor de la clase. Sirve para iniciar la base de datos.
 - main() El resto de funciones se llaman desde aqui. ¿Por que las he separado? Por que me permite organizar mejor el codigo. Y una vez acabo un ejercicio solo tengo que comentar la llamada para que lo anterior no me moleste.
 - menu() Muestra el menu y pide un numero para acceder a las opciones del CRUD. Una vez acabas de hacer la opción correspondiente se auto-llama para que puedas ir a otra opción. (Tenia que haber añadido una opción de salir)
 - mostrarEquipos() Realiza una consulta y con un bucle muestra cada equipo. Yo añadi ";" y para que se viera mejor.
 - mostrarCiudad() Pides el nombre del jugador y creas una variable string para la ciudad. Si el jugador existe el bucle sobre-escribe la ciudad. Si no ciudad esta vacía. Uso esto para mostrar el mensaje correspondiente.
 - jugadorMasAlto() Este ejercicio es mas complicado. Hay dos formas de hacerlo: la pro y en dos pasos.
   - Forma Pro: Con sub-consultas (Lo que he hecho)
   - Dos pasos: Pedir los equipos que forman parte de una division. Añades los nombres a un String: lista+="'"+result.getString("name")+"'," y luego con esa lista pides los jugadores que estan en esos equipos. Ordenas el resultado y coges el primero.
 - mediaEquipo() Puedes hacerlo con la funcion avg() o sumando todas las alturas y usando un contador (una variable int al que le añades uno por cada jugador) hayas la media.
 - opcion1() la primera parte del CRUD es poder añadir un jugador. Pides cada variable y luego vas añadiendo cada valor a una consulta insert. Guardando la consulta como int puedes saber si se ha guardado o no.
 - opcion2() Pedir el numero del jugador y proceder a eliminarlo. Lo mismo que arriba para saber si se ha eliminado o no.
 - opcion3() Es un update en que se pide multiplicar la columna por el valor indicado.
