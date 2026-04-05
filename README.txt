TP realizado por:
Sosa Chirino Marcos Antonio
DNI: 46807958

Descripción:
un conversor de monedas simple (de dolar a euro y viceversa), en donde se puede interactuar escogiendo el tipo de moneda y
también pudiendo cambiar el valor del euro. Solo del euro.
Este trabajo practico agrupa todos los conceptos vistos en clase y son puestos en practica.

Implementación del patrón MVVM:

Modelo: Representado por la clase Conversor, que contiene la lógica de negocio pura (fórmulas de conversión y gestión de la tasa de cambio). 

ViewModel (MainActivityViewModel): Actúa como intermediario entre el Modelo y la Vista.
Gestiona el estado de la aplicación mediante el uso de MutableLiveData para las variables de resultado, error y tasaActual.
Estos se exponen a la Vista como LiveData para garantizar el encapsulamiento, permitiendo que la Activity solo pueda observar los cambios sin modificar la lógica interna.
El ViewModel no tiene referencias a la Vista, lo que permite que los datos sobrevivan a cambios de configuración (como rotar la pantalla).

Vista (XML y MainActivity):
Activity_Main: Define la estructura visual del conversor.

MainActivity: Se encarga exclusivamente de la Lógica de la vista (la interfaz de usuario). Observa los LiveData del ViewModel y actualiza los componentes visuales automáticamente cuando el estado cambia.
Implementa ViewBinding para acceder a los componentes del XML de manera segura (evitando findViewById), optimizando la comunicación entre el código y la interfaz.
Gestiona eventos simples de la interfaz, como el bloqueo/habilitación de campos según el RadioButton seleccionado, ya que estas acciones no afectan la lógica de negocio, sino únicamente a la experiencia visual del usuario.
